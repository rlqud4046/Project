package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.GroupDAO;
import com.model.GroupDTO;

public class GroupUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 모임 정보 변경을 눌렀을때 페이지 정보를 로딩하는 클래스
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		
		GroupDAO dao = GroupDAO.getInstance();
		GroupDTO groupInfo = dao.getGorupInfo(group_no);
		
		String path = "update_group_info.jsp";
		request.setAttribute("groupInfo", groupInfo);
		request.setAttribute("page", path);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("sample.jsp");
		return forward;
	}

}
