package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommentDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	// 싱글톤 객체 만들기
	private static CommentDAO instance = new CommentDAO();

	private CommentDAO() {
	}

	public static CommentDAO getInstance() {
		if (instance == null) {
			instance = new CommentDAO();
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

	public int commentCount(int mgn_no) {
		int count = 0;
		try {
			openConn();
			sql = "select count(*)from comment_table where mgn_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mgn_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return count;
	}

	public List<CommentDTO> commentCont(int mgn_no) {
		int count = 0;
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		try {
			openConn();
			sql = "select * from comment_table where mgn_no=? order by comment_group desc, comment_no";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mgn_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setComment_no(rs.getInt("comment_no"));
				dto.setComment_writer(rs.getString("comment_writer"));
				dto.setComment_cont(rs.getString("comment_cont"));
				dto.setComment_date(rs.getString("comment_date"));
				dto.setComment_parent(rs.getInt("comment_parent"));
				dto.setComment_group(rs.getInt("comment_group"));
				dto.setMgn_no(rs.getInt("mgn_no"));
				list.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return list;
	}

	public boolean insertComment(CommentDTO comment) {
		boolean result = false;

		try {
			// 자동 커밋을 false로 한다.
			openConn();
			con.setAutoCommit(false);

			sql = "insert into comment_table(mgn_no, comment_writer, comment_cont, comment_date, comment_group) values(?,?,?,sysdate,comment_table_seq2.nextval)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment.getMgn_no());
			pstmt.setString(2, comment.getComment_writer());
			pstmt.setString(3, comment.getComment_cont());

			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				result = true;
				con.commit(); // 완료시 커밋
			}

		} catch (Exception e) {
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
	} // end boardInsert();

	public boolean replyComment(CommentDTO dto) {
		boolean result = false;

		try {
			// 자동 커밋을 false로 한다.
			openConn();
			con.setAutoCommit(false);

			sql = "insert into comment_table(mgn_no, comment_writer, comment_cont, comment_date,comment_parent, comment_group) values(?,?,?,sysdate,?,?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getMgn_no());
			pstmt.setString(2, dto.getComment_writer());
			pstmt.setString(3, dto.getComment_cont());
			pstmt.setInt(4, dto.getComment_parent());
			pstmt.setInt(5, dto.getComment_parent());

			int flag = pstmt.executeUpdate();
			if (flag > 0) {
				sql = "select comment_table_seq2.nextval from dual";
				pstmt = con.prepareStatement(sql);
				pstmt.executeQuery();

				result = true;
				con.commit(); // 완료시 커밋
			}

		} catch (Exception e) {
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
	} // end boardInsert();

	public CommentDTO getComment(int comment_no) {
		CommentDTO dto = new CommentDTO();

		try {
			openConn();
			sql = "select * from comment_table where comment_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setComment_cont(rs.getString("comment_cont"));
				dto.setComment_date(rs.getString("comment_date"));
				dto.setComment_parent(rs.getInt("comment_parent"));
				dto.setComment_writer(rs.getString("comment_writer"));
				dto.setMgn_no(rs.getInt("mgn_no"));
				dto.setComment_no(rs.getInt("comment_no"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return dto;
	}

}
