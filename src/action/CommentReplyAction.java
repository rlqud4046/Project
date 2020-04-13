package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CommentDAO;
import model.CommentDTO;

public class CommentReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		int comment_no = Integer.parseInt(request.getParameter("comment_no"));
		int mgn_no = Integer.parseInt(request.getParameter("mgn_no"));
		String reply_writer = request.getParameter("reply_writer");
        String reply_cont = request.getParameter("reply_cont");
        
        CommentDAO dao = CommentDAO.getInstance();

       
        
        CommentDTO rdto = dao.getComment(comment_no);
		
		/*request.setAttribute("reply", dto);*/
		request.setAttribute("rdto", rdto);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board_cont.jsp");
		return forward;
	}

}
