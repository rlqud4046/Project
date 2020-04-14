package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.PostDAO;

public class PostWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		ActionForward forward = new ActionForward();
		
		String receiverId=request.getParameter("receiverId");
		int sender=Integer.parseInt(request.getParameter("mem_no"));
		String post_cont=request.getParameter("cont");
		
		PostDAO dao = PostDAO.getInstance();
		
		//아이디가 존재하면, 해당하는 mem_no를 받아온다
		int receiver = dao.searchID(receiverId);
		
		PrintWriter out = response.getWriter();
		
		if(receiver!=0) {
			int result = dao.WriteOk(receiver,sender,post_cont);
			
			if(result==1) {
				forward.setRedirect(false);
				forward.setPath("post_send.do?mem_no="+sender);
				return forward;
			}else {
				out.println("<script>");
				out.println("alert('쪽지보내기에 실패하였습니다')");
				out.println("location.href='history.back()'");
				out.println("</script>");
			}
			
		}else if(receiver==0) {
			
			out.println("<script>");
			out.println("alert('아이디를 확인해주세요~')");
			out.println("location.href='history.back()'");
			out.println("</script>");
		}
	
		
		return null;
	}
}
