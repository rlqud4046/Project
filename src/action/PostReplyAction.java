package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostDAO;
import model.PostDTO;

public class PostReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		ActionForward forward = new ActionForward();
		
		int receiver = Integer.parseInt(request.getParameter("sender"));
		int sender = Integer.parseInt(request.getParameter("receiver"));
		System.out.println("값: "+sender+","+receiver);
		
		request.setAttribute("receiver", receiver);
		request.setAttribute("sender", sender);
		
		PostDAO dao = PostDAO.getInstance();
		PostDTO senderInfo = dao.postMemInfo(sender);
		request.setAttribute("senderInfo", senderInfo);
		
		forward.setRedirect(false);
		forward.setPath("post_reply.jsp");
		return forward;
	}

}
