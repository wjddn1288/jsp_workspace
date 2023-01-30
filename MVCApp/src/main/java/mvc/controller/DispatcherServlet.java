package mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.controller.board.RegistController;

/*
 * 하위 컨트롤러들이 직접 요청을 받게되면, 업무의 효율성과 유지보성이 저하된다..
 * 우리의 경우 너무 많은 서블릿 매핑이 필요하게 됨..
 * 따라서, 규모가 아무리 커질지라도, 모든 요청을 하나의 진입점으로 
 * 몰아서, 요청의 유형을 분석하여 적절한 컨트롤러들에게 배분할 주체가 필요
 * 
 * 컨트롤러의 전형적 업무 이 5단계를 벗어날수 없음 !!꼭 암기해야댐
 * 1) 요청을 받는다 (클라이언트랑 통신을 할수 있어야 한다)
 * 2) 요청을 분석한다(전문성있는 컨트롤러에게 전달할려고)
 * 		- 알맞는 하위 컨트롤러를 선택하여 요청을 전달하려고
 * 3) 알맞는 모델객체에게 일 시킨다 (일을 직접하는 순간 모델이 돼버림)
 * 4) 결과가 있을경우 결과를 저장해둔다 (m은 일만하고 컨트롤러가 가져가야지)
 * 5) 결과를 보여준다 (고양이가 저장해뒀다가 준다)
 * */
public class DispatcherServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //get,post 공통 코드
		//1단계 : 요청을 받는다.
		 System.out.println("요청 받았음"); 
		 
		 //2단계 : 현재 컨트롤러는 전문성이 없으므로, 담당 컨트롤러를 선택한다
		 String uri=request.getRequestURI();
		 System.out.println("클라이언트의 요청 URI는"+uri);
		 
		 //요청을 분석하자 왜 분석? 담당 컨트롤러에게 업무를 전달하기 위해
		 if(uri.equals("/blood.do")) {
			 //2단계) 혈액형 담당 컨트롤러 호출!
			 BloodController controller = new BloodController();
			 controller.excute(request, response); //하위 컨트롤러에게 일 시키기
			 
		 }else if(uri.equals("/movie.do")) {
			 //2단계) 영화 담당 컨트롤러 호출
			 MovieController controller = new MovieController();
			 //controller.메서드();
			 
		 }else if(uri.equals("/board/regist.do")) {
			 //2단계) 
			 RegistController controller =new RegistController();
			 controller.regist();
		 }
		 
		 //5단계)
		 RequestDispatcher dis= request.getRequestDispatcher("/blood/result.jsp");
		//포워딩 시작
		//죽지않은request,response 가져가기!!
		dis.forward(request,response);
	}
	
}









