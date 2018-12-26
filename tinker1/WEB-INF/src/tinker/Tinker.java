package tinker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;


public class Tinker extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	//instance variables for the user object
	private String email, password, username, firstName, lastName, dob, gender;
	//arraylists of users and friends
	private ArrayList<User> users=new ArrayList<User>();
	private ArrayList<String> friends=new ArrayList<String>();
	private ArrayList<String> friendsInverse=new ArrayList<String>();// Example: if John adds Tommy from John's profile, then this arraylist makes sure that John is also added as a friend in Tommy's profile. This may not be an optimal solution but it is the only one I know how to implement.
	//sessions
	private Map<String, Object> session;
	//for adding friend
	private String friendUsername;
	//for posting a comment
	private String friend, comment;
	//for getting posts
	private ArrayList<Post> posts=new ArrayList<Post>();
	
	
	public Tinker(){
		
	}
	
	

	

	public Tinker(String email, String password, ArrayList<User> users) {
		
		this.email = email;
		this.password = password;
		this.users = users;
		
		
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	
	
	public String profile() {
		return "SUCCESS";
		
	}
	
	public String addingFriend() {
		User userSession =(User) session.get("currentUser");
		try {
			Connection connection;
			// connect to database
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tinker", "root", "root");
			// insert data
			Statement select = connection.createStatement();
			ResultSet resultSet = select.executeQuery("SELECT * FROM users WHERE userid !="+userSession.getUserid());
			if (resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					User user=new User(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7), resultSet.getString(8));
					users.add(user);
				}
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(User u : users) {
			if(this.friendUsername.equals(u.getUsername())) {
				addUserToFriendDatabase(u);
				return "SUCCESS";
			}
		}
		return "FAIL";
	}
	
	private void addUserToFriendDatabase(User user) {
		User u =(User) session.get("currentUser");
		try {
			Connection connection;
			// connect to database
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tinker", "root", "root");
			// insert data
			PreparedStatement createFriend = connection.prepareStatement("INSERT into friends(username, userid)" + "VALUES(?,?)");
			createFriend.setString(1, u.getUsername());
			createFriend.setString(2, user.getUserid());
			@SuppressWarnings("unused")
			int rowsUpdated = createFriend.executeUpdate();
			createFriend.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		try {
			Connection connection;
			// connect to database
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tinker", "root", "root");
			// insert data
			PreparedStatement createFriend = connection.prepareStatement("INSERT into friends(username, userid)" + "VALUES(?,?)");
			createFriend.setString(1, user.getUsername());
			createFriend.setString(2, u.getUserid());
			@SuppressWarnings("unused")
			int rowsUpdated = createFriend.executeUpdate();
			createFriend.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	
	public String viewFriends(){
		try {
			Connection connection;
			// connect to database
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tinker", "root", "root");
			// insert data
			Statement select = connection.createStatement();
			User user =(User) session.get("currentUser");
			ResultSet resultSet = select.executeQuery("SELECT * FROM friends WHERE userid ="+user.getUserid());
			if (resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					this.friends.add(resultSet.getString(2));
				}
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(!(friends.isEmpty())){
			return "SUCCESS";
		}
		return "FAIL";
	}
	
	public String viewAllMembers(){
		
		try {
			Connection connection;
			// connect to database
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tinker", "root", "root");
			// insert data
			Statement select = connection.createStatement();
			User u =(User) session.get("currentUser");
			ResultSet resultSet = select.executeQuery("SELECT * FROM users WHERE userid !="+u.getUserid());
			if (resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					User user=new User(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7), resultSet.getString(8));
					users.add(user);
				}
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(!(users.isEmpty())){
			return "SUCCESS";
		}
		return "FAIL";
	}
	
	
	
	
	public String postToWall(){
		//friend, comment
		User user=findFriend();
		if(user!=null){
			if(!this.comment.equals(null)){
				User u =(User) session.get("currentUser");
				try {
					Connection connection;
					// connect to database
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tinker", "root", "root");
					// insert data
					PreparedStatement createComment = connection.prepareStatement("INSERT into wall(comment, fromuserid, touserid, userid)" + "VALUES(?,?,?,?)");
					createComment.setString(1, this.comment);
					createComment.setString(2, u.getUserid());
					createComment.setString(3, user.getUserid());
					createComment.setString(4, user.getUserid());
					@SuppressWarnings("unused")
					int rowsUpdated = createComment.executeUpdate();
					createComment.close();
					connection.close();
					return "SUCCESS";
				} catch (Exception e) {
					e.printStackTrace();
					return "DATABASEERROR";
				}
				
			}
			else{
				return "NOCOMMENT";
			}
			
		}
		else{
			return "NOTFOUND";
		}
	}
	
	
	
	
	private User findFriend(){
		
		try {
			Connection connection;
			// connect to database
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tinker", "root", "root");
			// insert data
			User u =(User) session.get("currentUser");
			Statement select = connection.createStatement();
			ResultSet resultSet = select.executeQuery("SELECT * FROM users WHERE userid !="+u.getUserid());
			if (resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					User user=new User(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7), resultSet.getString(8));
					users.add(user);
				}
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		for(User u : users) {
			if(friend.equals(u.getUsername())) {
				return u;
			}
		}
		return null;
	}
	
	public String wall(){
		//1.get posts from database input into post objects in postarraylist.
		//2.return WALL if not empty or FAILWALL if empty
		
		//get comments from database:
		User u =(User) session.get("currentUser");
		try {
			Connection connection;
			// connect to database
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tinker", "root", "root");
			// insert data
			Statement select = connection.createStatement();
			ResultSet resultSet = select.executeQuery("SELECT * FROM wall WHERE touserid ="+u.getUserid());
			if (resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					//insert posts into arraylist
					Post post =new Post(resultSet.getString(2), resultSet.getString(4));
					posts.add(post);
				}
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!(posts.isEmpty())){
			return "WALL";
		}
		else{
			return "FAILWALL";
		}
		
	}
	
	
	
	public String login(){
		
		try {
			Connection connection;
			// connect to database
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tinker", "root", "root");
			// insert data
			Statement select = connection.createStatement();
			ResultSet resultSet = select.executeQuery("SELECT * FROM users");
			if (resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					User user=new User(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7), resultSet.getString(8));
					users.add(user);
				}
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		for(User u : users) {
			if((email.equals(u.getEmail()))&&(password.equals(u.getPassword()))) {
				this.session.put("currentUser", u);
				return "WALL";
			}
		}
		return "FAIL";
	}
	
	public String register(){
		
		//if(!(firstName.equals(null))&&(gender.equals(null) )&&(lastName.equals(null))&&(email.equals(null))&&(password.equals(null))&&(dob.equals(null))) {
			try {
				Connection connection;
				// connect to database
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tinker", "root", "root");
				// insert data
				PreparedStatement createUser = connection.prepareStatement("INSERT into users(username, firstName, lastName, email ,password, birthday, gender)" + "VALUES(?,?,?,?,?,?,?)");
				createUser.setString(1, this.username);
				createUser.setString(2, this.firstName);
				createUser.setString(3, this.lastName);
				createUser.setString(4, this.email);
				createUser.setString(5, this.password);
				createUser.setString(6, this.dob);
				createUser.setString(7, this.gender);
				@SuppressWarnings("unused")
				int rowsUpdated = createUser.executeUpdate();
				createUser.close();
				connection.close();
				
				setSessionOfNewUser();
				return "WALL";
			} catch (Exception e) {
				e.printStackTrace();
			}
		//}
		return "FAIL";
		
		
	}
	
	private void setSessionOfNewUser(){
		try {
			Connection connection;
			// connect to database
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tinker", "root", "root");
			// insert data
			Statement select = connection.createStatement();
			ResultSet resultSet = select.executeQuery("SELECT * FROM users");
			if (resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					User user=new User(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7), resultSet.getString(8));
					System.out.println(""+user.toString());
					users.add(user);
				}
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.session.put("currentUser", users.get(users.size()-1));
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





	public String getDob() {
		return dob;
	}





	public void setDob(String dob) {
		this.dob = dob;
	}





	public String getGender() {
		return gender;
	}





	public void setGender(String gender) {
		this.gender = gender;
	}





	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public ArrayList<User> getUsers() {
		return users;
	}



	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	





	





	public String getFriendUsername() {
		return friendUsername;
	}





	public void setFriendUsername(String friendUsername) {
		this.friendUsername = friendUsername;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	public Map<String, Object> getSession() {
		return session;
	}





	public ArrayList<String> getFriends() {
		return friends;
	}





	public void setFriends(ArrayList<String> friends) {
		this.friends = friends;
	}





	public String getComment() {
		return comment;
	}





	public void setComment(String comment) {
		this.comment = comment;
	}





	public String getFriend() {
		return friend;
	}





	public void setFriend(String friend) {
		this.friend = friend;
	}





	public ArrayList<Post> getPosts() {
		return posts;
	}





	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}














	
	
	
}
