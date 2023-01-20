package notice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿 = 오직 서버에사만 실행될 수 있는 javaEE 기반 클래스
//무조건 서블릿은 매핑이다.!!
public class DeleteServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기
		String notice_idx=request.getParameter("notice_idx");
		
		System.out.println("넘어온 notice_idx"+notice_idx);
	}
}
