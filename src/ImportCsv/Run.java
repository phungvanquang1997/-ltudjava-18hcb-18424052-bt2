package ImportCsv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Run {
	private static int accessiable = 0;
	
	public static User clientUser;
	
	public static void login()
	{
		do {
		Scanner inp = new Scanner(System.in);
		String userName, passWord;
		System.out.println("==================================================");
		System.out.println("\t\t Đăng nhập hệ thống");
		System.out.print("\t Tên đăng nhập: ");
		userName = inp.nextLine();
	    System.out.print("\t Mật khẩu : "); 
	    passWord = inp.nextLine();  
	    clientUser = new User(userName, passWord);
		} while (!clientUser.hasLogged(clientUser));
	}
	
	public static void menu() throws Exception
	{
		ImportUser impUser = new ImportUser();
		impUser.importUser();
		login();
	
		Grade classes = new Grade();
		Schedule schedule = new Schedule();
		PointManagement pointManagement = new PointManagement();

		int choice;
	    do {
			System.out.println("=============================================");
			System.out.println("\t\tQuản lý sinh viên");
			System.out.println("1. Import danh sách lớp");
			System.out.println("2. Import thời khóa biểu");
			System.out.println("3. Import điểm số");
			System.out.println("4. Xem danh sách lớp");
			System.out.println("5. Xem thời khóa biểu");
			System.out.println("6. Xem bảng điểm");
			System.out.println("7. Nhập điểm sinh viên");
			System.out.println("8. Thêm sinh viên");
			System.out.println("9. Đổi mật khẩu");
			System.out.println("0. Thoát");
			Scanner inp = new Scanner(System.in);
		    System.out.print("Lựa chọn của bạn : "); 
		    choice = inp.nextInt(); 
		    
			switch(choice) {
				case 1:
				{
					ImportStudents importCsv = new ImportStudents(classes);
			    	importCsv.process();
			    	break;
				}
				case 2:
				{
					ImportSubjects importSubject = new ImportSubjects(schedule);
					importSubject.process();
					break;
				}
				case 3:
				{
					ImportPoints importPoints = new ImportPoints(pointManagement);
					importPoints.process();
					break;
				}
				case 4:
				{
					Scanner sc = new Scanner(System.in);
					String className;
					System.out.println("Nhập tên lớp muốn xem: "); 
					className = sc.nextLine();
					classes.showStudentListByClassName(className);
					break;
				}
				case 5:
				{
					Scanner sc = new Scanner(System.in);
					String className;
					System.out.println("Nhập tên lớp muốn xem: "); 
					className = sc.nextLine();
					schedule.showScheduleByGradeName(className);
					break;
				}
				case 6:
				{
					Scanner sc = new Scanner(System.in);
					String className;
					System.out.println("Nhập tên lớp muốn xem: "); 
					className = sc.nextLine();
					if(clientUser.Role.isAccessable() == 1) {
						pointManagement.showPointOfStudentByClassName(className);
					} else {
						pointManagement.showPointOfStudentByClassName(className, true);
					}
					break;
				}
				case 7:
				{
					String gradeName;
					String MSSV;
					float middlePoint, finalPoint, otherPoint, sumPoint;
					Scanner sc = new Scanner(System.in);
					System.out.println("Nhập tên lớp của sinh viên: "); 
					gradeName = sc.nextLine();
					System.out.println("Nhập mã số sinh viên: "); 
					MSSV = sc.nextLine();
					System.out.println("Nhập diểm giữa kì: "); 
					middlePoint = sc.nextFloat();
					System.out.println("Nhập điểm cuối kì: "); 
					finalPoint = sc.nextFloat();
					System.out.println("Nhập điểm khác: "); 
					otherPoint = sc.nextFloat();
					System.out.println("Nhập điểm tổng: "); 
					sumPoint = sc.nextFloat();
					pointManagement.updatePointsByStudentId(gradeName, MSSV, new Point(middlePoint, finalPoint, otherPoint, sumPoint));
					System.out.println("cập nhật điểm thành công!"); 
					break;
				}
				case 8:
				{
					Scanner sc = new Scanner(System.in);
					String MSSV, name, gender, CMND, studentClass;
					System.out.println("\t\t Thêm sinh viên mới"); 
			        System.out.println("MSSV: "); 
			        MSSV = sc.nextLine();
			        System.out.println("Name: "); 
			        name = sc.nextLine();
			        System.out.println("Gender: "); 
			        gender = sc.nextLine();
			        System.out.println("CMND: "); 
			        CMND = sc.nextLine();
			        System.out.println("Class name: ");
			        studentClass = sc.nextLine();
			        classes.addStudent(new Student(name, MSSV, gender, CMND), studentClass);
			        break;
				}
				case 9:
				{
					String newPassword;
					Scanner sc = new Scanner(System.in);
					System.out.println("Nhập mật khẩu mới: "); 
					newPassword = sc.nextLine();
					clientUser.changePassword(clientUser, newPassword);
					System.out.println("Thay đổi mật khẩu thành công!"); 
					break;
				}
				case 0:
					clientUser.saveUsers();
					classes.writeFiles();
					pointManagement.writeFiles();
					System.exit(0);
				default:
					break;
			}
	    }while(choice != 0);
	}
	
	public static void main(String[] args)  {
		ImportPoints imp = new ImportPoints();
		try {
			imp.process();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//menu();
	}

}
