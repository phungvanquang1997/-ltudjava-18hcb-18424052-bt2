package ImportCsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ImportPoints {
	
	private ArrayList<Student> pointList;
	
	private Point pointInformation;
	
	private PointManagement pointManagement;
	
	public ImportPoints(PointManagement pointManagement)
	{
		this.pointList = new ArrayList();
		this.pointManagement = pointManagement;
	}
	

	@SuppressWarnings("unchecked")
	public void listFilesForFolder(File folder) {
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            this.readFiles(fileEntry.getAbsolutePath());
	            this.pointManagement.create(fileEntry.getName().split("\\.")[0], this.pointList);
	            fileEntry.delete();
	        }
	    }
	}
	
	public void processData()
	{
		File folder = new File("upload/points");
		this.listFilesForFolder(folder);
	}
	
	public void readFiles(String fileName) 
	{
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<String> subjectList = new ArrayList<String>();
        try {

            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName), "UTF-8"));
            while ((line = br.readLine()) != null) {
            	subjectList.add(line);
                // use comma as separator
            	String[] data = line.split(cvsSplitBy);
            	String MSSV = data[1];
            	String name = data[2];
            	
                float middlePoint = Float.parseFloat(data[3]);
                float finalPoint = Float.parseFloat(data[4]);
                float otherPoint = Float.parseFloat(data[5]);
                float sumPoint = Float.parseFloat(data[6]);
                
                this.pointInformation = new Point(middlePoint, finalPoint, otherPoint, sumPoint);
                Student student = new Student();
                student.setMSSV(MSSV);
                student.setName(name);
                student.setPoint(this.pointInformation);
                
                this.pointList.add(student);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
