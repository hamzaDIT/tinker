package tinker;

public class User {
	private String userid, username, firstName, lastName, email, password, birthday, gender;
	
	

	public User(String userid, String username, String firstName, String lastName, String email, String password, String birthday,
			String gender) {
		this.userid=userid;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
	}
	public User(){
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String toString() {
		return ("user name: "+ this.username+"<br>"+
				"first name: "+this.firstName+"<br>"+
				"last name: "+this.lastName+"<br>"+
				"email: "+ this.email+"<br>"+
				"birthday: "+ this.birthday+"<br>"+
				"gender: "+ this.gender);
				
	}
}
