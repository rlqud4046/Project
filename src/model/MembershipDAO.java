package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MembershipDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	// 싱글톤 객체 만들기
	// 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private으로 선언을 한다.
	// 2. 정적 멤버로 선언을 한다. - static으로 선언을 한다.
	private static MembershipDAO instance = new MembershipDAO();
	
	// 3. 기본생성자는 외부에서 접근이 되지 않도록 해야 한다. - private 으로 생성자를 만든다.
	//    외부에서 new를 사용하지 못하게 하는 접근 기법.
	private MembershipDAO() {}  // 기본 생성자
	
	// 4. 생성자 대신에 싱글톤 객체를 return을 해 주는 getInstance() 메서드를 만들어 주자.
	public static MembershipDAO getInstance() {
		
		if(instance == null) {
			instance = new MembershipDAO();
		}
		return instance;
	}
	
	public Connection openConn() {
		
		try {
			
			// 1. JNDI 서버 객체 생성
			InitialContext ic = new InitialContext();
			
			// 2. lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/myoracle");
			
			// 3. DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}  // openConn() 메서드 end
	
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}  // closeconn() 메서드 end
	
	public int groupTal(int mem_no, int group_no) {
		
		int result = 0;
		
		try {

			con = openConn();
			sql = "delete membership_table where mem_no=? and group_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, group_no);
			
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
