package com.jspshop.repository;

import org.apache.ibatis.session.SqlSession;

import com.jspshop.domain.Psize;
import com.jspshop.exception.PsizeException;

public class PsizeDAO {
	private SqlSession sqlSession;
	
public void setSqlSession(SqlSession sqlSession) {
	this.sqlSession = sqlSession;
}
	public int insert(Psize psize) throws PsizeException{
		int result=0;
		result=sqlSession.insert("Psize.insert", psize);
		if(result<1) {
			throw new PsizeException("사이즈 등록 실패");
		}
		return result;
	}
}
