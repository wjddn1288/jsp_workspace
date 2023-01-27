package com.jspshop.repository;

import org.apache.ibatis.session.SqlSession;

import com.jspshop.domain.Admin;
import com.jspshop.mybatis.MybatisConfig;

public class AdminDAO {
	MybatisConfig config = MybatisConfig.getInstance();

	// 관리자 1명 조회하기(id,pass 가 일치)
	public Admin select(Admin admin) { // 파라미터 회원임
		// db에서 가져온 회원 /파라미터 회원이랑 차이점있음!!
		Admin obj = null; // admin이랑 겹칠까봐 피해갈려고
		SqlSession sqlSession = config.getSqlSession();
		obj = sqlSession.selectOne("Admin.select", admin); // 핵심
		config.release(sqlSession);
		return obj;
	}
}
