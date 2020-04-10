package action;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.BoardDAO;
import model.BoardDTO;

public class BoardWriteAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

      // 자료실 폼에서 넘오온 데이터를 DB에 저장하는 클래스
      BoardDTO dto = new BoardDTO();
      
      // 첨부파일이 저장될 위치
      String saveFolder = "C:\\NCS\\workspace(jsp)\\Project\\WebContent\\uploadTest";
      
      // 첨부파일의 최대 크기00cs
      int fileSize = 1024 * 1024 * 10;  //=> 1024(kb) * 1024(kb) * 10 => 10MB 
      
      MultipartRequest multi = new MultipartRequest(
            request,      // 일반적인 request 
            saveFolder,    // 업로드 파일 저장 위치
            fileSize,       // 업로드할 파일 최대 사이즈
            "UTF-8",      // 문자 인코딩 방식
            new DefaultFileRenamePolicy());   // 파일이름이 중복될 경우 파일 이름을 rename 해주는 역할 ex) test, test1, test2 ..
      
      // 폼창에서 넘어온 데이터들을 처리해 주자
      String Board_title = multi.getParameter("title").trim();
      String Board_cont = multi.getParameter("cont").trim();
      int Board_imp = 0;
      if(multi.getParameter("imp") != null) {
         Board_imp = 1;
      }
      
      // 자료실 폼 창에서 type="file"이라고 되어있으면
      // getFile() 메서드로 받아주어야 한다.
      File Board_file = multi.getFile("file");
      
      if(Board_file != null) {   // 첨부파일이 존재하는 경우.;
         // getName(): 첨부파일의 이름을 문자열로 반환하는 메서드
         String fileName = Board_file.getName();
         
         // 날짜 객체 생성
         Calendar cal = Calendar.getInstance();
         int year = cal.get(Calendar.YEAR);
         int month = cal.get(Calendar.MONTH) + 1;
         int day = cal.get(Calendar.DAY_OF_MONTH);
         
         // 아래처럼 하면 ......../Board/2020-03-03 형태로 됨
         String homedir = saveFolder + "/" + year + "-" + month + "-" + day;
         
         File path1 = new File(homedir);
         
         if(!path1.exists()) {   // 폴더가 존재하지 않는 경우
            path1.mkdirs();      // 실제 폴더가 만들어짐
         }
         
         // 파일을 만들어 보자.
         String refileName = fileName;
         Board_file.renameTo(new File(homedir + "/" + refileName));
         
         
         String fileDBName = "/" + year + "-" + month + "-" + day + "/"+ refileName;
      
         dto.setBoard_file(fileDBName);
      }
      
      dto.setBoard_title(Board_title);
      dto.setBoard_cont(Board_cont);
      dto.setBoard_imp(Board_imp);
      
      BoardDAO dao = BoardDAO.getInstance();
      
      int res = dao.insertBoard(dto);
      System.out.println("res=>" + res);
      String path = null;
      
      if(res == 1) {
         path = "board_cont.do";
      } else {
         path = "board_write.do";
      }
      System.out.println(path);
      ActionForward forward = new ActionForward();
      forward.setRedirect(true);
      forward.setPath(path);
      
      return forward;
   }
}