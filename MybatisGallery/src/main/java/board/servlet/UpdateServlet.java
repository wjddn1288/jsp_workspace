package board.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import board.domain.Board;
import board.repository.BoardDAO;
import board.util.FileManager;

public class UpdateServlet extends HttpServlet {
	ServletContext context;
	DiskFileItemFactory factory;
	int maxSize = 5 * 1024 * 1024;
	String savePath;
	BoardDAO boardDAO= new BoardDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();

		context = request.getServletContext();
		// 설정객체
		factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxSize); // 문지방 용량제한?
		savePath = context.getRealPath("/data/");

		// 임시파일의 위치이자, 실제 파일이 위치할 곳
		factory.setRepository(new File(savePath));

		// 업로드담당 객체
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 요청분석 시작
		Board board = new Board();
		try {
			List<FileItem> itemList = upload.parseRequest(request);

			// 우리의 경우 4회전...

			for (FileItem item : itemList) {
				if (item.isFormField()) { // 텍스트 데이터라면...
					switch(item.getFieldName()) {
						case "board_idx" : board.setBoard_idx(Integer.parseInt(item.getString()));break;
						case "filename" : board.setFilename(item.getString());break;
						case "title" : board.setTitle(item.getString("utf-8"));break;
						case "writer" : board.setWriter(item.getString("utf-8"));break;
						case "content" : board.setContent(item.getString("utf-8"));break;
					}
				} else {
					//업로드 한 경우만...
					if(item.getSize()>0){
						long time=System.currentTimeMillis();
						String ext=FileManager.getExt(item.getName());
						
						File file=new File(savePath+time+"."+ext); //새롭게 업로드 될 파일
						try {
							item.write(file);
							
							//기존 파일 찾아서 제거
							File df=new File(savePath+board.getFilename());
							
							if(df.delete()) { //잘 지워졌다면.
								
								//새로운 파일명으로 대체
								board.setFilename(time+"."+ext);
								//DAO update()
//								boardDAO.update(board);
							}
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else {
						//파일업로드 없이 update
						//boardDAO.update(board);
					}
				}
			}
			//파일업로드 없이 update
			int result=boardDAO.update(board);
			
			out.print("<script>");
			if(result>0) {
				out.print("alert('수정 성공');");
				out.print("location.href='/board/list.jsp';");
			}else {
				out.print("alert('수정 실패')");
				out.print("history.back()");
			}
			out.print("</script>");
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
}
