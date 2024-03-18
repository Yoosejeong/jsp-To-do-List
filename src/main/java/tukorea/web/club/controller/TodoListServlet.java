package tukorea.web.club.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import tukorea.web.club.domain.TodoListVO;

import tukorea.web.club.persistence.TodoListDAO;

/**
 * Servlet implementation class TodoListServlet
 */
@WebServlet("/TodoListServlet")
public class TodoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String cmdReq="";
		cmdReq = request.getParameter("cmd");
		if (cmdReq.equals("todolist")) {
		    

		  
		    String loggedInUserId = (String) request.getSession().getAttribute("loggedInUser");
		    TodoListDAO todoListDAO = new TodoListDAO();
		    ArrayList<TodoListVO> todoList = todoListDAO.getTodoList(loggedInUserId);

		  

		    request.setAttribute("todoList", todoList);
		    RequestDispatcher view = request.getRequestDispatcher("todolist.jsp");
		    view.forward(request, response);
		}
		
		if (cmdReq.equals("delete")) {
		    // 삭제할 투두리스트의 ID와 content를 가져옴
			String todoId = request.getParameter("id");
		    String todoContent = request.getParameter("content");

		    // TodoListDAO를 통해 투두리스트 삭제
		    TodoListDAO todoListDAO = new TodoListDAO();
		    if (todoListDAO.delete(todoId, todoContent)) {
		        // 삭제 성공
		        System.out.println("TodoList deleted successfully");

		        String loggedInUserId = (String) request.getSession().getAttribute("loggedInUser");
		        // 삭제 후에 투두리스트 목록을 다시 불러와서 화면 갱신 등을 수행
		        ArrayList<TodoListVO> updatedTodoList = todoListDAO.getTodoList(loggedInUserId);

		        // 가져온 투두리스트를 request 속성에 설정
		        request.setAttribute("todoList", updatedTodoList);

		        // 포워딩
		        RequestDispatcher view = request.getRequestDispatcher("todolist.jsp");
		        view.forward(request, response);
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
		    if (cmdReq.equals("todoadd")) {

				
						    	
		    	// 사용자가 입력한 투두리스트 내용
		    	String todoContent = request.getParameter("add");

		    	// 현재 로그인한 사용자의 id 값을 세션에서 가져옴
		    	String loggedInUserId = (String) request.getSession().getAttribute("loggedInUser");

		    	// 투두리스트 객체 생성 및 id 설정
		    	TodoListVO todolistVO = new TodoListVO();
		    	todolistVO.setId(loggedInUserId);
		    	todolistVO.setContent(todoContent);

		    	// TodoListDAO를 통해 데이터베이스에 투두리스트 추가
		    	TodoListDAO todolistDao = new TodoListDAO();

		    	if (todolistDao.add(todolistVO)) {
		            // 투두리스트 추가 성공
		            // 데이터베이스에서 현재 로그인한 사용자의 투두리스트 가져오기
		            ArrayList<TodoListVO> todoList = todolistDao.getTodoList(loggedInUserId);

		            // 가져온 투두리스트를 request 속성에 설정
		            request.setAttribute("todoList", todoList);

		            // 현재 추가한 투두리스트 내용도 request 속성에 설정
		            request.setAttribute("id", todolistVO.getId());
		            request.setAttribute("content", todolistVO.getContent());

		            // JSP 페이지로 포워딩
		            RequestDispatcher view = request.getRequestDispatcher("todolist.jsp");
		            view.forward(request, response);
		        } 
		    }
		}
}