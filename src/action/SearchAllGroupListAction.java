package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AreaDAO;
import model.AreaDTO;
import model.GroupDAO;
import model.InterestDAO;
import model.InterestDTO;
import model.JoinDTO;

public class SearchAllGroupListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String area = request.getParameter("area");
		String interest = request.getParameter("interest");

		System.out.println(area);
		System.out.println(interest);
		
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

		GroupDAO dao = GroupDAO.getInstance();
		AreaDAO areaDao = AreaDAO.getInstance();
		InterestDAO interestDao = InterestDAO.getInstance();
		totalRecord = dao.getSearchAllGroupListCount(area, interest);

		allPage = (int) Math.ceil(totalRecord / (double) rowsize);

		if (endBlock > allPage) {
			endBlock = allPage;
		}

		List<JoinDTO> list = dao.SearchAllGroupList(area, interest, page, rowsize);
		List<AreaDTO> city = areaDao.getCityList();
		List<InterestDTO> inter = interestDao.getInterList();

		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("City", city);
		request.setAttribute("Inter", inter);
		request.setAttribute("List", list);
		request.setAttribute("area", area);
		request.setAttribute("interest", interest);

		// view 페이지로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("searchAllGroupListOk.do");

		return forward;
	}

}
