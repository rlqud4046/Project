package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberDTO;

public class FindPwdEmailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String id = request.getParameter("id").trim();
		System.out.println(id);
		String check_q = request.getParameter("check_q").trim();
		System.out.println(check_q);
		String check_a = request.getParameter("check_a").trim();
		System.out.println(check_a);
		
		PrintWriter out = response.getWriter();
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = dao.getMember(id);
		int count = dao.infoOk(id,check_q,check_a);
		
		if(count == 1) {
			
			System.out.println("성공");
			
			StringBuffer temp =new StringBuffer();
            Random rnd = new Random();
            for(int i=0;i<10;i++)
            {
                int rIndex = rnd.nextInt(3);
                switch (rIndex) {
                case 0:
                    // a-z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    temp.append((rnd.nextInt(10)));
                    break;
                }
            }
            String pwd = temp.toString();
            System.out.println(pwd);
            
	         
            dao.findpwd(dto, pwd);
            dao.updatepwd(pwd,id);
            
            out.println("<script>");
			out.println("alert('임시 비밀번호를 이메일에 발송했습니다.')");
			out.println("location.href='login.do'");
			out.println("</script>");
			
			
		}else if(count == -1) {
			
			System.out.println("실패");
			
			out.println("<script>");
			out.println("alert('잘못된입력입니다... 다시 입력해주세요..')");
			out.println("location.href='login.do'");
			out.println("</script>");
			
		}
		
		return null;
	}

}
