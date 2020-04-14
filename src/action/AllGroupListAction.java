package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AreaDAO;
import model.AreaDTO;
import model.GroupDAO;
import model.InterestDAO;
import model.InterestDTO;
import model.JoinDTO;

public class AllGroupListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		AreaDAO areaDao = AreaDAO.getInstance();
		InterestDAO interestDao = InterestDAO.getInstance();
		GroupDAO dao = GroupDAO.getInstance();

		// 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어 주어야 한다.
		// 이 과정을 거치면 전체 페이지 수가 나온다.
		// 전체 페이지가 나올 때 나머지가 있으면 무조건 올려주어야 한다.

		List<JoinDTO> list = dao.getBoardList();
		List<AreaDTO> city = areaDao.getCityList();
		List<InterestDTO> inter = interestDao.getInterList();

		request.setAttribute("List", list);
		request.setAttribute("City", city);
		request.setAttribute("Inter", inter);

		String path = "allGroupList.jsp";
		request.setAttribute("page", path);
		// view page로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("samplePage.jsp"); // ㄴ므ㅔ
		return forward;

	}

}
