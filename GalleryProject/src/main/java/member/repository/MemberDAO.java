package member.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gallery.pool.PoolManager;
import member.domain.Member;

public class MemberDAO {
	PoolManager pool=PoolManager.getInstance();
	
	public int insert(Member member) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=pool.getConnection();
		
		String sql="insert into member(member_idx, id, pass, name)";
		sql+=" values(seq_member.nextval, ?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			
			result=pstmt.executeUpdate(); //쿼리수행
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con,pstmt);
		}
		return result;
	}
	
	public Member select(Member member) {
		Member dto=null;
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		con=pool.getConnection();
		String sql="select * from member where id=? and pass=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			rs=pstmt.executeQuery();
			
			if(rs.next()) { //일치하는 회원 레코드가 있다면..
				dto=new Member();
				//회원 서비스를 위해 회원정보를 모두 담아놓자!!
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getString("regdate"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con,pstmt,rs);
		}
		return dto; //충돌날까봐 리턴은 dto
	}
	
	
	
	
	
	
	
	
}
