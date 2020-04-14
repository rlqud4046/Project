package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberDTO;

public class FindIdEmailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String name = request.getParameter("name").trim();
		String email = request.getParameter("email").trim();
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = dao.getMemberInfo(name,email);
		
		if(dto != null) {
			System.out.println("성공");
			dao.findId(name, email, dto);
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("login.do");       

			return forward;
		}else if(dto == null) {	
			System.out.println("실패");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된입력입니다... 다시 입력해주세요..')");
			out.println("location.href='find_id.do'");
			out.println("</script>");
			
		}
		return null;
	}

}
