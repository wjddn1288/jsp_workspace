package com.jspshop.domain;

import lombok.Data;

@Data
public class Color {
	private int color_idx;
	//객체 자식이 product를 가는거니까 어쏘시에이트
	private Product product; //부모인 product를 assocation(1:1 나와 부모)으로 가져오기
	private String color_name;
}
