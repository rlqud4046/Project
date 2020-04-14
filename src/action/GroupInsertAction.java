package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AreaDAO;
import model.AreaDTO;
import model.InterestDAO;
import model.InterestDTO;

public class GroupInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//DB에서 대분류, 소분류를 키로 보내고.. 어디로가야하나......?
	
		
		InterestDAO dao1 = InterestDAO.getInstance();
		List<InterestDTO> l_category = dao1.selectLC();
		
		AreaDAO dao2 = AreaDAO.getInstance();
		List<AreaDTO> city = dao2.selectCity();
		
		
		
		
		
		request.setAttribute("l_category", l_category);
		request.setAttribute("city", city);
		
		String path = "group_insert.jsp";
		request.setAttribute("page", path);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("samplePage.jsp");
		return forward;
		
	}

}
