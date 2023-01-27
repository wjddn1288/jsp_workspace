package com.jspshop.domain;

import lombok.Data;

@Data
public class Cart{
	private Product product;
	//상품에는 존재하지 않는 속성인 개수를 자식에다가만 추가하자~~
	private int ea;
	private Member member;
}
