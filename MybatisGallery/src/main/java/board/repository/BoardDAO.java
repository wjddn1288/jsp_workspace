package board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.domain.Board;
import board.mybatis.MybatisConfig;

public class BoardDAO {
	// 아래의 싱글턴 객체의 인스턴스가 메모리에 생설될때, 동시에
	// 멤버변수로 존재하는 SqlSessionFactory로 올라간다!!(한번만..)
	MybatisConfig config = MybatisConfig.getInstance();

	public int insert(Board board) {
		int result = 0;
		SqlSession sqlSession = config.getSqlSession();
		result = sqlSession.insert("Board.insert", board); // 1=쿼리문 들어있는 xml코드 id /BoardDAO에 id
		sqlSession.commit(); // DML일 경우..
		config.release(sqlSession); // 닫아주기
		return result;
	}

	public List selectAll() {
		List list = null;
		SqlSession sqlSession = config.getSqlSession();
		list = sqlSession.selectList("Board.selectAll");
		config.release(sqlSession);
		return list;
	}

	public Board select(int board_idx) {
		Board board = null;
		SqlSession sqlSession = config.getSqlSession();
		board = sqlSession.selectOne("Board.select", board_idx);
		config.release(sqlSession);
		return board;
	}

	public int update(Board board) {
		int result = 0;
		SqlSession sqlSession = config.getSqlSession();
		result = sqlSession.update("Board.update", board);
		sqlSession.commit(); //DML이므로...
		config.release(sqlSession);
		return result;
	}
}
