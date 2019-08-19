package ImportCsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.hibernate.Session;

public class ImportSubjects {
	private String className;
	
	public ImportSubjects()
	{
		
	}
	
	@SuppressWarnings("unchecked")
	public void listFilesForFolder(File folder) {
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	this.className = fileEntry.getName().split("\\.")[0];
	            this.readFiles(fileEntry.getAbsolutePath());
	            fileEntry.delete();
	        }
	    }
	}
	
	public void processData()
	{
		File folder = new File("upload/schedules");
		this.listFilesForFolder(folder);
	}
	
	public void readFiles(String fileName) 
	{
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<String> subjectList = new ArrayList<String>();
        Session ss = SessionUtil.session();
        try {
        	ss.beginTransaction();
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName), "UTF-8"));
            while ((line = br.readLine()) != null) {
            	subjectList.add(line);
                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                String subjectId = data[1];
                String subjectName = data[2];
                String room = data[3];
                ss.save(new Subject(subjectId, subjectName, room, this.className));                    
            }
            ss.getTransaction().commit();
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        	ss.getTransaction().rollback();
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
	
	public void process() 
	{
		this.processData();
	}
}
