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
		boolean isLogged = false;
		do {
			Scanner inp = new Scanner(System.in);
			String userName, passWord;
			System.out.println("==================================================");
			System.out.println("\t\t Đăng nhập hệ thống");
			System.out.print("\t Tên đăng nhập: ");
			userName = inp.nextLine();
		    System.out.print("\t Mật khẩu : "); 
		    passWord = inp.nextLine();  
		    clientUser = new User(userName, passWord, userName.equals("giaovu") ? 1 : 0);
		    isLogged = clientUser.findUser(userName, passWord);
		} while (!isLogged);
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
					ImportStudents importCsv = new ImportStudents();
			    	importCsv.process();
			    	break;
				}
				case 2:
				{
					ImportSubjects importSubject = new ImportSubjects();
					importSubject.process();
					break;
				}
				case 3:
				{
					ImportPoints importPoints = new ImportPoints();
					importPoints.process();
					break;
				}
				case 4:
				{
					Scanner sc = new Scanner(System.in);
					String className;
					System.out.println("Nhập tên lớp muốn xem: "); 
					className = sc.nextLine();
					List l = classes.findStudentsInClasses(className);
					if (l != null) {
						System.out.println("Thông tin lớp " + className);
						System.out.println("STT\t| Hoten \t| MSSV \t| gender \t| CMND \t");
						for(int i = 0; i <= l.size()-1 ; i++) {
							Object[] row = (Object[]) l.get(i);
							Student st = (Student) row[1];						
							System.out.println((i+1) + "\t" + st.getName() + "\t" + st.getMSSV() + "\t" + st.getGender() + "\t" + st.getCMND());
						}					
					} 
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
					if(clientUser.Role == 1) {
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
			        Session ss = SessionUtil.session(); 
			        try {
			        	 ss.beginTransaction();
			        	 ss.save(new Student(name, MSSV, gender, CMND, studentClass));
			        	 ss.getTransaction().commit();
			        	 System.out.println("Added student is successful!");
			        } catch (Exception e) {
			        	System.out.println(e.getMessage());
			        	ss.getTransaction().rollback();
			        }
			        
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
					System.exit(0);
				default:
					break;
			}
	    }while(choice != 0);
	}
	
	public static void main(String[] args) throws Exception  {
		Run.menu();
	}

}
