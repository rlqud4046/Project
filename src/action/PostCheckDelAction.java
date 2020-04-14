package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostDAO;

public class PostCheckDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String check=request.getParameter("total_check");
		int mem_no=5;
		String[] check = request.getParameterValues("total_check");
		System.out.println(check[0]);
		
		PostDAO dao = PostDAO.getInstance();
		int res =dao.checkDel(check[0]);
		
		ActionForward forward = new ActionForward();
		
		
		if(res>=1) {
			//삭제성공
			forward.setRedirect(false);
			forward.setPath("post_receive.do?mem_no="+mem_no);
			return forward;
		}else if (res==0) {
			//삭제실패
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제실패했습니당.')");
			out.println("location.href='history.back()'");
			out.println("<script>");
			
		}
		
		return null;
	}

}
