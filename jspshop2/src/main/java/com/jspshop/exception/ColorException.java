package com.jspshop.exception;

public class ColorException extends RuntimeException {
	
	//개발자가 전달하고픈 에러 메시지..
	public ColorException(String msg) {
		super(msg);
	}
}
