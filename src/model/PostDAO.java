package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PostDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	// 싱글톤 객체 만들기
	private static PostDAO instance = new PostDAO();

	private PostDAO() {
	}

	public static PostDAO getInstance() {
		if (instance == null) {
			instance = new PostDAO();
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

	// post_table에서 받은 쪽지 +닉네임,id를 받아오는 메서드
	public List<PostDTO> receive(int mem_no) {
		List<PostDTO> list = new ArrayList<PostDTO>();

		try {
			openConn();
			sql = "select * from post_table p, member_table m where p.sender=? and m.mem_no=p.receiver";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDTO dto = new PostDTO();
				dto.setPost_no(rs.getInt("post_no"));
				dto.setReceiver(rs.getInt("receiver"));
				dto.setSender(rs.getInt("sender"));
				dto.setPost_cont(rs.getString("post_cont").substring(0, 4) + "...");
				dto.setPost_date(rs.getString("post_date"));
				dto.setPost_read(rs.getInt("post_read"));

				dto.setId(rs.getString("id").substring(0, 3) + "...");
				dto.setNickname(rs.getString("nickname"));

				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}// receive() end;

	// 받은쪽지의 상세보기를 가져오는 메서드
	public PostDTO postRCont(int post_no) {
		PostDTO dto = new PostDTO();

		try {
			openConn();
			sql = "select * from post_table p, member_table m where p.post_no=? and p.receiver=m.mem_no";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, post_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setReceiver(rs.getInt("receiver"));
				dto.setSender(rs.getInt("sender"));
				dto.setPost_read(rs.getInt("post_read"));
				dto.setPost_cont(rs.getString("post_cont"));
				dto.setPost_date(rs.getString("post_date"));
				dto.setPost_no(rs.getInt("post_no"));

			}

			sql = "update post_table set post_read=0 where post_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_no);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}// post_cont end();

	// mem_no에 해당하는 닉네임, 아이디를 받아오는 메서드
	public PostDTO postMemInfo(int mem_no) {
		PostDTO dto = new PostDTO();

		try {
			openConn();
			sql = "select id,nickname from member_table where mem_no=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}// postMemInfo() end;
	

	// 답장을 DB에 보내는 메서드
	public int replyOk(int sender, int receiver, String post) {
		int result = 0;

		try {
			openConn();
			sql = "insert into post_table (sender,receiver,post_date,post_cont,post_read) values(?,?,sysdate,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sender);
			pstmt.setInt(2, receiver);
			pstmt.setString(3, post);
			pstmt.setInt(4, 1);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return result;
	}// replyOk(sender,receiver,post) end;

	// post_table에서 보낸 쪽지+아이디,닉네임를 받아오는 메서드
	public List<PostDTO> send(int mem_no) {
		List<PostDTO> list = new ArrayList<PostDTO>();

		String cont = null;
		try {
			openConn();
			sql = "select * from post_table p, member_table m where p.sender=? and m.mem_no = p.receiver";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PostDTO dto = new PostDTO();
				dto.setPost_no(rs.getInt("post_no"));
				dto.setReceiver(rs.getInt("receiver"));
				dto.setSender(rs.getInt("sender"));

				if (rs.getString("post_cont").length() >= 5) {
					cont = rs.getString("post_cont").substring(0, 4) + "...";
				} else {
					cont = rs.getString("post_cont");
				}
				dto.setPost_cont(cont);
				dto.setPost_date(rs.getString("post_date"));
				dto.setPost_read(rs.getInt("post_read"));
				dto.setId(rs.getString("id").substring(0, 3) + "...");
				dto.setNickname(rs.getString("nickname"));

				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return list;
	}// send() end;

	// 보낸쪽지의 상세보기를 가져오는 메서드
	public PostDTO postSCont(int post_no) {
		PostDTO dto = new PostDTO();

		try {
			openConn();
			sql = "select * from post_table p, member_table m where p.post_no=? and p.sender=m.mem_no";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, post_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setReceiver(rs.getInt("receiver"));
				dto.setSender(rs.getInt("sender"));
				dto.setPost_read(rs.getInt("post_read"));
				dto.setPost_cont(rs.getString("post_cont"));
				dto.setPost_date(rs.getString("post_date"));
				dto.setPost_no(rs.getInt("post_no"));

			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
		
	}//postSCont() end;
	
	// 쪽지를 보낼 아이디가 DB상에 존재하는지 확인하는 메서드,존재하면 mem_no를 받아온다
	public int searchID(String receiverID) {
		int receiver=0;
		try {
			openConn();
			sql="select mem_no from member_table where id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, receiverID);
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				receiver=rs.getInt("mem_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		
		return receiver;
	}//searchID() end;
	
	//보낸쪽지를 DB에 저장하는 메서드
	public int WriteOk(int receiver,int sender,String post_cont) {
		int result=0;
		
		try {
			openConn();
			sql="insert into post_table (sender,receiver,post_date,post_cont,post_read) values(?,?,sysdate,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sender);
			pstmt.setInt(2, receiver);
			pstmt.setString(3, post_cont);
			pstmt.setInt(4, 1);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}// WriteOk() End;
	
	public int PostDel(int post_no) {
		int res=0;
		
		try {
			openConn();
			sql="delete from post_table where post_no=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_no);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return res;
		
	}//postDel() end;
}
