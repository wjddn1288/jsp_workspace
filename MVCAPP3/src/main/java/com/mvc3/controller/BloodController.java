package com.mvc3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc3.model.BloodAdvisor;

//혈액형에 대한 요청을 처리하는 하위 컨트롤러
//3단계: 일 시키기
//4단계: 결과가 있다면 저장하기
public class BloodController implements Controller { //컨트롤러의 자식 is a 관계
	String TAG = this.getClass().getName(); // 태그에 블러드컨트롤 명이 들어감
	BloodAdvisor advisor = new BloodAdvisor(); // 혈액형 전문 엔지니어

	public void execute(HttpServletRequest request, HttpServletResponse response) { // 일을 시키는 메서드
		System.out.println(TAG + "의execute 호출");

		// 3단계) 알맞는 로직 객체에 일시키기
		String blood = request.getParameter("blood"); // 매개변수로 부터 받아와야댐!
		String msg = advisor.getAdvice(blood);

		// 브레이스 닫히면 죽으니까 어딘가()에 담아둬야댐 
		//4단계) request,response 객체가 아직 살아있으므로, 요청 객체에 결과 저장
		//4단계의 결과 저장 절차가 있을 경우 무조건 request는 살아있어야 하므로
		//포워딩(기존의 접속 유지) 처리해야 한다..
		request.setAttribute("msg", msg); //키값으로 저장
		
	}

	@Override
	public String getViewName() {
		return "/blood/view";
	}
}
