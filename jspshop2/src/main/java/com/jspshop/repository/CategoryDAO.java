package com.jspshop.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jspshop.mybatis.MybatisConfig;

public class CategoryDAO {
 MybatisConfig config= MybatisConfig.getInstance();
 
	public List selectAll() { // 파라미터 회원임
		// db에서 가져온 회원 /파라미터 회원이랑 차이점있음!!
		List list = null; // admin이랑 겹칠까봐 피해갈려고
		SqlSession sqlSession = config.getSqlSession();
		list = sqlSession.selectList("Category.selectAll"); // 핵심
		config.release(sqlSession);
		return list;
	}
	
}
