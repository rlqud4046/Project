package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int board_category = Integer.parseInt(request.getParameter("board_category"));
		int group_no=0;
		List<BoardDTO> list;
		if(request.getParameter("group_no")!=null) {
			group_no = Integer.parseInt(request.getParameter("group_no"));
			list = dao.getBoardList(group_no, board_category);
		}else {
			list = dao.getBoardList(0, board_category);
		}
		
		request.setAttribute("List", list);					// O
		request.setAttribute("group_no", group_no);					// O
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
