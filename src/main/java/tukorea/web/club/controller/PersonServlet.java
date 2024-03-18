package tukorea.web.club.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tukorea.web.club.domain.PersonVO;
import tukorea.web.club.persistence.PersonDAO;
/**
 * Servlet implementation class PersonServlet
 */
@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String cmdReq="";
		cmdReq = request.getParameter("cmd");
		

		if (cmdReq.equals("join")) {
	        PersonVO personVO = new PersonVO();
	        personVO.setId(request.getParameter("id"));
	        personVO.setPasswd(request.getParameter("passwd"));
	        personVO.setUsername(request.getParameter("username"));
	        personVO.setMobile(request.getParameter("mobile"));
	        personVO.setEmail(request.getParameter("email"));

	        PersonDAO personDao = new PersonDAO();
	        String message = "";

	        if (personDao.add(personVO)) {
	            // 회원가입 성공
	            // 세션에 사용자 이름 저장
	            request.getSession().setAttribute("loggedInUser", personVO.getId());

	            // 포워딩
	            RequestDispatcher view = request.getRequestDispatcher("welcome.jsp");
	            view.forward(request, response);
	        } 
	    }
		  if (cmdReq.equals("login")) {
	            // 로그인 처리
	            String id = request.getParameter("id");
	            String passwd = request.getParameter("passwd");

	            // PersonDAO를 통해 데이터베이스에서 로그인 확인
	            PersonDAO personDAO = new PersonDAO();
	            String username = personDAO.login(id, passwd);

	            if (username != null) {
	                // 로그인 성공
	                // 사용자 이름을 세션에 저장
	                request.getSession().setAttribute("loggedInUser", id);

	                // 환영 페이지로 이동
	                response.sendRedirect("welcome.jsp");
	            } else {
	                // 로그인 실패
	                // 여기서 모달 대화상자를 띄우는 JavaScript 코드로 응답을 작성
	                response.setContentType("text/html;charset=UTF-8");
	                PrintWriter out = response.getWriter();
	                out.println("<script>");
	                out.println("alert('일치하는 ID나 password가 없습니다.');");
	                out.println("window.location.href='login.html';");
	                out.println("</script>");
	            }
	        }
		  
		  
	}
		}