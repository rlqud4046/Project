package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.InterestDAO;
import com.model.InterestDTO;
import com.model.MemberDAO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 회원정보 변경 클릭 시 페이지 정보를 불러오는 클래스
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
		forward.setPath("mem_update.jsp");

		return forward;
	}

}
