package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class InterestDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	// 싱글톤 객체 만들기
	private static InterestDAO instance = new InterestDAO();

	private InterestDAO() { }

	public static InterestDAO getInstance() {
		if (instance == null) {
			instance = new InterestDAO();
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
	
	//selectLC(): interest_table에서 대분류를 받아오는 메서드
	public List<InterestDTO> selectLC(){  
		List<InterestDTO> l_category = new ArrayList<InterestDTO>();
		
		try {
			openConn();
			sql="select distinct l_category from interest_table order by l_category";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				InterestDTO dto = new InterestDTO();
				dto.setL_category(rs.getString("l_category"));
				l_category.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return l_category;
	}//selectLC() end;
	
	//selectSC(): interest_table에서 선택한 대분류의 해당 소분류를 받아오는 메서드(대분류 부터 받아와야함)
	public List<InterestDTO> selectSC(String lc){  
		List<InterestDTO> s_category = new ArrayList<InterestDTO>();
		
		try {
			openConn();
			sql="select s_category from interest_table where l_category=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,lc);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				InterestDTO dto = new InterestDTO();
				dto.setS_category(rs.getString("s_category"));
				s_category.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return s_category;
	}//selectSC() end;
}
	
