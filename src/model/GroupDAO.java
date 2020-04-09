package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

	public class GroupDAO {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		// 싱글톤 객체 만들기
		private static GroupDAO instance = new GroupDAO();

		private GroupDAO() {
		}

		public static GroupDAO getInstance() {
			if (instance == null) {
				instance = new GroupDAO();
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
		
		//searchG_no():그룹테이블에 있는 그룹번호중 가장 큰 값을 받아오자!
		public int searchG_no() {
			int group_no = 0;
			
			
			try {
				openConn();
				sql="select max(group_no) from group_table";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					group_no = rs.getInt(1)+1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			
			return group_no;
		}//searchG_no() end:
		
		
		//insertOK():모임개설시 그룹테이블에 값 추가하는메서드
		public int insertOK(GroupDTO dto) {
			int res = 0;
			
			try {
				openConn();
				sql="insert into group_table (group_no,group_name,group_intro,group_area,group_interest,group_premium,group_limitmem,group_currmem) values(?,?,?,?,?,?,?,?)";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, dto.getGroup_no());
				pstmt.setString(2, dto.getGroup_name());
				pstmt.setString(3, dto.getGroup_intro());
				pstmt.setInt(4, dto.getGroup_interest());
				pstmt.setInt(5, dto.getGroup_area());
				pstmt.setInt(6, 0);
				pstmt.setInt(7, 200);
				pstmt.setInt(8, 1);
				res = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return res;
		}//insertOK() end;
}
