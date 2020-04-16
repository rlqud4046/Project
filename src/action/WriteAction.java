package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class WriteAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
      int board_category = Integer.parseInt(request.getParameter("board_category"));
      
      
      request.setAttribute("board_category", board_category);
      
      
      
      
      ActionForward forward = new ActionForward();
      String path = "board_write.jsp";
      request.setAttribute("page", path);
      // view page로 포워딩

      forward.setRedirect(false);
      forward.setPath("samplePage.jsp"); // ㄴ므ㅔ
      return forward;
   }

}