package action;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import sun.util.locale.StringTokenIterator;

import model.BoardDAO;
import model.BoardDTO;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 자료실 폼에서 넘오온 데이터를 DB에 저장하는 클래스
		BoardDTO dto = new BoardDTO();

		// 첨부파일이 저장될 위치
		String saveFolder = "C:\\ncs\\workspace(jsp)\\00_somoim\\WebContent\\upload\\board";

		// 첨부파일의 최대 크기00cs
		int fileSize = 1024 * 1024 * 10; // => 1024(kb) * 1024(kb) * 10 => 10MB

		MultipartRequest multi = new MultipartRequest(request, // 일반적인 request
				saveFolder, // 업로드 파일 저장 위치
				fileSize, // 업로드할 파일 최대 사이즈
				"UTF-8", // 문자 인코딩 방식
				new DefaultFileRenamePolicy()); // 파일이름이 중복될 경우 파일 이름을 rename 해주는 역할 ex) test, test1, test2 ..

		// 폼창에서 넘어온 데이터들을 처리해 주자

		// 작성자, 모임번호, 게시판 분류 등은 request로 전달받는다
		// String board_writer =
		String board_writer = "leess"; // 아이디
		String nickname = "이순신";
		int group_no = 1; // 모임번호
		int board_category = 6; // 게시판 분류 - 1:사이트공지 ,2:공지사항, 3:가입인사, 4:정모게시판, 5:자유게시판, 6:사진첩

		String board_title = multi.getParameter("title").trim();
		String board_cont = multi.getParameter("context").trim();
		String tempThumb = multi.getParameter("thumb").trim();
		int Board_imp = 0;

		if (multi.getParameter("imp") != null) {
			Board_imp = 1;
		}

		// 이미지 썸네일 업로드 부분
		// 이미지파일 url값이 비어있는지 확인
		if (!tempThumb.isEmpty()) {

			// 이미지 url에서 파일을 불러온다
			File thumb = new File(tempThumb);

			// 파일 이름에서 이름과 확장자를 분리한다
			String fileName = thumb.getName();
			StringTokenizer st = new StringTokenizer(fileName, ".");
			String name = st.nextToken(); // 파일이름
			String extension = st.nextToken(); // 확장자

			// 파일이 저장될 경로를 만든다 => 모임번호/게시판분류/날짜/제목_작성자.확장자
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			String date = year + "-" + month + "-" + day;

			// 파일이 저장될 경로를 변수에 저장
			String path = saveFolder + "/" + group_no + "/" + board_category + "/" + date;

			// 경로 생성
			File bbsDir = new File(path);
			if (!bbsDir.exists()) {
				bbsDir.mkdirs();
			}

			// 파일 이름을 변경해서 생선한 경로에 만들어준다 => 제목_작성자.확장자
			String newFileName = board_title + "_" + board_writer + "." + extension;
			thumb.renameTo(new File(path + "/" + newFileName));
			String ImgDBName = "/upload/board/"+group_no+"/"+board_category+"/"+date+"/"+newFileName;
			dto.setBoard_photo(ImgDBName);
		}

		// 자료실 폼 창에서 type="file"이라고 되어있으면
		// getFile() 메서드로 받아주어야 한다.
		File Board_file = multi.getFile("file");

		if (Board_file != null) { // 첨부파일이 존재하는 경우.;
			// getName(): 첨부파일의 이름을 문자열로 반환하는 메서드
			String fileName = Board_file.getName();

			// 날짜 객체 생성
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			String date = year + "-" + month + "-" + day;

			// 아래처럼 하면 ......../Board/group_no/board_category/yyyy-mm-dd 형태로 됨
			String path = saveFolder + "/" + group_no + "/" + board_category + "/" + date;

			// 경로 생성
			File bbsDir = new File(path);
			if (!bbsDir.exists()) {
				bbsDir.mkdirs();
			}

			// 파일을 만들어 보자.
			String refileName = board_title+"_"+board_writer+""+fileName;
			Board_file.renameTo(new File(path + "/" + refileName));
			String fileDBName = "/upload/board/"+group_no+"/"+board_category+"/"+date+"/"+refileName;
			dto.setBoard_file(fileDBName);
		}
		
		// 데이터 전송 객체에 데이터를 저장
		dto.setBoard_writer(board_writer);
		dto.setNickname(nickname);
		dto.setBoard_category(board_category);
		dto.setGroup_no(group_no);		
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_imp(Board_imp);

		BoardDAO dao = BoardDAO.getInstance();
//		System.out.println(dto.toString());

		int res = dao.insertBoard(dto);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("board_list.do");

		return null;
	}
}