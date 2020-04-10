package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardDTO;

public class GroupMainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		
		request.setAttribute("group_no", group_no);
		
		//group_no에 해당하는 board_table의 값(mgn_no,board_category,board_title,board_photo)을 받아온다
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> boardList = dao.listBoard(group_no);
		List<BoardDTO> boardPhoto = dao.listPhoto(group_no);
		List<BoardDTO> boardNotice = dao.listNotice(group_no);
		List<BoardDTO> boardHello = dao.listHello(group_no);
		List<BoardDTO> boardFree = dao.listFree(group_no);
		List<BoardDTO> boardMeet = dao.listMeet(group_no);
		
		//사진첩용
		request.setAttribute("boardPhoto", boardPhoto);
		request.setAttribute("boardPhoto1", boardPhoto);
		//공지사항용
		request.setAttribute("boardNotice", boardNotice);
		//가입인사용
		request.setAttribute("boardHello", boardHello);
		//전체게시판용
		request.setAttribute("boardList", boardList);
		//자유게시판용
		request.setAttribute("boardFree", boardFree);
		//정모게시판용
		request.setAttribute("boardMeet", boardMeet);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("group_main.jsp");
		
		return forward;
		
		
		
	}
	
}
