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

   } // closeconn() 메서드 end

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
         }
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         closeConn(rs, pstmt, con);
      }
      return count;

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
   
   public JoinDTO getMemberActivitiesList(int group_no, String id) {
      
      JoinDTO dto = new JoinDTO();
      
      try {
    	  System.out.println("id => " +id+ "group_no => " + group_no);
         con = openConn();
         sql = "SELECT * FROM MLB_JOIN_VIEW WHERE id = ? and group_no = ?";
         pstmt = con.prepareStatement(sql);
         
         pstmt.setString(1, id);
         pstmt.setInt(2, group_no);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            
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
   
   public int totalBoardNo(int group_no, String id ){
      
      int count = 0;
      
      try {

         con = openConn();
         sql="SELECT COUNT(BOARD_NO) FROM MLB_JOIN_VIEW WHERE BOARD_WRITER = ? and group_no = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setInt(2, group_no);
         
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
   
   public int totalReplyNo(int group_no, String id) {
      
      int count = 0;
      
      try {

         con = openConn();
         sql="SELECT COUNT(COMMENT_NO) FROM MLB_JOIN_VIEW WHERE COMMENT_WRITER = ? and group_no = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setInt(2, group_no);
         
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
   
   public List<JoinDTO> getRegisterBoardList(int group_no, String id) {
      
      List<JoinDTO> list = new ArrayList<JoinDTO>();
      
      try {

         con = openConn();
         sql="SELECT * FROM MLB_JOIN_VIEW WHERE BOARD_WRITER = ? and group_no = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setInt(2, group_no);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            
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
         sql="SELECT * FROM MLB_JOIN_VIEW WHERE COMMENT_WRITER = ? and group_no = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setInt(2, group_no);
         
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            
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
}