package tinker;

public class Post {
	private String comment, user;
	
	public Post(String comment, String user) {
		this.comment = comment;
		this.user = user;
	}
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String toString(){
		return "Comment: " + comment + ", user: " + user;
	}


	
}
