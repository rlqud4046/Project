package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int rowsize = 10; // 한 페이지에 보여질 게시글 수
		int block = 10; // 아래에 보여질 페이지의 최대 수 [1][2][3] >누르면 [4][5][6]...
		int totalRecord = 0; // DB상의 레코드 전체 수(게시물의 수)
		int allPage = 0; // 전체 페이지 수

		int page = 0; // 보여줄 페이지

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		} else {
			page = 1; // 처음으로 게시목목록 태그를 클릭한 경우
		}

		// 해당 페이지에서 시작 번호 1페이지에 1,2,3 2페이지에 4,5,6번 글이 있을 때 1페이지의 시작번호=1, 2페이지의 시작번호=4
		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = page * rowsize;

		// 해당 페이지의 시작 블럭
		int startBlock = (((page - 1) / block) * block) + 1; // 1

		int endBlock = (((page - 1) / block) * block) + block; // 10

		BoardDAO dao = BoardDAO.getInstance();
		
		int board_category = Integer.parseInt(request.getParameter("board_category"));
		int group_no=0;
		List<BoardDTO> list;
		if(request.getParameter("group_no")!=null) {
			group_no = Integer.parseInt(request.getParameter("group_no"));
			list = dao.getBoardList(group_no, board_category, page, rowsize);
		}else {
			list = dao.getBoardList(0, board_category, page, rowsize);
		}
		
		totalRecord = dao.getListCount(group_no,board_category);
		/*
		전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어 주어야 한다.
		이 과정을거치면 전체 페이지 수가 나온다. 전체 페이지가 나올 때 나머지가 있으면 무조건 올림(ceil)
		 */
		allPage = (int) Math.ceil(totalRecord/(double)rowsize);
		
		if(endBlock>allPage) {
			endBlock = allPage;
			
		}
		
		
		
		// 페이징 처리 시 사용했던 모든 값들을 키로 저장하자. O친 애들이 주로 사용되고 나머지는 거의 안쓰이기는 한다.
		request.setAttribute("page", page);					// O
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);				// O
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);			// O
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);		// O
		request.setAttribute("endBlock", endBlock);			// O
		request.setAttribute("List", list);					// O
		request.setAttribute("group_no", group_no);					// O
		request.setAttribute("board_category", board_category);					// O
		
		// view page로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board_list.jsp");
		return forward;
	}

}
