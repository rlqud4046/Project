package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostDAO;

public class PostReplyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 받아온 값들을 DB에 저장
		ActionForward forward = new ActionForward();
		int sender = Integer.parseInt(request.getParameter("sender"));
		int receiver = Integer.parseInt(request.getParameter("receiver"));
		
		String post = request.getParameter("post");
		
		PostDAO dao = PostDAO.getInstance();
		int res=dao.replyOk(sender,receiver,post);
		
		PrintWriter out = response.getWriter();
		
		if(res==1) {
			forward.setRedirect(false);
			forward.setPath("post_send.do?mem_no="+sender);
			return forward;
		}else {
			out.println("<script>");
			out.println("답장보내기 실패");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
		
	}

}
