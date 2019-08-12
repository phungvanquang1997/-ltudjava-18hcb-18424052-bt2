package ImportCsv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PointManagement {
	public ArrayList<Student> studentPointList;
	
	private HashMap<String, ArrayList<Student>> students = new HashMap<String, ArrayList<Student>>();
	
	private int passedQuantity = 0;
	
	private int failedQuantity = 0;
	
	public void showPointOfStudentByClassName(String className) 
	{
		ArrayList<Student> studentPointList = this.students.get(className);
		System.out.println("Thông tin điểm lớp " + className);
		System.out.println("STT\tMSSV\tHọ tên\t\tĐiểm GK\t\tĐiểm CK\t\tĐiểm khác\t\tĐiểm tổng\t\tĐậu(x)");
		int i = 1;
		try {
			for(Student student : studentPointList)
			{
				System.out.println(i + "\t" + 
						student.MSSV + "\t" + 
						student.name + "\t\t" + 
						student.getPoint().getMiddlePoint() + "\t\t" + 
						student.getPoint().getFinalPoint() + "\t\t" + 
						student.getPoint().getOtherPoint() + "\t\t" + 
						student.getPoint().getSumPoint() + "\t\t" +
						(this.isPassed(student.getPoint()) == true ? "X" : ""));
				i++;
			}
			System.out.println("Số lượng đậu: " + this.passedQuantity + ", rớt: " + this.failedQuantity);
		} catch(Exception e) {
			System.out.println("Danh sách điểm lớp" + className + " không tồn tại");
		}	
	}
	
	public void showPointOfStudentByClassName(String className, boolean isStudent)
	{
		ArrayList<Student> studentPointList = this.students.get(className);
		try {
			System.out.println("MSSV\tHọ tên\t\tĐiểm GK\t\tĐiểm CK\t\tĐiểm khác\t\tĐiểm tổng\t\tĐậu(x)");
			for(Student student : studentPointList)
			{
				if (isStudent && student.MSSV.equals(Run.clientUser.getUserName())) {
					System.out.println(student.MSSV + "\t" + 
							student.name + "\t\t" + 
							student.getPoint().getMiddlePoint() + "\t\t" + 
							student.getPoint().getFinalPoint() + "\t\t" + 
							student.getPoint().getOtherPoint() + "\t\t" + 
							student.getPoint().getSumPoint() + "\t\t" +
							(this.isPassed(student.getPoint()) == true ? "X" : ""));
					break;
				}
			}
		} catch(Exception e) {
			System.out.println("Điểm lớp " + className + " không tồn tại");
		}	
	}
	
	public void create(String gradeName, ArrayList<Student> studentPointList)
	{
		this.studentPointList = studentPointList;
		this.students.put(gradeName, studentPointList);
	}
	
	public boolean isPassed(Point point)
	{ 
		if (point.isPassed()) {
			this.passedQuantity++;
		} else {
			this.failedQuantity++;
		}
		return point.isPassed();
	}
	
	public void writeFiles() throws IOException
	{
		String folderName = "upload/points";
		int i = 1;
		for(Map.Entry<String, ArrayList<Student>> studentInclass : this.students.entrySet()) {
		    String key = studentInclass.getKey();
		    ArrayList<Student> value = studentInclass.getValue(); 
	        BufferedWriter writer = new BufferedWriter(new FileWriter(folderName + "/" + key + ".csv", false));
	        writer.flush();
		    for (Student student: value) {
		    	writer.write(i + "," + student.MSSV + "," + student.name + "," 
		    			+ student.getPoint().middlePoint + ","
		    			+ student.getPoint().finalPoint + ","
		    			+ student.getPoint().otherPoint + ","
		    			+ student.getPoint().sumPoint);
		    	writer.newLine();
		    	i++;
		    }
		    writer.close();   
		    
		}
	}
	
	public void updatePointsByStudentId(String gradeName,String MSSV, Point point)
	{
		ArrayList<Student> students = this.students.get(gradeName);
		try {
			for(Student student: students)
			{
				if(student.MSSV.equals(MSSV)) {
					student.setPoint(point);
				}
			}
		} catch (Exception e) {
			System.out.println("Không tồn tại lớp học " + gradeName);
		}
	}
}
