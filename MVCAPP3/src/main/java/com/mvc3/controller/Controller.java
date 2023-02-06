package com.mvc3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//MovieController, BloodController 등의 모든 하위 컨트롤러의 최상위 클래스
public interface Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response);
	
	//view 페이지 반환
	public String getViewName(); //컨트롤러 자식들 강제하기 위해서
	
	//포워딩 여부 (무조건 다 하면 안되니까)
	public boolean isForward();
}
