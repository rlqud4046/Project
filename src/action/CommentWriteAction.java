package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CommentBean;
import model.CommentDAO;
import model.CommentDTO;

public class CommentWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		CommentDAO dao = CommentDAO.getInstance();
        CommentDTO comment = new CommentDTO();
        // 파리미터 값을 가져온다.
        int mgn_no = Integer.parseInt(request.getParameter("mgn_no"));
        String comment_writer = request.getParameter("comment_writer");
        String comment_cont = request.getParameter("comment_cont");
        
        //comment.setComment_num(dao.getSeq());    // 댓글 번호는 시퀀스값으로
        comment.setMgn_no(mgn_no);
        comment.setComment_writer(comment_writer);
        comment.setComment_cont(comment_cont);
        
        boolean result = dao.insertComment(comment);
        
 
        if(result){
            response.setContentType("text/html;charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("1");
            out.close();
        }
            
        return null;


	}

}
