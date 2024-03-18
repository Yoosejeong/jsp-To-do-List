package tukorea.web.club.domain;

public class PersonVO {
	private String id;
	private String passwd;
	private String username;
	private String mobile;
	private String email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;	
		}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd=passwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username=username;	
		}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile=mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	
}
