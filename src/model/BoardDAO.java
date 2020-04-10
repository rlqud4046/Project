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
	private static BoardDAO instance = new BoardDAO();

	private BoardDAO() {
	}

	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
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
	
	//group_no에 해당하는 전체게시글을 받아옴
	public List<BoardDTO> listBoard(int group_no){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			openConn();
			sql="select * from board_table where group_no=? order by mgn_no desc ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setMgn_no(rs.getInt("mgn_no"));
				dto.setBoard_category(rs.getInt("board_category"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_photo(rs.getString("board_photo"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}//listBoard() end
	
	//Board_table에서 해당그룹의 공지사항을 받아오는 메서드
	public List<BoardDTO> listNotice(int group_no){
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			openConn();
			sql="select * from board_table where group_no=? and board_category=2 order by mgn_no desc";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setMgn_no(rs.getInt("mgn_no"));
				dto.setBoard_title(rs.getString("board_title"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}//listNotice() end;
	
	//Board_table에서 해당그룹의 가입인사를 받아오는 메서드
		public List<BoardDTO> listHello(int group_no){
			
			List<BoardDTO> list = new ArrayList<BoardDTO>();
			
			try {
				openConn();
				sql="select * from board_table where group_no=? and board_category=3 order by mgn_no desc";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, group_no);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setMgn_no(rs.getInt("mgn_no"));
					dto.setBoard_title(rs.getString("board_title"));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return list;
		}//listHello() end;
		
		//Board_table에서 해당그룹의 정모게시판을 받아오는 메서드
		public List<BoardDTO> listMeet(int group_no){
			
			List<BoardDTO> list = new ArrayList<BoardDTO>();
			
			try {
				openConn();
				sql="select * from board_table where group_no=? and board_category=4 order by mgn_no desc";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, group_no);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setMgn_no(rs.getInt("mgn_no"));
					dto.setBoard_title(rs.getString("board_title"));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return list;
		}//listMeet() end;
		
		//Board_table에서 해당그룹의 자유게시판을 받아오는 메서드
		public List<BoardDTO> listFree(int group_no){
			
			List<BoardDTO> list = new ArrayList<BoardDTO>();
			
			try {
				openConn();
				sql="select * from board_table where group_no=? and board_category=5 order by mgn_no desc";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, group_no);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setMgn_no(rs.getInt("mgn_no"));
					dto.setBoard_title(rs.getString("board_title"));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return list;
		}//listFree() end;
		
		//Board_table에서 해당그룹의 사진경로,제목을 받아오는 메서드
		public List<BoardDTO> listPhoto(int group_no){
			
			List<BoardDTO> list = new ArrayList<BoardDTO>();
			
			try {
				openConn();
				sql="select * from board_table where group_no=? and board_category=6 order by mgn_no desc";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, group_no);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setMgn_no(rs.getInt("mgn_no"));
					dto.setBoard_photo(rs.getString("board_photo"));
					dto.setBoard_title(rs.getString("board_title"));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return list;
		}//listPhoto() end;
		

}
