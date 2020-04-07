package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardDTO;
import model.CommentDAO;
import model.CommentDTO;


public class BoardContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int mgn_no = Integer.parseInt(request.getParameter("no"));
		/*int nowPage = Integer.parseInt(request.getParameter("page"));
		int search = Integer.parseInt(request.getParameter("search"));
		String find_field = request.getParameter("find_field");
		String find_name = request.getParameter("find_name");*/
		
		BoardDAO dao =BoardDAO.getInstance();
		dao.boardHit(mgn_no);		// 조회수 증가 메서드 호출
		
		BoardDTO dto = dao.boardCont(mgn_no);		// 게시물 상세 내역 조회하는 메서드 호출
		request.setAttribute("cont", dto);
	/*	request.setAttribute("page", nowPage);
		request.setAttribute("search", search);
		request.setAttribute("find_field", find_field);
		request.setAttribute("find_name", find_name);*/
		
		CommentDAO cdao = CommentDAO.getInstance();
		List<CommentDTO> cdto = cdao.commentCont(mgn_no);
		int count = cdao.commentCount(mgn_no);
		
		request.setAttribute("comment", cdto);
		request.setAttribute("count", count);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board_cont.jsp");
		return forward;
	}

}
