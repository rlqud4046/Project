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
        CommentDTO dto = new CommentDTO();
        //comment.setComment_num(dao.getSeq());    // 댓글 번호는 시퀀스값으로
        dto.setMgn_no(mgn_no);
        dto.setComment_parent(comment_no);
        dto.setComment_writer(reply_writer);
        dto.setComment_cont(reply_cont);
        
        boolean result = dao.replyComment(dto);
 
        if(result){
            response.setContentType("text/html;charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("1");
            out.close();
        }
		
		request.setAttribute("reply", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("board_list.jsp");
		return forward;
	}

}
