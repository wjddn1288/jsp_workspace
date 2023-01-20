package news.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import news.domain.News;
import news.repository.NewsDAO;
import news.util.ResponseMessage;

public class RegistServlet extends HttpServlet{
	NewsDAO newsDAO;
	
	public RegistServlet() { //고양이가 호출 하면서 init이랑 같이!! 
		newsDAO= new NewsDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		request.setCharacterEncoding("utf-8");
		
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		News news= new News(); //DTO에 채워넣기!!
		news.setTitle(title);
		news.setWriter(writer);
		news.setContent(content);
		
		int result=newsDAO.insert(news);
		if(result>0) {
 			out.print(ResponseMessage.getMsgURL("등록성공", "/news/list.jsp"));
		}else {
			out.print(ResponseMessage.getMsgBack("등록실패"));
		}
	}

}
