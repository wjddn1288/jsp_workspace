package mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.model.movie.MovieAdvisor;

public class MovieController extends HttpServlet{
	MovieAdvisor advisor=new MovieAdvisor();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String movie=request.getParameter("movie");
		String msg=advisor.getAdvice(movie);
		
		HttpSession session=request.getSession();
		session.setAttribute("msg",msg);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		out.print("<script>");
		out.print("location.href='/movie/result.jsp';");
		out.print("</script>");
	}
}
