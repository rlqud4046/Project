package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class InterestDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	// 싱글톤 객체 만들기
	// 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private으로 선언 한다
	// 2. 정적 멤버로 선언한다
	private static InterestDAO instance = new InterestDAO();
	
	// 3.기본 생성자는 외부에서 접근이 되지 않도록 해야한다 - private 으로 생성자를 만듬
	// 외부에서 new를 사용하지 못하게 하는 접근기법
	private InterestDAO() {}
	
	// 4. 생성자 대신 싱글톤 객체를 return 해주는 getInstance() 메서드를 만들어 주자
	public static  InterestDAO getInstance() {
		if(instance == null) {
			instance = new InterestDAO();
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
	
	// 회원가입폼에 관심사 대분류를 출력하기 위한 데이터를 가져온다
	public List<String> getLCategory() {
		List<String> l_category = new ArrayList<String>();
		try {
			openConn();
			sql = "SELECT DISTINCT l_category FROM interest_table ORDER BY l_category";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				l_category.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return l_category;
	}	// getLCategory() end
	
	// 회원가입폼에 관심사 소분류를 출력하기 위한 데이터를 가져온다
	public List<List<InterestDTO>> getInterestList(List<String> lCategorys){
		List<List<InterestDTO>> allCate = new ArrayList<List<InterestDTO>>();
		
		openConn();
		try {
			for(String k : lCategorys) {
				List<InterestDTO> cate = new ArrayList<InterestDTO>();
				sql = "SELECT * FROM interest_table WHERE l_category = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, k);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					InterestDTO dto = new InterestDTO();
					dto.setL_category(rs.getString("l_category"));
					dto.setS_category(rs.getString("s_category"));
					dto.setInterest_no(rs.getInt("interest_no"));
					cate.add(dto);
				}
				allCate.add(cate);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return allCate;
	}	// getInterestList() end

}
