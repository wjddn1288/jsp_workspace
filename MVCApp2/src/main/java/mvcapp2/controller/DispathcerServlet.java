package mvcapp2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//어플리케이션의 모든 요청을 혼자 다 받아서, 보다 전문적인 하위 컨트롤러에 
//전달하기 위한 진입점 클래스
//이 클래스의 존재가 없을 경우, 하위 모든 컨트롤러들은 직접 서블릿으로 정의하여
//수많은 매핑이 필요하게 되고, 각 컨트롤러간의 일관성도 없어서 
//유지보수성이 오히려 힘들어짐
public class DispathcerServlet extends HttpServlet {

	@Override //온갖 종류의 요청을 받아야 하므로, Get, Post, 가리지 않고 처리가 되어야 한다..
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	//post요청과 get요청을 하나로 공통 메서드에서 처리하자 우리가 만든 전면컨트롤러 메서드임
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청 파라미터에 대한 처리를 이 시점에 해두면, 하위 컨트롤러에서
		//처리할 필요가 없게 된다.
		//1단계) 아직 요청을 받고 있음 뭐 들어온게 없음 현시점에서 9:30
		request.setCharacterEncoding("utf-8");
		
		//2단계) 요청을 분석한다
		String uri=request.getRequestURI(); //uri 받아오기 "/blood.do")
		
		if(uri.equals("/blood.do")) { //누가 일해야될지 
			//혈액형 전문 하위 컨트롤러 생성하고, 메서드 호출!!
			BloodController controller= new BloodController();
			controller.execute(request, response); //동생이 알아서 3,4단계 채워놨음 블러드컨트롤러
			
			//5단계) 결과 보여주기
			//request를 살려서, 뷰인 jsp까지 가져가기!!
			RequestDispatcher dis=request.getRequestDispatcher("/blood/result.jsp");
			dis.forward(request,response); //포워딩
			
			//응답을 받은 클라이언트가, 지정한 URL로 다시 들어와라
			//response.sendRedirect("/blood/regist.jsp");
			
		}else if(uri.equals("/movie.do")) {
			//영화 전문 하위 컨트롤러 생성하고, 메서드 호출!!
			MovieController controller= new MovieController();
			controller.handle(request, response); //3,4단계 완료
			
			//무비페이지에 포워딩을 걸고싶어!
			RequestDispatcher dis=request.getRequestDispatcher("/movie/result.jsp");
			dis.forward(request,response); //포워딩

		}
	}

}
