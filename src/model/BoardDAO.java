package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		public int getListCount() {
			int count = 0;

			try {
				openConn();
				sql = "select count(*) from board_table";
				pstmt = con.prepareStatement(sql);
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
		public List<BoardDTO> getBoardList(int page, int rowsize) {
			List<BoardDTO> list = new ArrayList<BoardDTO>();

			// 해당 페이지의 시작 번호
			int startNo = (page * rowsize) - (rowsize - 1);
			// 해당 페이지의 끝 번호
			int endNo = page * rowsize;

			try {
				openConn();
				// row_number() over : 특정 컬럼을 기준으로 행 번호를 부여할 때 사용하는 함수
				// 1. row_number() over(order by board_no desc) rnum : board_no를 내림차수로 정렬한 결과를
				// 순차적으로 rnum으로 번호를 저장
				// 2. (select p.*, 위의 1번 from board1 p : board1 테이블을 p로 별칭을 주고 거기에서 board1에 위의
				// 1번을 추가로 뽑아온다
				// 3. where rnum>=? and rnum<=? : 각각 startNo, endNo

				sql = "select * from " + "(select p.*, row_number() " + "over(order by board_no desc) rnum "
						+ "from board_table p) " + "where rnum >=? and rnum<=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startNo);
				pstmt.setInt(2, endNo);
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

}
