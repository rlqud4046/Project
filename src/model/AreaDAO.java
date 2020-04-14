package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AreaDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	// 싱글톤 객체 만들기
	// 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private으로 선언을 한다.
	// 2. 정적 멤버로 선언을 한다. - static으로 선언을 한다.
	private static AreaDAO instance = new AreaDAO();

	// 3. 기본생성자는 외부에서 접근이 되지 않도록 해야 한다. - private 으로 생성자를 만든다.
	// 외부에서 new를 사용하지 못하게 하는 접근 기법.
	private AreaDAO() {
	} // 기본 생성자

	// 4. 생성자 대신에 싱글톤 객체를 return을 해 주는 getInstance() 메서드를 만들어 주자.
	public static AreaDAO getInstance() {

		if (instance == null) {
			instance = new AreaDAO();
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
	
	public List<AreaDTO> getCityList() {
		
		List<AreaDTO> list = new ArrayList<AreaDTO>();
		
		try {
			
			con = openConn();
			sql ="SELECT DISTINCT(city) FROM AREA_TABLE";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				AreaDTO dto = new AreaDTO();
				dto.setCity(rs.getString("city"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}
	
	// selectCity(): area_table에서 대분류를 받아오는 메서드
		public List<AreaDTO> selectCity() {
			List<AreaDTO> city = new ArrayList<AreaDTO>();

			try {
				openConn();
				sql = "SELECT DISTINCT city FROM area_table";

				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					AreaDTO dto = new AreaDTO();
					dto.setCity(rs.getString("city"));
					city.add(dto);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return city;
		}// selectCity() end;
		
		
		// selectCity(): area_table에서 시에 해당하는 구/군을 받아오는 메서드
		public List<AreaDTO> selectTown() {
			List<AreaDTO> town = new ArrayList<AreaDTO>();

			try {
				openConn();
				sql = "SELECT * FROM area_table";

				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					AreaDTO dto = new AreaDTO();
					dto.setCity(rs.getString("city"));
					dto.setTown(rs.getString("town"));
					dto.setArea_no(rs.getInt("area_no"));
					town.add(dto);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return town;
		}// selectCity() end;

		// searchA_no(): DB에서 선택한 지역의 area_no를 가져오는 메서드
		public int searchA_no(String s_c, String s_t) {
			int area_no = 0;

			try {
				openConn();
				sql = "select area_no from area_table where city=? and town=?";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, s_c);
				pstmt.setString(2, s_t);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					area_no = rs.getInt(1);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}

			return area_no;
		}
	
	
	
	
	
	
	
	
	
}
