package tukorea.web.club.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tukorea.web.club.domain.PersonVO;
import tukorea.web.club.persistence.PersonDAO;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String cmdReq="";
		cmdReq = request.getParameter("cmd");
		

		if (cmdReq.equals("mypage")) {
			 // 세션에서 로그인한 사용자의 ID 가져오기
	        String loggedInUserId = (String) request.getSession().getAttribute("loggedInUser");

	        // PersonDAO를 사용하여 데이터베이스에서 사용자 정보 가져오기
	        PersonDAO personDAO = new PersonDAO();
	        PersonVO user = personDAO.getUserInfo(loggedInUserId);

	        // 사용자 정보를 요청 속성에 설정
	        request.setAttribute("user", user);

	        // MyPage.jsp로 포워딩
	        RequestDispatcher dispatcher = request.getRequestDispatcher("mypage.jsp");
	        dispatcher.forward(request, response);
	    }
		else if (cmdReq.equals("pwdupdate")) {
		     // 세션에서 현재 로그인한 사용자의 ID를 가져옴
	        String loggedInUserId = (String) request.getSession().getAttribute("loggedInUser");
	      
	        PersonDAO personDAO = new PersonDAO();
	        PersonVO user = personDAO.getUserInfo(loggedInUserId);

	        if (user != null) {
	            // 회원 정보를 request 속성으로 추가
	            request.setAttribute("user", user);

	            // 수정 페이지로 forward
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/editpasswd.jsp");
	            dispatcher.forward(request, response);
	        
		}
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String cmdReq="";
		cmdReq = request.getParameter("cmd");
		
		 if (cmdReq.equals("newpasswd")) {
	            // 세션에서 현재 로그인한 사용자의 ID를 가져옴
	            String loggedInUserId = (String) request.getSession().getAttribute("loggedInUser");

	            // 새로운 비밀번호를 가져옴
	            String newPassword = request.getParameter("newPassword");

	            // PersonDAO를 통해 데이터베이스에서 비밀번호 업데이트
	            PersonDAO personDAO = new PersonDAO();
	            boolean passwordUpdated = personDAO.updatePassword(loggedInUserId, newPassword);

	            if (passwordUpdated) {
	                // 비밀번호 업데이트 성공
	            	 RequestDispatcher view = request.getRequestDispatcher("welcome.jsp");
	                 view.forward(request, response);
	            
	            }
		 }
		 
		
		doGet(request, response);
		
	}

}
