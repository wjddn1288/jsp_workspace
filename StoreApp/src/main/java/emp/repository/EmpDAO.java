package emp.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import store.mybatis.MybatisConfig;

public class EmpDAO {
	MybatisConfig config = MybatisConfig.getInstance();
	
	// 전체 목록 가져오기
	public List selectAll() {
		List list = null;

		SqlSession sqlSession = config.getSqlSession();
		list = sqlSession.selectList("Emp.selectAll");
		config.release(sqlSession);
		
		return list;
	}
}
