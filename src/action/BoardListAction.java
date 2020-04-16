package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;
import model.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int board_category = Integer.parseInt(request.getParameter("board_category"));
		int group_no=0;
		List<BoardDTO> list;
		HttpSession session = request.getSession();
		
		
		if(session.getAttribute("group_No")!=null) {
			group_no = (int) session.getAttribute("group_No");
			list = dao.getBoardList(group_no, board_category);
			request.setAttribute("group_no", group_no);					// O
		}else {
			list = dao.getBoardList(0, board_category);
		}
		
		System.out.println(group_no);
		System.out.println(board_category);
		
		request.setAttribute("List", list);					// O
		request.setAttribute("board_category", board_category);					// O
		
		String path = "board_list.jsp";
		request.setAttribute("include", path);
		// view page로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("sampleGroup.jsp"); // ㄴ므ㅔ
		return forward; 
	}

}
