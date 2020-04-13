package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setAttribute("no", request.getParameter("no"));
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board_delete.jsp");
		return forward;
		
		
	}

}
