package com.jspshop.util;

import lombok.Data;

@Data
public class MessageObject {
	private int code; //성공 또는 실패를 식별할수 있는 식별코드 1(성공),0(실패)
	private String msg; //클라이언트에게 전송하고 픈 말
}
