package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LikeDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	// 싱글톤 객체 만들기
	private static LikeDAO instance = new LikeDAO();

	private LikeDAO() {
	}

	public static LikeDAO getInstance() {
		if (instance == null) {
			instance = new LikeDAO();
		}
		return instance;
	}

	// openConn()
	public Connection openConn() {

		try {
			// 1. JNDI 서버 객체 생성
			InitialContext ic = new InitialContext();

			// 2.lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.

			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");

			// 3.DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}// openConn() end

	// closeConn()
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// closeConn() end

	public boolean like(int mem_no, int mgn_no) {

		boolean result = false;
		try {
			openConn();
			con.setAutoCommit(false);
			sql = "insert into like_table values(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, mgn_no);

			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				result = true;
				con.commit(); // 완료시 커밋
			}

		} catch (SQLException e) {
			try {
				con.rollback(); // 오류시 롤백
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			closeConn(rs, pstmt, con);
		}

		return result;

	}

	public int likeCheck(int mem_no, int mgn_no) {
		int result = 0;

		try {
			openConn();
			con.setAutoCommit(false);
			sql = "select * from like_table where mem_no = ? and mgn_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, mgn_no);

			rs = pstmt.executeQuery();
			if (rs.next())
				result = 1;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			closeConn(rs, pstmt, con);
		}

		return result;

	}

}
