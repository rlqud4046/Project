package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.LikeDAO;

public class LikeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		int mgn_no = Integer.parseInt(request.getParameter("mgn_no"));
		int like = Integer.parseInt(request.getParameter("like"));
		LikeDAO dao = LikeDAO.getInstance();

		boolean result = dao.like(mem_no, mgn_no);
		BoardDAO bdao = BoardDAO.getInstance();
		if (like == 0) {
			bdao.likeIncrease(mgn_no);
			
		} else if (like == 1) {
			bdao.likeDecrease(mem_no, mgn_no);
			
		}
		if (result) {
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("1");
			out.close();
		}

		return null;
	}

}
