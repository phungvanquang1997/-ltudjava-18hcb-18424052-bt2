package ImportCsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class User {
	public String userName;
	public String passWord;
	RoleManagement Role;
	public static ArrayList<User> userList = new ArrayList<User>();
	static int isChangedPassword = 0;
	
	public User(String newUserName, String newPassWord) {
		this.userName = newUserName;
		this.passWord = newPassWord;
		
		if(userName.equals("giaovu")) {
			this.Role = new RoleManagement(1, "giaovu");
		} else {
			this.Role = new RoleManagement(0, "students");
		}
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
