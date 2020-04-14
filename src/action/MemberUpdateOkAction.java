package action;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.MemberDAO;
import com.model.MemberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 회원정보를 수정하는 클래스

		MemberDTO dto = new MemberDTO();

		String saveFolder = "C:\\ncs\\workspace(jsp)\\00_somoim\\WebContent\\upload";

		int fileSize = 1024 * 1024 * 10;

		MultipartRequest multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy());

		String id = multi.getParameter("id").trim();
		String pwd = multi.getParameter("pwd").trim();
		String mem_name = multi.getParameter("mem_name").trim();
		String nickname = multi.getParameter("nickname").trim();
		String birth = multi.getParameter("birth").trim();
		String e_mail = multi.getParameter("e_mail").trim();
		String phone = multi.getParameter("phone").trim();
		String check_q = multi.getParameter("check_q").trim();
		String check_a = multi.getParameter("check_a").trim();
		String city = multi.getParameter("city").trim();
		String area1 = multi.getParameter("town1").trim();
		String area2 = multi.getParameter("town2").trim();
		String area3 = multi.getParameter("town3").trim();
		String[] sCate = multi.getParameterValues("s_category");
		String interests = "";
		for (int i = 0; i < sCate.length; i++) {
			if (i == sCate.length - 1) {
				interests += sCate[i];
				break;
			}
			interests += sCate[i] + ",";
		}

		File upload_file = multi.getFile("profile_img");

		if (upload_file != null) {
			String fileName = upload_file.getName();

			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.DAY_OF_MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);

			String homedir = saveFolder + "/" + year + "-" + month + "-" + day;
			File path1 = new File(homedir);
			if (!path1.exists()) {
				path1.mkdirs();
			}

			String refileName = id + "_" + fileName;
			upload_file.renameTo(new File(homedir + "/" + refileName));
			String fileDBName = "/" + year + "-" + month + "-" + day + "/" + refileName;
			dto.setProfile_img(fileDBName);
		}else {
			String fileDBName = multi.getParameter("profile_crnt");
			dto.setProfile_img(fileDBName);
		}
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setMem_name(mem_name);
		dto.setNickname(nickname);
		dto.setBirth(birth);
		dto.setE_mail(e_mail);
		dto.setPhone(phone);
		dto.setCheck_q(check_q);
		dto.setCheck_a(check_a);
		dto.setCity(city);
		dto.setArea1(area1);
		dto.setArea2(area2);
		dto.setArea3(area3);
		dto.setInterests(interests);
		
		System.out.println(dto.toString());

		MemberDAO dao = MemberDAO.getInstance();
		int res = dao.updateMemberInfo(dto);

		return null;
	}

}
