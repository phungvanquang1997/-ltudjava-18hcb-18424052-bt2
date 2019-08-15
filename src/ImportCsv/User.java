package ImportCsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
	
	@Id
	@Column(name="id")
	public int id;
	
	@Column(name="username")
	public String userName;
	
	@Column(name="password")
	public String passWord;
	
	@Column(name="role")
	public int Role;
	
	public static ArrayList<User> userList = new ArrayList<User>();
	static int isChangedPassword = 0;
	
	public User() {}
	public User(String newUserName, String newPassWord, int Role) {
		this.userName = newUserName;
		this.passWord = newPassWord;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String newUsername) {
		this.userName = newUsername;
	}
	
	public String getPassWord() {
		return this.passWord;
	}
	
	public void setPassWord(String newPassWord) {
		this.passWord = newPassWord;
	}
	
	public boolean hasLogged(User clientUser)
	{
		for(User user : userList) {
			if(user.getUserName().equals(clientUser.getUserName()) && 
					user.getPassWord().equals(clientUser.getPassWord())) {
				return true;
			}
		}
		return false;
	}
	
	public void changePassword(User clientUser, String newPassword)
	{
		for(User user : userList) {
			if(user.getUserName().equals(clientUser.getUserName()) && 
					user.getPassWord().equals(clientUser.getPassWord())) {
				this.setPassWord(newPassword);
				userList.remove(user);
				userList.add(new User(getUserName(), getPassWord()));
				isChangedPassword = 1;
				break;
			}
		}
	}
	
	public static void saveUsers() throws Exception {
		String fileName = "upload/users/users.txt";
        BufferedReader br = null;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
        writer.flush();
        for(User user : userList) {
        	writer.write(user.getUserName() + " " + user.getPassWord());
        	writer.newLine();
        }
        writer.close();
	}
}
