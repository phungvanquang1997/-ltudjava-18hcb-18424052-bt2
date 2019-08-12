package ImportCsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Grade {
	private HashMap<String, ArrayList<Student>> classes;
	
	private ArrayList<Student> students;
	
	private String classId;
	
	private String className;
	
	public Grade()
	{
		this.classes = new HashMap();
		this.students = new ArrayList<Student>();
	}
	
	public void createClass(String className, ArrayList<Student> students)
	{
		this.className = className;
		this.students = students;
		this.classes.put(className, students);
	}
	
	public ArrayList getStudentsInClass()
	{
		return this.students;
	}
	
	public String getClassName()
	{
		return this.className;
	}
	
	public void showStudentList()
	{
		System.out.println("Danh sách sinh viên");
		try {
			for(Student student: this.students)
			{
				System.out.println(student.name + "-" + student.MSSV + "-" + student.gender + "-" + student.CMND);
			}
		} catch(Exception e) {
			System.out.println("Không tồn tại sinh viên");
		}
	}
	
	public void showStudentListByClassName(String className)
	{
		ArrayList<Student> students = this.classes.get(className);
		System.out.println("Danh sách lớp " + className);
		try {
	    	for(Student student: students) {
				System.out.println(student.name + "-" + student.MSSV + "-" + student.gender + "-" + student.CMND);
	    	}
		} catch(Exception e)
		{
			System.out.println("Không có sinh viên trong lớp này");
		}
	}
	
	public void addStudent(Student newStudent)
	{
		this.students.add(newStudent);
	}
	
	public void addStudent(Student newStudent, String optionClass)
	{
		ArrayList<Student> temp = this.classes.get(optionClass);
		this.addStudent(newStudent);
		if(temp != null) {
			temp.add(newStudent);
			this.classes.remove(optionClass);
			this.classes.put(optionClass, temp);
		}
		else {
			ArrayList<Student> newStudentList = new ArrayList<Student>();
			newStudentList.add(newStudent);
			this.classes.put(optionClass, newStudentList);
		}
	}
	
	public void writeFiles() throws IOException
	{
		String folderName = "upload/students";
		int i = 1;
		for(Map.Entry<String, ArrayList<Student>> studentInclass : this.classes.entrySet()) {
		    String key = studentInclass.getKey();
		    ArrayList<Student> value = studentInclass.getValue(); 
	        BufferedWriter writer = new BufferedWriter(new FileWriter(folderName + "/" + key + ".csv", false));
	        writer.flush();
		    for (Student student: value) {
		    	writer.write(i + "," + student.MSSV + "," + student.name + "," + student.gender + "," + student.CMND);
		    	writer.newLine();
		    	i++;
		    }
		    writer.close();   
		}
	}
}

