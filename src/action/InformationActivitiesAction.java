package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.JoinDTO;
import model.MemberDAO;

public class InformationActivitiesAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
	  int group_no = Integer.parseInt(request.getParameter("group_no"));
      int rating = Integer.parseInt(request.getParameter("rating"));
      String id = request.getParameter("id");
      
      /*System.out.println("group => " +group_no+"rating => "+rating + "id => " +id);*/
      
      MemberDAO dao = MemberDAO.getInstance();
      
      JoinDTO list = dao.getMemberActivitiesList(group_no, id);
      int totalBoardNo = dao.totalBoardNo(group_no, id);
      int totalReplyNo = dao.totalReplyNo(group_no, id);
      List<JoinDTO> RBoardList = dao.getRegisterBoardList(group_no, id);
      List<JoinDTO> ReplyList = dao.getReplyList(group_no, id);
      
      request.setAttribute("id", id);
      request.setAttribute("rating", rating);
      request.setAttribute("list", list);
      request.setAttribute("totalBoardNo", totalBoardNo);
      request.setAttribute("totalReplyNo", totalReplyNo);
      request.setAttribute("RBoardList", RBoardList);
      request.setAttribute("ReplyList", ReplyList);

      /*System.out.println("group => " +group_no);
      System.out.println("list => " + list.getId());*/
      
      ActionForward forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("activeInfo.do");

      return forward;
   }

}