package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.JoinDTO;
import model.LikeDAO;

public class LikeBoardSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String field = request.getParameter("find_field");
		String name = request.getParameter("find_name").trim();
		int mem_no = Integer.parseInt(request.getParameter("mem_no"));

		// 페이징 처리 기법
		int rowsize = 3; // 한 페이지당 보여질 게시물의 수
		int block = 3; // 아래에 보여질 페이지의 최대 수 - 예) [1][2][3] / [4][5][6]
		int totalRecord = 0; // DB상의 레코드 전체 수(게시글의 수)
		int allPage = 0; // 전체 페이지 수 - totalRecord / rowsize

		int page = 1; // 현재 페이지 번호

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// 해당 페이지에서의 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);
		// 해당 페이지에서의 끝 번호
		int endNo = (page * rowsize);

		// 해당 페이지의 시작 블럭
		int startBlock = (((page - 1) / block) * block) + 1;
		// 해당 페이지의 끝 블럭
		int endBlock = (((page - 1) / block) * block) + block;

		LikeDAO dao = LikeDAO.getInstance();
		totalRecord = dao.getSearchLikeListCount(field, name, mem_no);

		// 검색된 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나눠주면 된다.
		// 이런 작업을 거치면 전체 페이지 수가 나온다.
		// 전체 페이지가 나올 때 나머지가 있으면 무조건 올림이 되어야 한다.
		allPage = (int) Math.ceil(totalRecord / (double) rowsize);

		if (endBlock > allPage) {
			endBlock = allPage;
		}
		
		List<JoinDTO> list = dao.searchLikeBoardList(field, name, page, rowsize, mem_no);
		
		// 페이징 처리 작업 시 사용했던 모든 값들을 키로 저장해 주자.
		
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("find_field", field);
		request.setAttribute("find_name", name);
		request.setAttribute("searchList", list);

		// view 페이지로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("SearchLikeBoardList.do");

		return forward;
	}

}
