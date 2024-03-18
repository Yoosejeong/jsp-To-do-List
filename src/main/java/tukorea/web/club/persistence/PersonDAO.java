package tukorea.web.club.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tukorea.web.club.domain.PersonVO;
public class PersonDAO {
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
	
	public boolean add( PersonVO vo) {
		connect();
		String sql = "insert into person values (?,?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getUsername());
			pstmt.setString(4, vo.getMobile());
			pstmt.setString(5, vo.getEmail());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	  // 로그인을 위한 메서드
    public String login(String id, String passwd) {
        connect();

        String sql = "SELECT * FROM person WHERE id=? AND passwd=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, passwd);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                // 만약 ResultSet에 데이터가 있다면 로그인 성공
                if (resultSet.next()) {
                    // 사용자 id 반환
                    return resultSet.getString("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect(); // connect 메서드에서 예외 발생 시에도 disconnect 호출
        }
        // 로그인 실패
        return null;
    }
    // ID를 기반으로 사용자 정보 검색
    public PersonVO getUserInfo(String userId) {
        connect();
        PersonVO user = null;

        String sql = "SELECT * FROM person WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    // PersonVO에 사용자 정보 채우기
                    user = new PersonVO();
                    user.setId(resultSet.getString("id"));
                    user.setPasswd(resultSet.getString("passwd"));
                    user.setUsername(resultSet.getString("username"));
                    user.setMobile(resultSet.getString("mobile"));
                    user.setEmail(resultSet.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return user;
    }
    
 // 비밀번호 업데이트 메서드
    public boolean updatePassword(String userId, String newPassword) {
        connect();
        String sql = "UPDATE person SET passwd = ? WHERE id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, userId);
            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
    }
    
    public PersonVO read(String id) {
	    connect();
	    String sql = "SELECT * FROM person WHERE id = ?";
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            PersonVO person = new PersonVO();
	            person.setId(rs.getString("id"));
	            person.setPasswd(rs.getString("passwd"));
	            person.setUsername(rs.getString("username"));
	            person.setMobile(rs.getString("mobile"));
	            person.setEmail(rs.getString("email"));
	            rs.close();
	            return person;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        disconnect();
	    }
	    return null;
	}

  
}
