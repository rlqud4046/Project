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
	private static AreaDAO instance = new AreaDAO();

	private AreaDAO() {
	}

	public static AreaDAO getInstance() {
		if (instance == null) {
			instance = new AreaDAO();
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
