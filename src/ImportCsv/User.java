package ImportCsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;

@Entity
@Table(name="User")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
		this.Role = Role;
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
		Session ss = SessionUtil.session();
		String hql = "UPDATE from User set passWord=:password where userName=:username";
		try {
			ss.beginTransaction();
			Query query = ss.createQuery(hql);
			query.setParameter("username", clientUser.userName);
			query.setParameter("password", newPassword);
			int rs = query.executeUpdate();
			System.out.println(rs);
			ss.getTransaction().commit();
		} catch(Exception e) {
			System.out.println(e.getMessage());
			ss.getTransaction().rollback();
		}
	}
	
	public boolean findUser(String username, String password)
	{
		Session ss = SessionUtil.session();
		try {
			ss.beginTransaction();
			String hql = "from User where userName=:username and passWord=:password";
			Query query = ss.createQuery(hql);
			query.setParameter("username", username);
			query.setParameter("password", password);
			List<User> l = query.list();
			if (l.size() > 0) {
				return true;
			}
			ss.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ss.getTransaction().rollback();
		}
		return false;
	}
}
