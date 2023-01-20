package gallery.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gallery.repository.GalleryDAO;

public class DeleteServlet extends HttpServlet {
	// 이미 양식이 있기때문에 일부러 post로 받을꺼임
	GalleryDAO galleryDAO = new GalleryDAO();
	ServletContext context; // 어플리케이션의 전반적인 컨텍스트..(행위 달성을 위한 부가정보)

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청 파라미터 받기
		// 주의) 이 문서에 특성? 못들음 을 가져오는거임
		response.setContentType("text/html;charset=utf-8");

		String gallery_idx = request.getParameter("gallery_idx");
		String filename = request.getParameter("filename");

		System.out.println("gallery_idx is" + gallery_idx);

		// galleryDAO.delete("gallery_idx is"+gallery_idx);
		// delete gallery where gallery_idx=5; -> 이런식이니까 인코딩 필요없음

		// 로컬 하드의 파일을 제어하기 위해서는 자바의 File클래스..
		// 웹 어플리케이션에 루트만 정해줘도 하드디스크에 스스로 조사해줌
		context = request.getServletContext();

		// root를 기준으로해서 data 안에 너가 조사해!
		String app_path = context.getRealPath("/data");
		// System.out.println("현재 어플리케이션의 data디렉토리의 실제위치는" + app_path);

		File file = new File(app_path + "/" + filename);
		System.out.println(app_path + "/" + filename);

		PrintWriter out = response.getWriter(); // 출력스트림 객체 얻기

		if (file.delete()) {// 파일이 삭제되었다면...
			int result = galleryDAO.delete(Integer.parseInt(gallery_idx)); // DB삭제 + 파일

			out.print("<script>");
			if (result > 0) {
				out.print("alert('삭제완료');");
				out.print("location.href='/gallery/list.jsp';");
			} else {
				out.print("alert('삭제실패');");
				out.print("history.back();");
			}
			out.print("</script>");
		}
	}
}
