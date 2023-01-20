package news.repository;

import org.apache.ibatis.session.SqlSession;

import news.domain.Comments;
import news.mybatis.MybatisConfig;

public class CommentsDAO {
	MybatisConfig config = MybatisConfig.getInstance();

	// 글 한건 넣기
	public int insert(Comments comments) {
		int result = 0;
		SqlSession sqlSession = config.getSqlSession();

		// comments 등록
		result = sqlSession.insert("Comments.insert", comments);
		sqlSession.commit();
		config.release(sqlSession);

		return result;
	}
}
