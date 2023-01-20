package gallery.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gallery.domain.Gallery;
import gallery.pool.PoolManager;

//Gallery 테이블에 대한 CRUD만을 수행
public class GalleryDAO {
	PoolManager pool = PoolManager.getInstance();

	public int insert(Gallery gallery) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = pool.getConnection(); // 대여!

		String sql = "insert into gallery(gallery_idx, title, writer, content, filename)";
		sql += " values(seq_gallery.nextval, ?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gallery.getTitle());
			pstmt.setString(2, gallery.getWriter());
			pstmt.setString(3, gallery.getContent());
			pstmt.setString(4, gallery.getFilename());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt);
		}

		return result;
	}

	// 모든 레코드 가져오기
	public List selectAll() {
		List list = new ArrayList(); // gallery로 바꿔줌
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = pool.getConnection(); // 접속객체 대여
		String sql = "select * from gallery order by gallery_idx desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Gallery gallery = new Gallery();
				gallery.setGallery_idx(rs.getInt("gallery_idx"));
				gallery.setTitle(rs.getString("title"));
				gallery.setWriter(rs.getString("writer"));
				gallery.setContent(rs.getString("content"));
				gallery.setRegdate(rs.getString("regdate"));
				gallery.setHit(rs.getInt("hit"));
				gallery.setFilename(rs.getString("filename"));

				list.add(gallery);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt, rs);
		}
		return list;
	}

	// 모든 레코드 가져오기
	public Gallery select(int gallery_idx) {
		Gallery gallery = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con = pool.getConnection(); // 접속객체 대여
		String sql = "select * from gallery where gallery_idx=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gallery_idx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				gallery = new Gallery();
				gallery.setGallery_idx(rs.getInt("gallery_idx"));
				gallery.setTitle(rs.getString("title"));
				gallery.setWriter(rs.getString("writer"));
				gallery.setContent(rs.getString("content"));
				gallery.setRegdate(rs.getString("regdate"));
				gallery.setHit(rs.getInt("hit"));
				gallery.setFilename(rs.getString("filename"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt, rs);
		}
		return gallery;
	}

	// 레코드 한건 삭제하기
	public int delete(int gallery_idx) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = pool.getConnection(); // 대여
		String sql = "delete gallery where gallery_idx=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gallery_idx);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt);
		}
		return result;
	}

	public int update(Gallery gallery) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		con = pool.getConnection();
		String sql = "update gallery set title=?, writer=?, content=?";
		sql += ", filename=? where gallery_idx=?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gallery.getTitle());
			pstmt.setString(2, gallery.getWriter());
			pstmt.setString(3, gallery.getContent());
			pstmt.setString(4, gallery.getFilename());
			pstmt.setInt(5, gallery.getGallery_idx());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt);
		}
		return result;
	}
}
