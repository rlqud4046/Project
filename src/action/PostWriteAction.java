package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostDAO;
import model.PostDTO;

public class PostWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		ActionForward forward = new ActionForward();
		
		int mem_no =Integer.parseInt(request.getParameter("mem_no"));
		PostDAO dao = PostDAO.getInstance();
		
		request.setAttribute("mem_no", mem_no);
		
		forward.setRedirect(false);
		forward.setPath("post_write.jsp");
		return forward;
	}

}
