package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	// 싱글톤 객체 만들기
	// 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private으로 선언 한다
	// 2. 정적 멤버로 선언한다
	private static MemberDAO instance = new MemberDAO();
	
	// 3.기본 생성자는 외부에서 접근이 되지 않도록 해야한다 - private 으로 생성자를 만듬
	// 외부에서 new를 사용하지 못하게 하는 접근기법
	private MemberDAO() {}
	
	// 4. 생성자 대신 싱글톤 객체를 return 해주는 getInstance() 메서드를 만들어 주자
	public static  MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
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
	
	public List<String> getInterest() {
		List<String> interestList = new ArrayList<String>();
		try {
			openConn();
			sql = "SELECT DISTINCT l_category FROM interest_table";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				interestList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return interestList;
	}
	public List<String> getCity() {
		List<String> cityList = new ArrayList<String>();
		try {
			openConn();
			sql = "SELECT DISTINCT city FROM area_table";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cityList.add(rs.getString(1).trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return cityList;
	}
	
	// 구/군 목록을 가져오는 메서드
	public List<String> getTown(String city){
		List<String> towns = new ArrayList<String>();
		
		try {
			openConn();
			sql = "SELECT town FROM area_table WHERE city=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, city);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				towns.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return towns;
	} // getTown() end
	
	public List<String> getQuestion() {
		List<String> qList = new ArrayList<String>();
		try {
			openConn();
			sql = "SELECT question FROM checkq_table";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				qList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return qList;
	}
	
	// 아이디 중복 체크 메서드
	public int checkId(String id) {
		int result = 0;
		
		try {
			openConn();
			sql = "SELECT * FROM member_table WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return result;
	}
	
	// 닉네임 중복여부 확인하는 메서드
	public int checkNickname(String nickname) {
		int result = 0;
		try {
			openConn();
			sql = "SELECT * FROM member_table WHERE nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return result;
	}
	
	// 회원정보를 DB에 저장하는 메서드
	public int insertMemberTable(MemberDTO dto) {
		int result = 0;
		
		try {
			openConn();
			con.setAutoCommit(false);
			sql = "INSERT INTO member_test "
				+ "(id, pwd, mem_name, nickname, profile_img, birth, e_mail, phone, check_q, check_a, city, area1, area2, area3, interests) " 
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId()); 			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getMem_name());		pstmt.setString(4, dto.getNickname());
			pstmt.setString(5, dto.getProfile_img());	pstmt.setString(6, dto.getBirth());
			pstmt.setString(7, dto.getE_mail());		pstmt.setString(8, dto.getPhone());
			pstmt.setString(9, dto.getCheck_q());		pstmt.setString(10, dto.getCheck_a());
			pstmt.setString(11, dto.getCity());
			pstmt.setString(12, dto.getArea1());		pstmt.setString(13, dto.getArea2());
			pstmt.setString(14, dto.getArea3());		pstmt.setString(15, dto.getInterests());
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
	
	// 회원정보 수정시 회원정보를 불러오는 메서드
	public MemberDTO selectMember(String id) {
		MemberDTO dto = new MemberDTO();
		try {
			openConn();
			sql = "SELECT * FROM member_test WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setMem_name(rs.getString("mem_name"));
				dto.setNickname(rs.getString("nickname"));
				dto.setProfile_img(rs.getString("profile_img"));
				dto.setBirth(rs.getString("birth"));
				dto.setE_mail(rs.getString("e_mail"));
				dto.setPhone(rs.getString("phone"));
				dto.setCheck_q(rs.getString("check_q"));
				dto.setCheck_a(rs.getString("check_a"));
				dto.setCity(rs.getString("city"));
				dto.setArea1(rs.getString("area1"));
				dto.setArea2(rs.getString("area2"));
				dto.setArea3(rs.getString("area3"));
				dto.setInterests(rs.getString("interests"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return dto;
	}
	
	// 회원정보를 수정하는 메서드
	public int updateMemberInfo(MemberDTO dto) {
		int result = 0;
		try {
			openConn();
			con.setAutoCommit(false);
			sql = "UPDATE member_test " + 
				  "SET pwd=?, mem_name=?, nickname=?, profile_img=?, birth=?, e_mail=?, phone=?, check_q=?, check_a=?, "
	   		    + "city=?, area1=?, area2=?, area3=?, interests=? " 
			    + "WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getMem_name());
			pstmt.setString(3, dto.getNickname());
			pstmt.setString(4, dto.getProfile_img());
			pstmt.setString(5, dto.getBirth());
			pstmt.setString(6, dto.getE_mail());
			pstmt.setString(7, dto.getPhone());
			pstmt.setString(8, dto.getCheck_q());
			pstmt.setString(9, dto.getCheck_a());
			pstmt.setString(10, dto.getCity());
			pstmt.setString(11, dto.getArea1());
			pstmt.setString(12, dto.getArea2());
			pstmt.setString(13, dto.getArea3());
			pstmt.setString(14, dto.getInterests());
			pstmt.setString(15, dto.getId());
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
}
