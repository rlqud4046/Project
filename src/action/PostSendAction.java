package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostDAO;
import model.PostDTO;

public class PostSendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int mem_no =Integer.parseInt(request.getParameter("mem_no"));
		
		ActionForward forward = new ActionForward();
		
		PostDAO dao = PostDAO.getInstance();
		List<PostDTO> send_list =dao.send(mem_no);
		
		request.setAttribute("send_list", send_list);
		
		
		forward.setRedirect(false);
		forward.setPath("post_send.jsp");
		
		return forward;
	}

}
