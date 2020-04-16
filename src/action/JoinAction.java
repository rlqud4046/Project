package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.InterestDAO;
import model.InterestDTO;
import model.MemberDAO;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 회원가입 버튼 클릭 시 관심사 대분류, 지역(시/도), 비밀번호 질문답변 불러오는 클래스
		MemberDAO dao = MemberDAO.getInstance();
		List<String> cityList = dao.getCity();
		List<String> qList = dao.getQuestion();
		
		InterestDAO iDao = InterestDAO.getInstance();
		List<String> lCategorys = iDao.getLCategory();
		List<List<InterestDTO>> allCate = iDao.getInterestList(lCategorys);
		
		request.setAttribute("lCategorys", lCategorys);
		request.setAttribute("cityList", cityList);
		request.setAttribute("qList", qList);
		request.setAttribute("allCate", allCate);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("signUp.jsp");
		
		return forward;
	}
}
