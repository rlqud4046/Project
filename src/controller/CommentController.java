package controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
 
public class CommentController extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private HashMap<String,Action> commandMap;
    
    /**
     * 최초 실행 init
     */
    public void init(ServletConfig config) throws ServletException {    
        loadProperties("properties/CommentCommand");
    }
 
    /**
     * 프로퍼티 파일에서 키값과 클래스 정보를 추출하여 그것을 Map에 저장한다.
     * @param filePath 프로퍼티 파일의 경로
     */
    private void loadProperties(String filePath) 
    {
        commandMap = new HashMap<String, Action>();
        
        ResourceBundle rb = ResourceBundle.getBundle(filePath);
        Enumeration<String> actionEnum = rb.getKeys(); // 키값을 가져온다.
         
        while (actionEnum.hasMoreElements()) 
        {
            // 명령어를 가져온다.
            String command = actionEnum.nextElement(); 
            // 명령어에 해당하는 Action 클래스 이름을 가져온다.
            String className = rb.getString(command); 
            
            try {
                 Class actionClass = Class.forName(className); // 클래스 생성
                 Action actionInstance = (Action)actionClass.newInstance(); // 클래스의 객체를 생성
                 
                 // 맵에 명령어와 Action을 담는다.
                 commandMap.put(command, actionInstance);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
 
    /**
     * GET 방식일 경우 doGet()
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            doProcess(request,response);
    }      
        
    /**
     * POST 방식일 경우 doPost()
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            doProcess(request,response);
    }
 
    /**
     * 명령어에 따른 해당 Action을 지정해 준다.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doProcess(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        // 넘어온 커맨드를 추출하는 과정
        String requestURI = request.getRequestURI();
        int cmdIdx = requestURI.lastIndexOf("/") + 1;
        String command = requestURI.substring(cmdIdx);
 
        // URI, command 확인
        // System.out.println("requestURI : "+requestURI);
        //System.out.println("Board cmd : "+command);
        
        ActionForward forward = null;
        Action action = null;
        
        try {
            // 맵에서 명령어에 해당하는 Action을 가져온다.
            action = commandMap.get(command);
            
            if (action == null) {
                System.out.println("명령어 : "+command+"는 잘못된 명령입니다.");
                return;
            }
 
            forward = action.execute(request, response);
            
            /*
             * 화면이동 - isRedirext() 값에 따라 sendRedirect 또는 forward를 사용
             * sendRedirect : 새로운 페이지에서는 request와 response객체가 새롭게 생성된다.
             * forward : 현재 실행중인 페이지와 forwad에 의해 호출될 페이지는 request와 response 객체를 공유
             */
            if(forward != null){
                if (forward.isRedirect()) {
                    response.sendRedirect(forward.getPath());
                } else {
                    RequestDispatcher dispatcher = request
                            .getRequestDispatcher(forward.getPath());
                    dispatcher.forward(request, response);
                }
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end doProcess                    
}
