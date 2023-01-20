package board.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//Mybatis의 설정 정보를 읽어와서, 쿼리를 수행하는 객체인 SqlSession 객체를 모아서
//관리해주는 객체인 SqlSessionFactory를 , 싱글턴 클래스로 관리해보자
public class MybatisConfig {
	private static MybatisConfig instance;

	// SqlSession 들이 모여 사는 공장...(팩토리 패턴) 팩토리에 살고 있는 세션이 쿼리문 수행하는거임
	SqlSessionFactory sqlSessionFactory = null; // connection pool이라고 생각하면 된다

	private MybatisConfig() {
		String resource = "board/mybatis/config.xml";

		// 순수 jdbc 코드에서는 쿼리 실행을 담당하는 객체는 PreparedStatement 였지만,
		// Mybatis는 jdbc를 wrapper하여 SqlSession 이라는 쿼리수행 객체를 지원해준다...
		// 즉 내부적으로는 사실 jdbc가 사용되고 있다. 따라서 개발자가 쿼리문을 수행하기 위해서는
		// SqlSession을 얻어와야 하는데, 이 SqlSession를 관리해주고 반환해주는 객체가 바로
		// SqlSessionFactory이다.

		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// SqlSessionFactory로 부터 쿼리수행 객체인 SqlSession을
	// 반환해줄수 있는 메서드 정의
	public SqlSession getSqlSession() {
		// 팩토리로부터 쿼리수행 객체인 SqlSession 하나 반환해주기
		return sqlSessionFactory.openSession();
	}

	public void release(SqlSession sqlSession) {
		if (sqlSession != null) {
			sqlSession.close();
		}
	}

	public static MybatisConfig getInstance() {
		if (instance == null) {
			instance = new MybatisConfig();
		}
		return instance;
	}
}
