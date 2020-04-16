package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GroupDAO;
import model.MemberDAO;
import model.MemberDTO;

public class LoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String mem_id = request.getParameter("id").trim();
		String mem_pwd = request.getParameter("pwd").trim();

		MemberDAO dao = MemberDAO.getInstance();
		int res = dao.userCheck(mem_id, mem_pwd);

		HttpSession session = request.getSession();

		if (res > 0) {

			MemberDTO dto = dao.getMember(mem_id);

			// 회원의 정보를 세션 객체에 저장
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getMem_name());
			session.setAttribute("nickname", dto.getNickname());
			session.setAttribute("mem_no", dto.getMem_no());
			session.setAttribute("email", dto.getE_mail());
			

			System.out.println("로그인성공");

		} else if (res == -1) {

			System.out.println("비번틀림~");

		} else if (res == -2) {

			System.out.println("회원이아님!");

		} else {

			System.out.println("로그인실패");

		}

		GroupDAO gdao = GroupDAO.getInstance();
		String[][] rand = gdao.random();

		request.setAttribute("rand", rand);

		ActionForward forward = new ActionForward();
		String path = "main.jsp";
		request.setAttribute("page", path);
		// view page로 포워딩

		forward.setRedirect(false);
		forward.setPath("samplePage.jsp"); // ㄴ므ㅔ
		return forward;
	}

}