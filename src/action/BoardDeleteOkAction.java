package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardDTO;



public class BoardDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		String pwd = request.getParameter("pwd");
		

		BoardDAO dao = BoardDAO.getInstance();

		int res = dao.boardDelete(no, pwd);

		PrintWriter out = response.getWriter();
		
		ActionForward forward = new ActionForward();
		
		BoardDTO dto = dao.boardCont(no);
	
		if (res > 0) {
			
			forward.setRedirect(false);
			forward.setPath("board_list.do?board_category="+dto.getBoard_category()+"&group_no="+dto.getGroup_no());

		} else if (res == 0) {
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
