package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostDAO;

public class PostDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward forward = new ActionForward();
		
		int post_no = Integer.parseInt(request.getParameter("post_no"));
		int mem_no = Integer.parseInt(request.getParameter("receiver"));
		PostDAO dao = PostDAO.getInstance();
		int result = dao.PostDel(post_no);
		
		if(result==1) {
			forward.setRedirect(false);
			forward.setPath("post_receive.do?mem_no"+mem_no);
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패~')");
			out.println("location.href='history.back()'");
			out.println("</script>");
		}
		return forward;
	}

}
