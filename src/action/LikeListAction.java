package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.JoinDTO;
import model.LikeDAO;

public class LikeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		
		// DB에  게시글 전체 레코드를 조회하는 클래스.
		// BoardDAO dao = BoardDAO.getInstance();
		// List<BoardDTO> list = dao.getBoardList();
		// request.setAttribute("List",list);
				
		// 페이징 처리
		int rowsize = 3;     // 한 페이지에 보여질 게시물의 수
		int block = 3;       // 아래에 보여질 페이지의 최대 수 = 예) [1][2][3] / [4][5][6]
		int totalRecord = 0; // DB상의 레코드 전체 수(게시물의 수)
		int allPage = 0;     // 전체 페이지 수
		
		int page = 0;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}else  {
			page = 1;   // 처음으로 게시물 목록태그를 클릭한 경우
		}
		
		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);
		// 해당 페이지의 끝 번호
		int endNo = (page * rowsize);
				
		//해당 페이지의 시작 블럭
		int startBlock = (((page - 1) / block) * block) + 1;
		// 해당 페이지의 마지막 블럭
		int endBlock = (((page-1)/block)*block)+block;
		
		LikeDAO dao = LikeDAO.getInstance();
		totalRecord = dao.getListCount(mem_no);
		
		// 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어 주어야 한다.
		// 이 과정을 거치면 전체 페이지 수가 나온다.
		// 전체 페이지가 나올 때 나머지가 있으면 무조건 올려주어야 한다.
				
		allPage = (int)Math.ceil(totalRecord / (double)rowsize);  //ceil 해 주었을때 44.0 같은 형식으로 나오기 때문에 int타입으로 형변환 해줘야한다.
				
		if(endBlock > allPage) {
			endBlock = allPage;
		}
		System.out.println(endBlock);
		
		List<JoinDTO> list = dao.getLikeBoardList(page,rowsize,mem_no);
		// 페이징 처리시 사용했던 모든 값들을 키로 저장하자.
				request.setAttribute("page", page);
				request.setAttribute("rowsize", rowsize);
				request.setAttribute("block", block);
				request.setAttribute("totalRecord", totalRecord);
				request.setAttribute("allPage", allPage);
				request.setAttribute("startNo", startNo);
				request.setAttribute("endNo", endNo);
				request.setAttribute("startBlock", startBlock);
				request.setAttribute("endBlock", endBlock);
				request.setAttribute("List", list);
		
		// view 페이지로 포워딩
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("likeBoardList.do");
						
		return forward;
	}

}
