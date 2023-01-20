package notice.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import notice.mybatis.MybatisConfig;

public class NoticeDAO {
	MybatisConfig config = MybatisConfig.getInstance();
	
	public List selectAll() {
		List list=null;
		SqlSession sqlSession=config.getSqlSession();
		list=sqlSession.selectList("notice.selectAll");
		config.release(sqlSession);
		return list;
	}
}
