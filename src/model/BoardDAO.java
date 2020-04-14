package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;

	private static BoardDAO instance = new BoardDAO();

	private BoardDAO() {
	}

	// 4. 생성자 대신에 싱글톤 객체를 retrun 해 주는 getInstance() 메서드를 만들어 준다.
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}

	public Connection openConn() {

		try {
			InitialContext ic = new InitialContext();

			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");

			con = ds.getConnection();
		} catch (Exception e) {
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

		}
	} // closeConn() 메서드 끝

	// board 테이블의 전체 게시물의 수를 조회하는 메서드
	public int getListCount(int group_no,int board_category) {
		int count = 0;

		try {
			openConn();
			sql = "select count(*) from board_table where group_no=? and board_category=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, group_no);
			pstmt.setInt(2, board_category);
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

	// board1 테이블에서 게시물을 가져오는 메서드
	public List<BoardDTO> getBoardList(int group_no, int board_category) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		
		try {
			openConn();
			// row_number() over : 특정 컬럼을 기준으로 행 번호를 부여할 때 사용하는 함수
			// 1. row_number() over(order by board_no desc) rnum : board_no를 내림차수로 정렬한 결과를
			// 순차적으로 rnum으로 번호를 저장
			// 2. (select p.*, 위의 1번 from board1 p : board1 테이블을 p로 별칭을 주고 거기에서 board1에 위의
			// 1번을 추가로 뽑아온다
			// 3. where rnum>=? and rnum<=? : 각각 startNo, endNo
			sql = "select * from board_table where group_no=? and board_category=? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, group_no);
				pstmt.setInt(2, board_category);
			/*	pstmt.setInt(3, startNo);
				pstmt.setInt(4, endNo);
*/
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setMgn_no(rs.getInt("mgn_no"));
				dto.setGroup_no(rs.getInt("group_no"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_category(rs.getInt("board_category"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_like(rs.getInt("board_like"));
				dto.setBoard_photo(rs.getString("board_photo"));
				dto.setBoard_file(rs.getString("board_file"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));
				dto.setBoard_imp(rs.getInt("board_imp"));

				list.add(dto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return list;
	} // getBoardList() 메서드 end
	
	
	
	public void boardHit(int no) {

		try {
			openConn();
			sql = "update board_table set board_hit = board_hit + 1 where mgn_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

	} // boardHit() 메서드 end

	// board1 테이블의 게시물 번호에 해당하는 글을 상세 조회하는 메서드
	public BoardDTO boardCont(int no) {

		BoardDTO dto = new BoardDTO();

		try {
			openConn();
			sql = "select * from board_table where mgn_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setMgn_no(rs.getInt("mgn_no"));
				dto.setGroup_no(rs.getInt("group_no"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_category(rs.getInt("board_category"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_like(rs.getInt("board_like"));
				dto.setBoard_photo(rs.getString("board_photo"));
				dto.setBoard_file(rs.getString("board_file"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));
				dto.setBoard_imp(rs.getInt("board_imp"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return dto;
	} // getCont() 메서드 end
	
	public List<String> attachList(int no) {
		
		String urls = null;
		List<String> attachList = new ArrayList<String>();
		
		
		try {
		openConn();
		sql = "select board_file from board_table where mgn_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				urls=rs.getString(1);
			}
			StringTokenizer st = new StringTokenizer(urls,",");
			String[] url = new String[st.countTokens()];
			int i=0;
			while(st.hasMoreElements()) {
				url[i++]=st.nextToken();
			}
			for(i=0;i<url.length;i++)
			attachList.add(url[i]);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return attachList;
	}
	
	public int insertBoard(BoardDTO dto) {

	      int result = 0;

	      try {
	         con = openConn();
	         sql = "insert into board_table values(1,5,7,5, ? , ? ,'BOARD_WRITER 57',sysdate,0,0, 'PHOTO', ? ,1,0,0, ? )";
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, dto.getBoard_title());
	         pstmt.setString(2, dto.getBoard_cont());
	         pstmt.setString(3, dto.getBoard_file());
	         pstmt.setInt(4, dto.getBoard_imp());

	         result = pstmt.executeUpdate();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         closeConn(rs, pstmt, con);
	      }
	      return result;
	   }
	
	
	public void likeIncrease(int mgn_no) {

		try {
			openConn();
			sql = "update board_table set board_like = board_like + 1 where mgn_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mgn_no);
			pstmt.executeUpdate();
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

	} 
	
	public void likeDecrease(int mem_no, int mgn_no) {

		try {
			openConn();
			sql = "update board_table set board_like = board_like - 1 where mgn_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mgn_no);
			pstmt.executeUpdate();
			
			sql ="delete like_table where mem_no=? and mgn_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, mgn_no);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

	} 
	
	public int boardDelete(int no, String pwd) {
		int result = 0;
		String pcheck = null;

		try {
			openConn();
			con.setAutoCommit(false);
			sql = "select pwd from member_table m, board_table b where b.mgn_no=? and m.id = b.board_writer";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pcheck = rs.getString("pwd");
			}

			if (pcheck.equals(pwd)) {
				sql = "delete from board_table where mgn_no=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				result = pstmt.executeUpdate();
				con.commit();
			}
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return result;
	}
	
	

}
