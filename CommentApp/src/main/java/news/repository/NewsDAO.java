package news.repository;

import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import news.domain.News;
import news.mybatis.MybatisConfig;

public class NewsDAO {
	// SqlSessionFactory가 이 객체에 존재하므로...
	MybatisConfig config = MybatisConfig.getInstance();

	public int insert(News news) {
		int result = 0;
		SqlSession sqlSession = config.getSqlSession();
		result = sqlSession.insert("News.insert", news); // 뉴스라고 불리는 녀석의 쿼리문
		sqlSession.commit(); // DML이니까
		config.release(sqlSession); // 커밋하고 닫아주기
		return result;
	}

	public List selectAll() {
		List list = null;
		SqlSession sqlSession = config.getSqlSession();
		list = sqlSession.selectList("News.selectAll");
		config.release(sqlSession);
		return list;
	}

	public News select(int news_idx) { // 한건 가져오기
		News news = null;
		SqlSession sqlSession = config.getSqlSession();
		news = sqlSession.selectOne("News.select", news_idx);
		config.release(sqlSession);
		return news;
	}

	public int update(News news) { // int는 매핑 대상이 아님!! news가 매핑대상임!!
		return 0;
	}

	public int delete(int news_idx) {
		int result=0;
		SqlSession sqlSession = config.getSqlSession();
		result = sqlSession.update("News.delete", news_idx);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
}
