package empapp.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import empapp.mybatis.MybatisConfig;

public class EmpDAO {
	MybatisConfig config=MybatisConfig.getInstance();
	
	//부서번호에 소속된 모든 사원 가져오기
	public List selectByFkey(int deptno) {
		List list=null;
		SqlSession sqlSession=config.getSqlSession();
		list= sqlSession.selectList("Emp.selectByFkey", deptno); //,주서 넘겨줘야댐!
		config.release(sqlSession);
		return list;
	}
	
	public List selectBySearch(Map map) { //dto를 대신하는거니까 Map오면 됨!
		List list=null;
		SqlSession sqlSession=config.getSqlSession();
		list= sqlSession.selectList("Emp.selectBySearch", map); //,주서 넘겨줘야댐!
		config.release(sqlSession);
		return list;
	}
}
