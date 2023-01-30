package mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.blood.BloodAdvisor;

//이 클래스는 MVC에서 컨트롤러로써 역할을 수행한다.
//컨트롤러란? Model 과 View를 분리시키키 위한 중간자 역할
/*
 * JaveEE 분야에서 MVC개발 방법이론을 적용하려면 다음과 같다
 * 1) Model - 재사용 가능한 중립적 코드이어야 하므로 순수 .java이용
 * 				 - Plain Old Java Object(POJO)
 
 * 2) View - 웹서버에서 실행될수 있으며, 디지도 표현가능한 객체
 * 				 JSP가 그 역할을 수행한다.. 여기에 로직이 들어있으면 무식한거다
  
 * 3) Controller - 웹서버에서 실행될수 있으며, 클라이언트의 요청과 응답을 
 * 					  -  처리할 수 있는 객체 Servlet이 그 역할을 수행
 
 * 모델2 - JavaEE로 구현(모델)한 MVC(이론) 패턴
 * 모델1 - 우리가 지금까지 써왔던 방식(jsp가 디자인 및 컨트롤러까지 모두 감당) 
 * */
public class BloodController {
	BloodAdvisor advisor=new BloodAdvisor();
	
	protected void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받아오기!
		String blood=request.getParameter("blood");
		
		//현재코드에서, 중립적인 자바 코드는 굳이 jsp안에 둘 필요없다.
		//이유? 미래의 재사용성을 위해서 별도로 분리시켜 놓아야 한다..
		
		//3단계) 알맞는 로직 객체에게 일 시키기 (직접하지 않음!!)
		String msg=advisor.getAdvice(blood);
		
		//출력은 가능하지만, 출력하면 안된다!!
		//그 이유?? M,V,C 로 개발하기 위해 철지히 분리시키고 있는 상황에
		//out.print()하는 순간 뷰 역활을 수행하게 됨 
		//System.out.println(msg); //브라우저에 안찍히고 콘솔에 찍힘
		//결과를 보여주는 View 역할을 하는 페이지가 result.jsp이므로 
		//result.jsp가 결과를 참조할수 있도록 어디간에 저장해놓자...
		//현재 배운기술이 session밖에 없으므로 session에 담아보자!!
//		HttpSession session=request.getSession();
//		session.setAttribute("msg", msg);
	
		//엔터프라이즈 급의 어플리케이션에서 모든 정보를 세션에 담아버리면 세션이 너무 비대해 진다..
		//해결책? 세션의 역할을 수행할수 있는 객체가 있다면, 세션이 가벼워질것이다..
		//요청이 들어오면, 이 요처엥 대해서 응답을 보류하고, 서버의 특정 서블릿으로
		//요청의 방향을 전달하는 방법을 가리켜 forwarding 이라 한다.
		//포워딩을 이용하면 현재 요청에 대한 응답을 하지 않은 상태이므로, 
		//request, response 객체가 죽지 않고 유지된다..
		//따라서 죽지 않은 request 객체에 원하는 정보를 세션에 담듯 이용할수 있다..
		
		//4단계) 결과저장
		request.setAttribute("msg", msg);
		
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out=response.getWriter();
		
//		out.print("<script>");
//		out.print("location.href='/blood/result.jsp';");
//		out.print("</script>");
	}
}
