package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	// 싱글톤 객체 만들기
	// 1. 싱글톤 객체를 만들때는 우선적으로 접근지정자는 private으로 선언을 한다.
	// 2. 정적 멤버로 선언을 한다. - static으로 선언을 한다.
	private static MemberDAO instance = new MemberDAO();

	// 3. 기본생성자는 외부에서 접근이 되지 않도록 해야 한다. - private 으로 생성자를 만든다.
	// 외부에서 new를 사용하지 못하게 하는 접근 기법.
	private MemberDAO() {
	} // 기본 생성자

	// 4. 생성자 대신에 싱글톤 객체를 return을 해 주는 getInstance() 메서드를 만들어 주자.
	public static MemberDAO getInstance() {

		if (instance == null) {
			instance = new MemberDAO();
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

	} // closeConn(rs, pstmt, con) 메서드 end

	public int userCheck(String id, String pwd) {

		int result = 0; // 회원 여부를 저장할 변수

		try {

			con = openConn();
			sql = "select * from member_table where id = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (pwd.equals(rs.getString("pwd"))) {
					// 현재 활동중인 회원인 경우
					result = 1;
				} else {
					// 비밀번호가 틀린경우
					result = -1;
				}
			} else {
				// 회원이 아닌 경우 이거나 탈퇴한 경우
				result = -2;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		}

		return result;

	}

	public MemberDTO getMember(String id) {

		MemberDTO dto = new MemberDTO();

		try {

			con = openConn();
			sql = "select * from member_table where id = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				dto.setMem_no(rs.getInt("mem_no"));
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setMem_name(rs.getString("mem_name"));
				dto.setNickname(rs.getString("nickname"));
				dto.setProfile_img(rs.getString("profile_img"));
				dto.setBirth(rs.getString("birth"));
				dto.setE_mail(rs.getString("e_mail"));
				dto.setPhone(rs.getString("phone"));
				dto.setCheck_q(rs.getString("check_q"));
				dto.setCheck_a(rs.getString("check_a"));
				dto.setInterests(rs.getString("interests"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		}

		return dto;
	}

	public MemberDTO getMemberInfo(String name, String email) {

		MemberDTO dto = new MemberDTO();
		try {

			con = openConn();
			sql = "select * from member_table where mem_name = ? and e_mail = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto.setMem_no(rs.getInt("mem_no"));
				dto.setId(rs.getString("id"));
				dto.setE_mail(rs.getString("e_mail"));

			} else {
				dto = null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return dto;
	}

	public void findId(String name, String email, MemberDTO dto) {

		String host = "smtp.naver.com";
		final String user = "";
		final String password = "";

		String to = email;

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.enable.ssl", "true");
		props.put("mail.user", "");
		props.put("mail.password", "");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.naver.com");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("", "");
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Subject
			message.setSubject("아이디를 발송해 드립니다.");

			// Text
			message.setText(name + "님의 아이디 는   [" + dto.getId() + "]   입니다.");

			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int infoOk(String id, String q, String a) {

		int count = 0;

		try {

			con = openConn();
			sql = "select count(*) from member_table where id = ? and check_q = ? and check_a = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, q);
			pstmt.setString(3, a);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			} else {
				count = -1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return count;

	}

	public void findpwd(MemberDTO dto, String pwd) {

		String host = "smtp.naver.com";
		final String user = "";
		final String password = "";

		String to = dto.getE_mail();

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.enable.ssl", "true");
		props.put("mail.user", "");
		props.put("mail.password", "");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.naver.com");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("", "");
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Subject
			message.setSubject("임시 비밀번호를 발송해 드립니다.");

			// Text
			message.setText(dto.getMem_name() + "님의 임시 비빌번호는   [" + pwd + "]   입니다.");

			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatepwd(String pwd, String id) {
		int res = 0;
		try {
			con = openConn();
			sql = "update member_table set pwd=? where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, id);

			res = pstmt.executeUpdate();
			System.out.println(res);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

	}

	// 회원정보 수정시 회원정보를 불러오는 메서드
	public MemberDTO selectMember(String id) {
		MemberDTO dto = new MemberDTO();
		try {
			openConn();
			sql = "SELECT * FROM member_table WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
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
			closeConn(rs, pstmt, con);
		}
		return dto;
	}

	public List<String> getInterest() {
		List<String> interestList = new ArrayList<String>();
		try {
			openConn();
			sql = "SELECT DISTINCT l_category FROM interest_table";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				interestList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
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
			while (rs.next()) {
				cityList.add(rs.getString(1).trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return cityList;
	}

	// 구/군 목록을 가져오는 메서드
	public List<String> getTown(String city) {
		List<String> towns = new ArrayList<String>();

		try {
			openConn();
			sql = "SELECT town FROM area_table WHERE city=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, city);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				towns.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return towns;
	} // getTown() end

	public List<String> getQuestion() {
		List<String> qList = new ArrayList<String>();
		try {
			openConn();
			sql = "SELECT question FROM check_q";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				qList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return qList;
	}

	public int checkId(String id) {
		int result = 0;

		try {
			openConn();
			sql = "SELECT * FROM member_table WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}

	public int checkNickname(String nickname) {
		int result = 0;
		try {
			openConn();
			sql = "SELECT * FROM member_table WHERE nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}

	public int insertMemberTable(MemberDTO dto) {
		int result = 0;

		try {
			openConn();
			con.setAutoCommit(false);
			sql = "INSERT INTO member_table "
					+ "(id, pwd, mem_name, nickname, profile_img, birth, e_mail, phone, check_q, check_a, city, area1, area2, area3, interests) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getMem_name());
			pstmt.setString(4, dto.getNickname());
			pstmt.setString(5, dto.getProfile_img());
			pstmt.setString(6, dto.getBirth());
			pstmt.setString(7, dto.getE_mail());
			pstmt.setString(8, dto.getPhone());
			pstmt.setString(9, dto.getCheck_q());
			pstmt.setString(10, dto.getCheck_a());
			pstmt.setString(11, dto.getCity());
			pstmt.setString(12, dto.getArea1());
			pstmt.setString(13, dto.getArea2());
			pstmt.setString(14, dto.getArea3());
			pstmt.setString(15, dto.getInterests());
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
			closeConn(rs, pstmt, con);
		}

		return result;
	}

	// 회원정보를 수정하는 메서드
	public int updateMemberInfo(MemberDTO dto) {
		int result = 0;
		try {
			openConn();
			con.setAutoCommit(false);
			sql = "UPDATE member_table "
					+ "SET pwd=?, mem_name=?, nickname=?, profile_img=?, birth=?, e_mail=?, phone=?, check_q=?, check_a=?, "
					+ "city=?, area1=?, area2=?, area3=?, interests=? " + "WHERE id=?";
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
			closeConn(rs, pstmt, con);
		}
		return result;
	}

	public int pwdCheck(String id, String pwd) {
		int result = 0;
		openConn();
		sql = "SELECT * FROM member_table WHERE id=? AND pwd=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}

	public JoinDTO getMemberActivitiesList(int group_no, String id) {

		JoinDTO dto = new JoinDTO();

		try {
			System.out.println("id => " + id + "group_no => " + group_no);
			con = openConn();
			sql = "SELECT * FROM MLB_JOIN_VIEW WHERE id = ? and group_no = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setInt(2, group_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto.setMgn_no(rs.getInt("mgn_no"));
				dto.setMem_no(rs.getInt("mem_no"));
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setMem_name(rs.getString("mem_name"));
				dto.setNickname(rs.getString("nickname"));
				dto.setProfile_img(rs.getString("profile_img"));
				dto.setComment_no(rs.getInt("comment_no"));
				dto.setComment_writer(rs.getString("comment_writer"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_writer(rs.getString("board_writer"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}

	public int totalBoardNo(int group_no, String id) {

		int count = 0;

		try {

			con = openConn();
			sql = "SELECT COUNT(BOARD_NO) FROM MLB_JOIN_VIEW WHERE BOARD_WRITER = ? and group_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, group_no);

			rs = pstmt.executeQuery();

			if (rs.next()) {

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

	public int totalReplyNo(int group_no, String id) {

		int count = 0;

		try {

			con = openConn();
			sql = "SELECT COUNT(COMMENT_NO) FROM MLB_JOIN_VIEW WHERE COMMENT_WRITER = ? and group_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, group_no);

			rs = pstmt.executeQuery();

			if (rs.next()) {

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

	public List<JoinDTO> getRegisterBoardList(int group_no, String id) {

		List<JoinDTO> list = new ArrayList<JoinDTO>();

		try {

			con = openConn();
			sql = "SELECT * FROM MLB_JOIN_VIEW WHERE BOARD_WRITER = ? and group_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, group_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				JoinDTO dto = new JoinDTO();
				dto.setMgn_no(rs.getInt("mgn_no"));
				dto.setMem_no(rs.getInt("mem_no"));
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setMem_name(rs.getString("mem_name"));
				dto.setNickname(rs.getString("nickname"));
				dto.setProfile_img(rs.getString("profile_img"));
				dto.setComment_no(rs.getInt("comment_no"));
				dto.setComment_writer(rs.getString("comment_writer"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_writer(rs.getString("board_writer"));

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

	public List<JoinDTO> getReplyList(int group_no, String id) {

		List<JoinDTO> list = new ArrayList<JoinDTO>();

		try {

			con = openConn();
			sql = "SELECT * FROM MLB_JOIN_VIEW WHERE COMMENT_WRITER = ? and group_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, group_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				JoinDTO dto = new JoinDTO();
				dto.setMgn_no(rs.getInt("mgn_no"));
				dto.setMem_no(rs.getInt("mem_no"));
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setMem_name(rs.getString("mem_name"));
				dto.setNickname(rs.getString("nickname"));
				dto.setProfile_img(rs.getString("profile_img"));
				dto.setComment_no(rs.getInt("comment_no"));
				dto.setComment_writer(rs.getString("comment_writer"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_writer(rs.getString("board_writer"));

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

	public List<MemberDTO> likeList(List<LikeDTO> likes) {

		MemberDTO dto = new MemberDTO();
		List<MemberDTO> list = new ArrayList<>();
		LikeDTO ldto = new LikeDTO();
		String distinct = "0";
		try {

			con = openConn();
			if(!likes.isEmpty()) {
			for (int i = 0; i < likes.size(); i++) {
				
				int str = likes.get(i).getMem_no();
				distinct = distinct + str + ",";
			}
			distinct = distinct.substring(1, distinct.length() - 1);
			System.out.println(distinct);
			}

			sql = "select distinct id from member_table where mem_no in("+distinct+")";
			pstmt = con.prepareStatement(sql);
			/* ldto.setMem_no(Integer.parseInt(likes. get(i).get("")toString())); */

			rs = pstmt.executeQuery();

			while (rs.next()) {

				dto.setId(rs.getString("id"));
				list.add(dto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
		}

		return list;
	}


}
