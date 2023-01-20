package gallery.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//외부자원을 이름으로 접근하는 JNDI 기술을 이용하여, 톰캣이 지원하는 커넥션풀을 가져와 보자!!
//커넥션을 얻거나, 다시 돌려보내는 기능을 메서드화시켜서 필요한 객체가 편하게 사용할수 있도록 정의해보자!
public class PoolManager {
	private static PoolManager instance;
	InitialContext context; // JNDI 검색객체
	DataSource ds; // 커넥션풀 구현 체

	// 생성자는 단 한번 호출되므로, 이 시점에 커넥션풀을 얻어다놓자!!
	private PoolManager() {
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 커넥션 하나 빌리기
	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// 커넥션 반납
	public void release(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DML ( Connection, PreparedStatement)
	public void release(Connection con, PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Select (Connection, PreparedStatement, ResultSet)
	public void release(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static PoolManager getInstance() {
		if (instance == null) {
			instance = new PoolManager();
		}
		return instance;
	}
}