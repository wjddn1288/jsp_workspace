package com.jspshop.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jspshop.domain.Member;
import com.jspshop.exception.MemberException;
import com.jspshop.mybatis.MybatisConfig;

public class MemberDAO {
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List selectAll() {
		return sqlSession.selectList("Member.selectAll");
	}
	
	//id,pass 일치하는 사람 가져오기(한사람)
	//비지니스 레이아웃에서는 throws가 최고다!!
	public Member select(Member member) throws MemberException{
		Member dto=null;
		dto=sqlSession.selectOne("Member.select", member);
		if(dto==null) {
			throw new MemberException("회원정보가 올바르지 않습니다");
		}
		return dto;
	}
	
	//한건넣기 
	public void insert(Member member) throws MemberException{
		int result=0; //반환형이 아니라 예외처리 판단용도임!!
		result = sqlSession.insert("Member.insert", member);
		if(result <1) {
			throw new MemberException("입력실패");
		}
	}
	
	//한건 수정!! (insert복사함)
	public void update(Member member) throws MemberException{
		int result=0; //반환형이 아니라 예외처리 판단용도임!!
		result= sqlSession.update("Member.update",member);
		if(result<1) {
			throw new MemberException("수정 실패");
		}
	}
	
	//한건 삭제!!(update복사함)
	public void delete(int member_idx) throws MemberException{
		int result=0; //반환형이 아니라 예외처리 판단용도임!!
		result= sqlSession.delete("Member.delete",member_idx);
		if(result<1) {
			throw new MemberException("삭제 실패");
		}
	}
}
	/*public Member select(int member_idx ) { 
		Member member = null; 
		SqlSession sqlSession = config.getSqlSession();
		member = sqlSession.selectOne("Member.select");
		sqlSession.commit();
		config.release(sqlSession);
		return member;
	}
	
	public List selectAll() { 
		List list = null;
		SqlSession sqlSession = config.getSqlSession();
		list = sqlSession.selectList("Member.selectAll");
		config.release(sqlSession);
		return list;
	}
	
	public int insert(Member member) {
		int result=0;
		SqlSession sqlSession = config.getSqlSession();
		result = sqlSession.insert("Member.insert", member);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
	public int update(Member member) {
		int result=0;
		SqlSession sqlSession = config.getSqlSession();
		result = sqlSession.update("Member.update", member);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
	public int delete(int member_idx) {
		int result=0;
		SqlSession sqlSession = config.getSqlSession();
		result = sqlSession.delete("Member.delete",member_idx);
		sqlSession.commit();
		config.release(sqlSession);
		return result;

	}
*/
