package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.PostDAO;

public class PostCheckDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String check=request.getParameter("total_check");
		HttpSession session = request.getSession();
		int mem_no= (int) session.getAttribute("mem_no");
		String[] check = request.getParameterValues("total_check");
	      int post_page = Integer.parseInt(request.getParameter("post_page"));

	      // post_page=1인 경우: 받은쪽지함에서 check된 리스트 삭제
	      // post_page=2인 경우: 보낸쪽지함에서 check된 리스트 삭제

	      if (post_page == 1) {//받은쪽지함
	         System.out.println(check[0]);

	         PostDAO dao = PostDAO.getInstance();
	         int res = dao.checkRDel(check[0]);

	         ActionForward forward = new ActionForward();

	         if (res >= 1) {
	            // 삭제성공
	            forward.setRedirect(false);
	            forward.setPath("post_receive.do?mem_no="+mem_no);
	            return forward;
	         } else if (res == 0) {
	            // 삭제실패
	            PrintWriter out = response.getWriter();
	            out.println("<script>");
	            out.println("alert('삭제실패했습니당.')");
	            out.println("location.href='history.back()'");
	            out.println("<script>");

	         }
	      
	      }else if(post_page == 2) {//보낸쪽지함
	         System.out.println(check[0]);

	         PostDAO dao = PostDAO.getInstance();
	         int res = dao.checkSDel(check[0]);

	         ActionForward forward = new ActionForward();

	         if (res >= 1) {
	            // 삭제성공
	            forward.setRedirect(false);
	            forward.setPath("post_send.do?mem_no="+mem_no);
	            return forward;
	         } else if (res == 0) {
	            // 삭제실패
	            PrintWriter out = response.getWriter();
	            out.println("<script>");
	            out.println("alert('삭제실패했습니당.')");
	            out.println("location.href='history.back()'");
	            out.println("<script>");

	         }
	         
	      }
	      return null;
	}

}
