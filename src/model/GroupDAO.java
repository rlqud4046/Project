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
	// 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private으로 선언 한다
	// 2. 정적 멤버로 선언한다
	private static GroupDAO instance = new GroupDAO();
	
	// 3.기본 생성자는 외부에서 접근이 되지 않도록 해야한다 - private 으로 생성자를 만듬
	// 외부에서 new를 사용하지 못하게 하는 접근기법
	private GroupDAO() {}
	
	// 4. 생성자 대신 싱글톤 객체를 return 해주는 getInstance() 메서드를 만들어 주자
	public static  GroupDAO getInstance() {
		if(instance == null) {
			instance = new GroupDAO();
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
	
	// 모임 정보를 수정하는 메서드
	public int updateGroupInfo(GroupDTO dto) {
		int result = 0;
		try {
			openConn();
			con.setAutoCommit(false);
			sql = "UPDATE group_table "
					+ "SET group_name=?, group_intro=?, group_front_img=?, group_main_img=?, group_insta=?, group_facebook=?, group_chatroom=? "
					+ "WHERE group_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getGroup_name());
			pstmt.setString(2, dto.getGroup_intro());
			pstmt.setString(3, dto.getGroup_front_img());
			pstmt.setString(4, dto.getGroup_main_img());
			pstmt.setString(5, dto.getGroup_insta());
			pstmt.setString(6, dto.getGroup_facebook());
			pstmt.setString(7, dto.getGroup_chatroom());
			pstmt.setInt(8, dto.getGroup_no());
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
	
	// 모임 정보 수정 시 기존 정보를 불러오는 메서드
	public GroupDTO getGorupInfo(int group_no) {
		GroupDTO dto = new GroupDTO();
		try {
			openConn();
			sql = "SELECT * FROM group_table WHERE group_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setGroup_no(rs.getInt("group_no"));
				dto.setGroup_name(rs.getString("group_name"));
				dto.setGroup_intro(rs.getString("group_intro"));
				dto.setGroup_area(rs.getInt("group_area"));
				dto.setGroup_interest(rs.getInt("group_interest"));
				dto.setGroup_premium(rs.getInt("group_premium"));
				dto.setGroup_limitmem(rs.getInt("group_limitmem"));
				dto.setGroup_currmem(rs.getInt("group_currmem"));
				dto.setGroup_front_img(rs.getString("group_front_img"));
				dto.setGroup_main_img(rs.getString("group_main_img"));
				dto.setGroup_insta(rs.getString("group_insta"));
				dto.setGroup_facebook(rs.getString("group_facebook"));
				dto.setGroup_chatroom(rs.getString("group_chatroom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}		
		return dto;
	}
}
