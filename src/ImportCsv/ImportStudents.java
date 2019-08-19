package ImportCsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.hibernate.Session;

public class ImportStudents {
	private ArrayList studentList;
	
	private Student studentInformation;
	
	private Grade classes;
	
	private String className;
	
	public ImportStudents()
	{
		this.studentList = new ArrayList();
	}
	
	public void listFilesForFolder(File folder) throws IOException {
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	this.className = fileEntry.getName().split("\\.")[0];
	            this.readFiles(fileEntry.getAbsolutePath());	         
	            //this.classes.createClass(fileEntry.getName().split("\\.")[0], this.studentList);	        
	            fileEntry.delete();
	        }
	    }
	}
	
	public void processData() throws IOException
	{
		File folder = new File("upload/students");
		this.listFilesForFolder(folder);
		Session ss = SessionUtil.session();
		ss.beginTransaction();
		Grade grade = new Grade(this.className);
       	ss.save(grade);
        ss.getTransaction().commit();
	}
	
	public void readFiles(String fileName) 
	{
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<String> studentList = new ArrayList<String>();
        Session session = SessionUtil.session();
        try {
        	try {
	            br = new BufferedReader(new InputStreamReader(
	                    new FileInputStream(fileName), "UTF-8"));
	            session.beginTransaction();
	            while ((line = br.readLine()) != null) {            
	            	studentList.add(line);
	                // use comma as separator
	                String[] data = line.split(cvsSplitBy);
	                String name = data[1];
	                String MSSV = data[2];
	                String gender = data[3];
	                String CMND = data[4];        
	                Student student = new Student(name, MSSV, gender, CMND, this.className);
	                session.save(student);              	   	             
	            }
	            session.getTransaction().commit();     
        	} catch (Exception e) {
        		System.out.println(e.getMessage());
        		session.getTransaction().rollback();
        	}
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
	
	public void process() throws IOException 
	{	
		processData();
	}
}
