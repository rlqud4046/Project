package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberDTO;
import model.PostDAO;
import model.PostDTO;

public class PostReceiveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int mem_no =Integer.parseInt(request.getParameter("mem_no"));
		
		
		System.out.println("메[ㅁ버넘ㅅ버"+mem_no);//sender값이 온당.
		ActionForward forward = new ActionForward();
		
		PostDAO dao = PostDAO.getInstance();
		//받은 쪽지 리스트를 받아온다
		List<PostDTO> receive_list =dao.receive(mem_no);
		request.setAttribute("receive_list", receive_list);
		
		
		forward.setRedirect(false);
		forward.setPath("post_receive.jsp");
		
		return forward;
	}

}
