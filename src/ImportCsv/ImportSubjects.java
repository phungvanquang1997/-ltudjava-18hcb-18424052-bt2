package ImportCsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ImportSubjects {
	private ArrayList<Subject> subjectList;
	
	private Subject subjectInformation;
	
	private Schedule schedule;
	
	public ImportSubjects(Schedule schedule)
	{
		this.subjectList = new ArrayList<Subject>();
		this.schedule = schedule;
	}
	
	@SuppressWarnings("unchecked")
	public void listFilesForFolder(File folder) {
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            this.readFiles(fileEntry.getAbsolutePath());
	            this.schedule.create(fileEntry.getName().split("\\.")[0], this.subjectList);
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
        try {

            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName), "UTF-8"));
            while ((line = br.readLine()) != null) {
            	subjectList.add(line);
                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                String subjectId = data[1];
                String subjectName = data[2];
                Room room = new Room(data[3]);
                this.subjectInformation = new Subject(subjectId, subjectName, room);
                this.subjectList.add(this.subjectInformation);
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
