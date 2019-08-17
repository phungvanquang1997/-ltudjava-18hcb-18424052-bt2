package ImportCsv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.hibernate.Session;

public class ImportUser {

	public void importUser() 
	{
		String csvFile = "upload/users/users.txt";
        BufferedReader br = null;
        String line = "";
        String splitBy = " ";
        ArrayList<String> subjectList = new ArrayList<String>();
        char hasRead = '0';
        Session session = SessionUtil.session();
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(csvFile), "UTF-8"));
            session.beginTransaction();
            while ((line = br.readLine()) != null) {
            	subjectList.add(line);
            	String[] data = line.split(splitBy);
            	String userName = data[0];
            	String passWord = data[1];
            	session.save(new User(userName, passWord, userName.equals("giaovu") ? 1 : 0));
            	hasRead = '1';
            }
            if(hasRead == '1') {
            	System.out.println("Imported users file is successful !");
            } else {
            	System.out.println("Imported users file is failure !");
            }
            session.getTransaction().commit();
        } catch (Exception e) {
        	session.getTransaction().rollback();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
