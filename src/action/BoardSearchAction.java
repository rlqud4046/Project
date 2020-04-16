package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardDTO;

public class BoardSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 사진첩 게시물을 검색하는 클래스
		// group_no와 board_category는 request 에서 받아온다
		int group_no = 1;
		int board_category = Integer.parseInt(request.getParameter("category"));
		String keyword = request.getParameter("keyword");
		System.out.println("검색어: "+keyword+", "+"게시판 : "+board_category);

		// 페이지네이션
		int rowSize = 8;
		int block = 5; // 보여질 페이지의 갯수
		int totalRecord = 0;
		int allPage = 0;
		int page = 0;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1;
		}

		// 해당 페이지의 시작/끝 글번호
		int startNo = (page * rowSize) - (rowSize - 1);
		int endNo = page * rowSize;

		// 해당 블럭의 시작 페이지 번호
		int startBlock = ((page - 1) / block) * block + 1;

		// 해당 블럭의 끝 페이지 번호
		int endBlock = ((page - 1) / block) * block + block;

		// 전체 페이지의 수를 구한다
		BoardDAO dao = BoardDAO.getInstance();
		totalRecord = dao.getRowCount(group_no, board_category, keyword);
		allPage = (int) Math.ceil(totalRecord / (double) rowSize);
		System.out.println("totalRecord:"+totalRecord);
		if (endBlock > allPage) {
			endBlock = allPage;
		}

		// 게시물 리스트를 받아온다
		List<BoardDTO> list = dao.getGalleryList(startNo, endNo, group_no, board_category, keyword);

		for(BoardDTO dto : list) {
			System.out.println(dto.toString());
		}
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.setAttribute("rowSize", rowSize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("group_no", group_no);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board_photo.jsp");
		return forward;
	}
}
