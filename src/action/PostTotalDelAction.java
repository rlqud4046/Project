package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PostDAO;

public class PostTotalDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 받은편지함에 나타나는 모든 메세지를 삭제하는 메서드
		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		
		/*PostDAO dao = PostDAO.getInstance();
		int res =dao.totalDel(mem_no);
		
		
		
		
		
		*/
		
		
		
		return null;
	}

}
