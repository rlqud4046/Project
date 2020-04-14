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
		
		GroupDAO dao = GroupDAO.getInstance();
		AreaDAO areaDao = AreaDAO.getInstance();
		InterestDAO interestDao = InterestDAO.getInstance();

	

		List<JoinDTO> list = dao.SearchAllGroupList(area, interest);
		List<AreaDTO> city = areaDao.getCityList();
		List<InterestDTO> inter = interestDao.getInterList();

		request.setAttribute("City", city);
		request.setAttribute("Inter", inter);
		request.setAttribute("List", list);
		request.setAttribute("area", area);
		request.setAttribute("interest", interest);

		// view 페이지로 포워딩
		String path = "searchAllGroupListOk.jsp";
		request.setAttribute("page", path);
		// view page로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("samplePage.jsp"); // ㄴ므ㅔ
		return forward; 
	}

}
