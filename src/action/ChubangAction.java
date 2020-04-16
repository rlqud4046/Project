package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GroupDAO;

public class ChubangAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

      String id = request.getParameter("id");
      int group_no = Integer.parseInt(request.getParameter("group_no"));
      
      
      GroupDAO dao = GroupDAO.getInstance();
      dao.chubang(id,group_no);
      
      ActionForward forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("member_management.do");
      
      return forward;
   }

}