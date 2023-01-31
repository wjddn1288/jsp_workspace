package mvcapp2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc2app.model.blood.BloodAdvisor;

//3단계 수행
//알맞는 비즈니스 로직 객체에게 일을 시키는 하위 컨트롤러 정의
public class BloodController {
	BloodAdvisor advisor=new BloodAdvisor();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		//3단계)알맞은 로직 객체에 일 시키기
		String blood= request.getParameter("blood");
		String msg=advisor.getAdvice(blood);
		
		
		//4단계)(클라이언트에게 전달할) 결과가 있으므로, 임시적으로 저장해 놓아야 한다
		//저장(session 보다는 request에 보관한다)
		//여기서의 reqeust 객체는 응답하기전까지는 생명력이 있으므로,
		//포워딩 처리로 전달하면, view인 jsp까지는 죽지 않고 도달할 수 있다..
		//이 경우 굳이 session을 이용할 필요 없다
		request.setAttribute("msg", msg); //결과저장
		
	}
}
