package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;



public class GroupDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	// 싱글톤 객체 만들기
	// 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private으로 선언을 한다.
	// 2. 정적 멤버로 선언을 한다. - static으로 선언을 한다.
	private static GroupDAO instance = new GroupDAO();

	// 3. 기본생성자는 외부에서 접근이 되지 않도록 해야 한다. - private 으로 생성자를 만든다.
	// 외부에서 new를 사용하지 못하게 하는 접근 기법.
	private GroupDAO() {
	} // 기본 생성자

	// 4. 생성자 대신에 싱글톤 객체를 return을 해 주는 getInstance() 메서드를 만들어 주자.
	public static GroupDAO getInstance() {

		if (instance == null) {
			instance = new GroupDAO();
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

	public List<JoinDTO> getMyGroupList(int mem_no) {

		List<JoinDTO> list = new ArrayList<JoinDTO>();

		try {

			con = openConn();
			sql = "SELECT g.group_no, g.group_name, m.rating FROM membership_table m, group_table g WHERE  m.mem_no = ? AND g.group_no = m.group_no AND rating > 0";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				JoinDTO dto = new JoinDTO();

				dto.setGroup_no(rs.getInt("Group_no"));
				dto.setGroup_name(rs.getString("Group_name"));
				dto.setRating(rs.getInt("Rating"));

				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return list;

	} // getMyGroupList 메서드 end

	public List<JoinDTO> SearchAllGroupList(String area, String interest){
		
		List<JoinDTO> list = new ArrayList<JoinDTO>();
		
		if(area != "" && interest != "") {
			
			try {

				con = openConn();
				sql="select * from AIG_JOIN_VIEW where city = ? and L_CATEGORY = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, area);
				pstmt.setString(2, interest);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					 JoinDTO dto = new JoinDTO();
			    	 dto.setGroup_no(rs.getInt("group_no"));
			    	 dto.setGroup_name(rs.getString("group_name"));
			    	 dto.setGroup_intro(rs.getString("group_intro"));
			    	 dto.setGroup_area(rs.getInt("group_area"));
			    	 dto.setGroup_interest(rs.getInt("group_interest"));
			    	 dto.setGroup_currmem(rs.getInt("group_currmem"));
			    	 dto.setGroup_main_img(rs.getString("group_main_img"));
			    	 dto.setArea_no(rs.getInt("area_no"));
			    	 dto.setCity(rs.getString("city"));
			    	 dto.setTown(rs.getString("town"));
			    	 dto.setInterest_no(rs.getInt("interest_no"));
			    	 dto.setS_category(rs.getString("s_category"));
			    	 dto.setL_category(rs.getString("l_category"));
			    	 list.add(dto);
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			
		}else if(area != "" && interest == "") {
			try {

				con = openConn();
				sql="select * from AIG_JOIN_VIEW where city = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, area);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					 JoinDTO dto = new JoinDTO();
			    	 dto.setGroup_no(rs.getInt("group_no"));
			    	 dto.setGroup_name(rs.getString("group_name"));
			    	 dto.setGroup_intro(rs.getString("group_intro"));
			    	 dto.setGroup_area(rs.getInt("group_area"));
			    	 dto.setGroup_interest(rs.getInt("group_interest"));
			    	 dto.setGroup_currmem(rs.getInt("group_currmem"));
			    	 dto.setGroup_main_img(rs.getString("group_main_img"));
			    	 dto.setArea_no(rs.getInt("area_no"));
			    	 dto.setCity(rs.getString("city"));
			    	 dto.setTown(rs.getString("town"));
			    	 dto.setInterest_no(rs.getInt("interest_no"));
			    	 dto.setS_category(rs.getString("s_category"));
			    	 dto.setL_category(rs.getString("l_category"));
			    	 list.add(dto);
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
		}else if(area == "" && interest != "") {
			try {

				con = openConn();
				sql="select * from AIG_JOIN_VIEW where L_CATEGORY = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, interest);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					
					 JoinDTO dto = new JoinDTO();
			    	 dto.setGroup_no(rs.getInt("group_no"));
			    	 dto.setGroup_name(rs.getString("group_name"));
			    	 dto.setGroup_intro(rs.getString("group_intro"));
			    	 dto.setGroup_area(rs.getInt("group_area"));
			    	 dto.setGroup_interest(rs.getInt("group_interest"));
			    	 dto.setGroup_currmem(rs.getInt("group_currmem"));
			    	 dto.setGroup_main_img(rs.getString("group_main_img"));
			    	 dto.setArea_no(rs.getInt("area_no"));
			    	 dto.setCity(rs.getString("city"));
			    	 dto.setTown(rs.getString("town"));
			    	 dto.setInterest_no(rs.getInt("interest_no"));
			    	 dto.setS_category(rs.getString("s_category"));
			    	 dto.setL_category(rs.getString("l_category"));
			    	 list.add(dto);
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
		}
		return list;
	}
	

	public List<JoinDTO> getBoardList() {

		List<JoinDTO> list = new ArrayList<JoinDTO>();

		try {

			con = openConn();

			sql = "select * from AIG_JOIN_VIEW";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				JoinDTO dto = new JoinDTO();
				dto.setGroup_main_img(rs.getString("group_main_img"));
				dto.setGroup_name(rs.getString("group_name"));
				dto.setGroup_interest(rs.getInt("group_interest"));
				dto.setGroup_area(rs.getInt("group_area"));
				dto.setGroup_currmem(rs.getInt("group_currmem"));
				dto.setGroup_intro(rs.getString("group_intro"));
				dto.setL_category(rs.getString("l_category"));
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
	

	public GroupDTO getGroupInfor(int group_no) {
		
		GroupDTO dto = new GroupDTO();
		
		try {

			con = openConn();
			sql="SELECT * FROM GROUP_TABLE WHERE GROUP_NO = ?";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}

	public int pwdcheck(String pwd,int mem_no) {
		
		int res = 0;
		
		try {

			con = openConn();
			sql="select * from MSG_JOIN_VIEW where mem_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pwd.equals(rs.getString("pwd")) && rs.getInt("rating") == 5 ) {
					res = 1 ;  // 일치
				}else {
					res = -1;  // 비번 틀림 또는 모임장이 아님 
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return res;
	}

	public int deleteGroup(int group_no) {
		
		int result = 0;
		
		try {

			con = openConn();
			sql="delete from group_table where group_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}

	public int getpremium_no(int group_no) {
		
		int count = 0;
		
		try {

			con = openConn();
			sql = "select group_premium from group_table where group_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return count;
		
	}
	
	public int changGroupPremium(int group_no) {
		
		int result = 0;
		
		try {
			con = openConn();
			sql="update group_table set group_premium=1 , group_limitmem=400 where group_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}

	public int releaseGroupPremium(int group_no) {
		
		int result = 0;
		
		try {
			con = openConn();
			sql="update group_table set group_premium=0 , group_limitmem=200 where group_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}
	
	
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

