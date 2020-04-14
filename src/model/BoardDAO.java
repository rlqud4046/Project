package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	// 싱글톤 객체 만들기
	// 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private으로 선언 한다
	// 2. 정적 멤버로 선언한다
	private static BoardDAO instance = new BoardDAO();
	
	// 3.기본 생성자는 외부에서 접근이 되지 않도록 해야한다 - private 으로 생성자를 만듬
	// 외부에서 new를 사용하지 못하게 하는 접근기법
	private BoardDAO() {}
	
	// 4. 생성자 대신 싱글톤 객체를 return 해주는 getInstance() 메서드를 만들어 주자
	public static  BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	public Connection openConn() {
		try {
			// 1. JNDI 객체 생성
			InitialContext ic = new InitialContext();
			
			// 2. lookup() 메서드를 이용해서 매칭되는 커넥션을 찾는다
			DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/liger");
			
			// 3. DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다
			con = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	} // openConn() 메서드 end
	
	public void closeConn() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 게시판에 글 작성하는 메서드
	public int insertBoard(BoardDTO dto) {
		int result = 0;
		int board_no = 0;
		try {
			openConn();
			con.setAutoCommit(false);
			sql = "SELECT MAX(board_no) FROM board_test WHERE group_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getGroup_no());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board_no = rs.getInt(1)+1;
			}
//			System.out.println("글번호 최대값:"+board_no); // 글이 없을경우 0			
			sql = "INSERT INTO board_test "
				+ "(mgn_no, group_no, board_no, board_category, board_title, board_cont, "
				+ "board_writer, nickname, board_date, board_hit, board_like, board_photo, board_file, "
				+ "board_group, board_step, board_indent, board_imp) "
				+ "VALUES(board_test_seq.nextval,?,?,?,?,?,?,?,sysdate,0,0,?,?,board_test_seq.currval,0,0,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getGroup_no());
			pstmt.setInt(2, board_no);
			pstmt.setInt(3, dto.getBoard_category());
			pstmt.setString(4, dto.getBoard_title());
			pstmt.setString(5, dto.getBoard_cont());
			pstmt.setString(6, dto.getBoard_writer());
			pstmt.setString(7, dto.getNickname());
			pstmt.setString(8, dto.getBoard_photo());
			pstmt.setString(9, dto.getBoard_file());
			pstmt.setInt(10, dto.getBoard_imp());
			result = pstmt.executeUpdate();
			con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			closeConn();
		}
		return result;
	}
	
	// 특정 게시판의 모든 개시물 갯수를 구하는 메서드
	public int getRowCount(int group_no, int board_category) {
		int result = 0;
		
		try {
			openConn();
			sql = "SELECT COUNT(*) FROM board_test WHERE group_no=? AND board_category=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			pstmt.setInt(2, board_category);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return result;
	}
	
	// 특정 게시판의 검색어에 해당하는 데이터 갯수를 구하는 메서드
	public int getRowCount(int group_no, int board_category, String keyword) {
		int result = 0;
		
		try {
			openConn();
			sql = "SELECT COUNT(*) FROM board_test WHERE group_no=? "
				+ "AND board_category=? " 
				+ "AND (UPPER(board_title) LIKE UPPER(?) OR UPPER(board_writer) LIKE UPPER(?) OR UPPER(board_cont) LIKE UPPER(?))";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			pstmt.setInt(2, board_category);
			pstmt.setString(3, "%"+keyword+"%");
			pstmt.setString(4, "%"+keyword+"%");
			pstmt.setString(5, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return result;
	}
	
	// 사진첩 글 목록을 불러오는 메서드
	public List<BoardDTO> getGalleryList(int startNo, int endNo, int group_no, int board_category){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			openConn();
			sql = "SELECT * "
					+ "FROM(SELECT p.*, ROW_NUMBER() OVER(ORDER BY board_no DESC) rnum " 
					+ "FROM board_test p " 
					+ "WHERE group_no=? AND board_category=? " 
					+ "ORDER BY board_no DESC, board_step) " 
					+ "WHERE rnum >= ? AND rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			pstmt.setInt(2, board_category);
			pstmt.setInt(3, startNo);
			pstmt.setInt(4, endNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setMgn_no(rs.getInt("mgn_no"));
				dto.setGroup_no(rs.getInt("group_no"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_category(rs.getInt("board_category"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setNickname(rs.getString("nickname"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_like(rs.getInt("board_like"));
				dto.setBoard_photo(rs.getString("board_photo"));
				dto.setBoard_file(rs.getString("board_file"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));
				dto.setBoard_imp(rs.getInt("board_imp"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return list;
	}
	
	// 게시판의 특정 검색어에 해당하는 데이터를 가져오는 메서드
	public List<BoardDTO> getGalleryList(int startNo, int endNo, int group_no, int board_category, String keyword){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			openConn();
			sql = "SELECT * "
				+ "FROM(SELECT p.*, ROW_NUMBER() OVER(ORDER BY board_no DESC) rnum " 
				+ "FROM board_test p " 
				+ "WHERE group_no=? AND board_category=? "
				+ "AND (UPPER(board_title) LIKE UPPER(?) OR UPPER(board_writer) LIKE UPPER(?) OR UPPER(board_cont) LIKE UPPER(?))" 
				+ "ORDER BY board_no DESC, board_step) " 
				+ "WHERE rnum >= ? AND rnum <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			pstmt.setInt(2, board_category);
			pstmt.setString(3, "%"+keyword+"%");
			pstmt.setString(4, "%"+keyword+"%");
			pstmt.setString(5, "%"+keyword+"%");
			pstmt.setInt(6, startNo);
			pstmt.setInt(7, endNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setMgn_no(rs.getInt("mgn_no"));
				dto.setGroup_no(rs.getInt("group_no"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_category(rs.getInt("board_category"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setNickname(rs.getString("nickname"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_like(rs.getInt("board_like"));
				dto.setBoard_photo(rs.getString("board_photo"));
				dto.setBoard_file(rs.getString("board_file"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));
				dto.setBoard_imp(rs.getInt("board_imp"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return list;
	}
}
