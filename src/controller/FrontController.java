package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;


public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 한글 인코딩 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		String uri = request.getRequestURI(); // 현재 프로젝트명과 파일명을 문자열로 반환하는 메서드, "/10_Board_reply/board_list.do"를 반환
		String path = request.getContextPath(); // 현재 프로젝트명을 문자열로 반환하는 메서드, "/10_Board_reply"를 반환
		String command = uri.substring(path.length() + 1); // 결과 값은 "~~~~.do"만 반환

		System.out.println("uri : " + uri);
		System.out.println("path : " + path);
		System.out.println("command : " + command);

		Action action = null;
		ActionForward forward = null;
		/*
		 * java.util.Properties 클래스 Properties 클래스는 Hashtable의 하위 클래스 보통은 환경변수 및 속성 값을
		 * properties 파일에 저장하여 쉽게 접근할 수 있는 장점이 있음 properties 파일은 일련의 키=값의 한 쌍으로 이루어져 있다.
		 * 보통은 파일에 저장하여 사용됨. 파일 이름을 *.properties로 끝나게 함 InputStream에 Properties 파일을 넣어서
		 * 그 스트림으로 부터 파일을 가져온다. 인자로 들어온 Properties 파일을 읽게 된다. 읽어들일 때 사용하는 메서드는 load()
		 */

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				"C:\\NCS\\workspace(jsp)\\Project\\src\\controller\\mapping.properties");

		prop.load(fis); // mapping.properties로

		String value = prop.getProperty(command); // command에는 키 = board_list.do가 들어있고 그에 해당하는 값이 매핑되어 있음

		System.out.println("value : " + value);

		if (value.substring(0, 7).equals("execute")) {
			StringTokenizer st = new StringTokenizer(value, "|");
			String url_1 = st.nextToken(); // "execute"
			String url_2 = st.nextToken(); // "패키지명.클래스명"



			try {
				Class url = Class.forName(url_2);
				action = (Action) url.newInstance(); // 동적 객체 생성
				// action = new BbsListAction(), newBbsSearchAction()....등 여러개를 일일히 하는 대신 동적 객체 하나 생성해서 다 처리해줌
				forward = action.execute(request, response); // ~~Action의 execute 호출
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else { // value 값 중에 "execute"가 아닌 경우
			
			// viewPage로 이동
			forward = new ActionForward();
			forward.setRedirect(false); // *.jsp 페이지로 이동
			forward.setPath(value);
		}
		
		
		if(forward!=null) {
			if(forward.isRedirect()) { // true인 경우- *.do
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		}

	}
}
