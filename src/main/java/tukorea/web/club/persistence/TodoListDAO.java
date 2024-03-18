package tukorea.web.club.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import java.sql.Statement;

import tukorea.web.club.domain.TodoListVO;

public class TodoListDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost/jspdb?allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
	
	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "jspbook","passwd");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void disconnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean add(TodoListVO vo) {
	    connect();
	    String sql = "INSERT INTO todolist (id, content) VALUES (?, ?)";
	    try {
	        pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        pstmt.setString(1, vo.getId());
	        pstmt.setString(2, vo.getContent());
	        pstmt.executeUpdate();

	        System.out.println("Insert successful");

	        // 생성된 투두리스트의 번호를 가져오기
	        ResultSet generatedKeys = pstmt.getGeneratedKeys();
	        if (generatedKeys.next()) {
	        	pstmt.setString(1, vo.getNumber());
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // 예외 출력
	        System.out.println("Error: " + e.getMessage()); // 에러 메시지 출력
	        return false;
	    } finally {
	        disconnect();
	    }
	    return true;
	}
	
	public boolean delete(String todoId, String todoContent) {
	    connect();
	    String sql = "DELETE FROM todolist WHERE id = ? AND content = ?";
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, todoId);
	        pstmt.setString(2, todoContent);
	        int rowsAffected = pstmt.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("TodoList deleted successfully");
	            return true;
	        } else {
	            System.out.println("TodoList not found or failed to delete");
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        disconnect();
	    }
	}
	
	public ArrayList<TodoListVO> getTodoList(String userId) {
	    connect();
	    ArrayList<TodoListVO> todolist = new ArrayList<TodoListVO>();
	    String sql = "SELECT * FROM todolist WHERE id = ?";
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, userId);
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            TodoListVO vo = new TodoListVO();
	        
	            vo.setId(rs.getString("id"));
	            vo.setContent(rs.getString("content"));

	            todolist.add(vo);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        disconnect();
	    }
	    return todolist;
	}
	
	
	
	
}