package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = "mypage.jsp";
		request.setAttribute("page", path);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("samplePage.jsp");
		
		return forward;
	}

}
