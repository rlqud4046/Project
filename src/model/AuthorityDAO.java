package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AuthorityDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	// 싱글톤 객체 만들기
	// 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private으로 선언을 한다.
	// 2. 정적 멤버로 선언을 한다. - static으로 선언을 한다.
	private static AuthorityDAO instance = new AuthorityDAO();

	// 3. 기본생성자는 외부에서 접근이 되지 않도록 해야 한다. - private 으로 생성자를 만든다.
	// 외부에서 new를 사용하지 못하게 하는 접근 기법.
	private AuthorityDAO() {
	} // 기본 생성자

	// 4. 생성자 대신에 싱글톤 객체를 return을 해 주는 getInstance() 메서드를 만들어 주자.
	public static AuthorityDAO getInstance() {

		if (instance == null) {
			instance = new AuthorityDAO();
		}
		return instance;
	}

	public Connection openConn() {

		try {

			// 1. JNDI 서버 객체 생성
			InitialContext ic = new InitialContext();

			// 2. lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");

			// 3. DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;

	} // openConn() 메서드 end

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

	} // closeconn() 메서드 end
	
	public AuthorityDTO getAuthorityList(int group_no) {
		
		AuthorityDTO dto = new AuthorityDTO();
		
		try {

			con = openConn();
			sql="SELECT * FROM AUTHORITY_TABLE WHERE GROUP_NO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setR_notice(rs.getInt("r_notice"));
				dto.setW_notice(rs.getInt("w_notice"));
				dto.setR_hi(rs.getInt("r_hi"));
				dto.setW_hi(rs.getInt("w_hi"));
				dto.setR_meet(rs.getInt("r_meet"));
				dto.setW_meet(rs.getInt("w_meet"));
				dto.setR_free(rs.getInt("r_free"));
				dto.setW_free(rs.getInt("w_free"));
				dto.setR_photo(rs.getInt("r_photo"));
				dto.setW_photo(rs.getInt("w_photo"));
				dto.setAuthority_important(rs.getInt("authority_important"));
				dto.setAuthority_delegation(rs.getInt("authority_delegation"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
		
	}
	
	public int authorityUpdate(int group_no, int rno,int wno,int rhi,int whi,int rme,int wme,int rfr,int wfr,int rpho,int wpho) {
		
		int result = 0;
		
		try {

			con = openConn();
			sql = "UPDATE AUTHORITY_TABLE SET R_NOTICE = ?,W_NOTICE = ?, R_HI = ?, W_HI = ?, R_MEET = ?, W_MEET = ?,"
					+ " R_FREE = ?, W_FREE = ?, R_PHOTO = ?, W_PHOTO = ? WHERE GROUP_NO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rno);
			pstmt.setInt(2, wno);
			pstmt.setInt(3, rhi);
			pstmt.setInt(4, whi);
			pstmt.setInt(5, rme);
			pstmt.setInt(6, wme);
			pstmt.setInt(7, rfr);
			pstmt.setInt(8, wfr);
			pstmt.setInt(9, rpho);
			pstmt.setInt(10, wpho);
			pstmt.setInt(11, group_no);
			
			result = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
