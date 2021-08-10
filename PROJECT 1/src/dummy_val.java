
public class dummy_val {

	private String username = "";
	private String pass = "";
	
	public void setUsername(String username) {
		if(username.length() > 6) {
			this.username = username;
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPass(String pass) {
		if(pass.contains("@") && pass.length()<11) {
			this.pass = pass;
		}
	}
	
	public String getPass() {
		return pass;
	}
}
