package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	//mgn_no에해당하는 mem_no를 모두받아옴
	   public List<LikeDTO> LikeMemList(int mgn_no){
	      List<LikeDTO> memList = new ArrayList<LikeDTO>();
	      
	      try {
	         openConn();
	         sql="select mem_no from like_table where mgn_no=?";
	         
	         pstmt = con.prepareStatement(sql);
	         pstmt.setInt(1, mgn_no);
	         
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	            LikeDTO dto = new LikeDTO();
	            dto.setMem_no(rs.getInt("mem_no"));
	            dto.setMgn_no(rs.getInt("mgn_no"));
	            
	            memList.add(dto);
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         closeConn(rs, pstmt, con);
	      }
	      return memList;
	   }//end
	   
	   
	   public List<JoinDTO> getLikeBoardList(int mem_no) {
			
			 List<JoinDTO> list = new ArrayList<JoinDTO>();
			
		      try {

			    con = openConn();
			    sql = "select * from join_view where mem_no = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, mem_no);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					JoinDTO dto = new JoinDTO();
					dto.setMgn_no(rs.getInt("mgn_no"));
					dto.setGroup_no(rs.getInt("group_no"));
					dto.setGroup_name(rs.getString("group_name"));
					dto.setBoard_category(rs.getInt("board_category"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_date(rs.getString("board_date"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_like(rs.getInt("board_like"));

					list.add(dto);
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			 
			 return list;
		}
	
	

}
