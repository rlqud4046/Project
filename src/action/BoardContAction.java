package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;
import model.BoardDTO;
import model.CommentDAO;
import model.CommentDTO;
import model.LikeDAO;
import model.LikeDTO;
import model.MemberDAO;
import model.MemberDTO;


public class BoardContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int mgn_no = Integer.parseInt(request.getParameter("no"));
		/*int mem_no = Integer.parseInt(request.getParameter("id"));*/
		HttpSession session = request.getSession();
		
		int mem_no = (int) session.getAttribute("mem_no");
		
		System.out.println(mem_no);
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
		List<String> attachList = dao.attachList(mgn_no);
		
		LikeDAO ldao = LikeDAO.getInstance();
		int lcheck = ldao.likeCheck(mem_no, mgn_no);
		List<LikeDTO> likes =  ldao.LikeMemList(mgn_no);
		
		MemberDAO mdao = MemberDAO.getInstance();
		
		List<MemberDTO> lmem = mdao.likeList(likes);
		request.setAttribute("lmem", lmem);
	
		request.setAttribute("comment", cdto);
		request.setAttribute("count", count);
		request.setAttribute("aList", attachList);
		request.setAttribute("lCheck", lcheck);
		request.setAttribute("like", likes);
		
		
		
		String path = "board_cont.jsp";
		request.setAttribute("page", path);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("samplePage.jsp");
		return forward;
	}

}
