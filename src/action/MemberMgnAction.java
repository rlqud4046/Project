package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GroupDAO;
import model.JoinDTO;

public class MemberMgnAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// DB의 전체 레코드를 화면으로 이동시켜서 출력시키는 클래스
		
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		
		//세션에서 멤버넘버받아오기
		int mem_no= Integer.parseInt(request.getParameter("mem_no"));
		
		
		GroupDAO dao = GroupDAO.getInstance();
		List<JoinDTO> list = dao.getmemMgn(group_no);
		System.out.println("memberMgnAction");

		request.setAttribute("List", list);
		
/*		for(JoinDTO dto : list) {
			System.out.println(dto.toString());
		}*/
		
		int rating =dao.MyRating(mem_no,group_no);
		
		request.setAttribute("rating", rating);
		
		// view page로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("member_management.jsp");

		return forward;
	}
}
