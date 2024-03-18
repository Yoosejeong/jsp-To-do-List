package tukorea.web.club.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tukorea.web.club.domain.PersonVO;
import tukorea.web.club.domain.TodoListVO;
import tukorea.web.club.persistence.PersonDAO;
import tukorea.web.club.persistence.TodoListDAO;


/**
 * Servlet implementation class RestServlet
 */
@WebServlet("/RestServlet")
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String cmdReq;
		cmdReq = request.getParameter("cmd");
		PersonDAO personDAO = new PersonDAO();
		JSONArray arrayJson=new JSONArray();
		
		
		if (cmdReq.equals("read")) {
		    try {
		        String id = request.getParameter("id");
		        if (id == null) {
		            out.print("계정을 확인하세요");
		            return;
		        }
		       
				PersonVO person = personDAO.read(id);
		        if (person != null) {
		            out.print("ID: " + person.getId() + "<br>");
		            out.print("Password: " + person.getPasswd() + "<br>");
		            out.print("Username: " + person.getUsername()+"<br>");
		            out.print("Mobile: " + person.getMobile() + "<br>");
		            out.print("Email: " + person.getEmail() + "<br>");
		        } else {
		            out.print("해당 학생 정보를 찾을 수 없습니다.");
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        out.print("알 수 없는 오류가 발생했습니다.");
		    }
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
