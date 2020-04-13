package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostButtonAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		ActionForward forward = new ActionForward();
		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		
		request.setAttribute("mem_no", mem_no);
		forward.setRedirect(false);
		forward.setPath("post_button.jsp");
		
		return forward;
		
	}

}
