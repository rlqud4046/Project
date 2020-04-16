package action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GroupDAO;
import model.GroupDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GroupUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 모임 정보를 수정하는 클래스
		
		GroupDTO dto = new GroupDTO();
	
		String saveFolder = "C:\\ncs\\workspace(jsp)\\00_somoim\\WebContent\\upload";
	
		int fileSize = 1024 * 1024 * 10;
	
		MultipartRequest multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8",
				new DefaultFileRenamePolicy());
		
		int group_no = Integer.parseInt(multi.getParameter("group_no"));
		String group_name = multi.getParameter("group_name");
		String group_intro = multi.getParameter("group_intro");
		String group_chatroom = multi.getParameter("group_chatroom");
		String group_facebook = multi.getParameter("group_facebook");
		String group_insta = multi.getParameter("group_insta");
		File upload_front = multi.getFile("group_front_img");
		File upload_main = multi.getFile("group_main_img");
	
		// 대문사진 업로드
		if (upload_front != null) {
			String fileName = upload_front.getName();
	
			String homedir = saveFolder + "/" + group_no + "/" + "frontImg";
			File path1 = new File(homedir);
			if (!path1.exists()) {
				path1.mkdirs();
			}
			String refileName = group_name + "_" + fileName;
			upload_front.renameTo(new File(homedir + "/" + refileName));
			String fileDBName = "/"+group_no+"/"+"frontImg"+"/"+refileName;
			dto.setGroup_front_img(fileDBName);
		}else {
			String fileDBName = multi.getParameter("front_img_crnt");
			dto.setGroup_front_img(fileDBName);
		}
		
		// 메인이미지 업로드
		if (upload_main != null) {
			String fileName = upload_main.getName();
			
			String homedir = saveFolder + "/" + group_no + "/" + "mainImg";
			File path1 = new File(homedir);
			if (!path1.exists()) {
				path1.mkdirs();
			}
			String refileName = group_name + "_" + fileName;
			upload_main.renameTo(new File(homedir + "/" + refileName));
			String fileDBName = "/"+group_no+"/"+"mainImg"+"/"+refileName;
			dto.setGroup_main_img(fileDBName);
		}else {
			String fileDBName = multi.getParameter("main_img_crnt");
			dto.setGroup_main_img(fileDBName);
		}
		
		dto.setGroup_no(group_no);
		dto.setGroup_name(group_name);
		dto.setGroup_intro(group_intro);
		dto.setGroup_chatroom(group_chatroom);
		dto.setGroup_insta(group_insta);
		dto.setGroup_facebook(group_facebook);
		
		GroupDAO dao = GroupDAO.getInstance();
		int res = dao.updateGroupInfo(dto);
		
//		ActionForward forward = new ActionForward();
//		forward.setRedirect(true);
//		forward.setPath("모임관리.jsp");		
		return null;
	}

}
