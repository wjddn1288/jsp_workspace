package com.mvc3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc3.model.MovieAdvisor;

//영화에 대한 요청을 처리하는 하위 컨트롤러
//3단계: 일 시키기
//4단계: 결과가 있다면 저장하기
public class MovieController implements Controller { //컨트롤러의 자식 is a 관계
	String TAG = this.getClass().getName(); // 태그에 무비컨트롤 명이 들어감
	MovieAdvisor advisor = new MovieAdvisor(); // 무비 전문 엔지니어

	public void execute(HttpServletRequest request, HttpServletResponse response) { // 일을 시키는 메서드
		System.out.println(TAG + "의 handle 호출");
		
		String movie=request.getParameter("movie"); //파라미터 받기
		
		//3단계) 일시키기
		String msg=advisor.getAdvice(movie);
		
		//4단계) 결과 저장 4단계가 있다는 경우 무조건 포워딩
		request.setAttribute("msg", msg);
		
	}

	@Override
	public String getViewName() {
		return "/movie/view";
	}
}
