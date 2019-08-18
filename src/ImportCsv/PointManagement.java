package ImportCsv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

public class PointManagement {
	
	private int passedQuantity = 0;
	
	private int failedQuantity = 0;
	
	public void showPointOfStudentByClassName(String className) 
	{
		Session ss = SessionUtil.session();
		try {
			ss.beginTransaction();
			String hql = "from Point p, Student st where st.MSSV = p.MSSV and p.className = :className";
			Query query = ss.createQuery(hql);
			query.setParameter("className", className);
			List l = query.list();
			if(l.size() > 0) {
				System.out.println("Thông tin điểm lớp " + className);
				System.out.println("STT\tMSSV\tHọ tên\t\tĐiểm GK\t\tĐiểm CK\t\tĐiểm khác\t\tĐiểm tổng\t\tĐậu(x)");
				for(int i = 0; i <= l.size()-1 ; i++) {
					Object[] row = (Object[]) l.get(i);
					Student student = (Student)row[1];
					Point p = (Point) row[0];
					
					if(p.getSumPoint() >= 5) this.passedQuantity++;
					else this.failedQuantity++;
					
					System.out.println((i+1) + "\t" + 
							student.MSSV + "\t" + 
							student.name + "\t\t" + 
							p.getMiddlePoint() + "\t\t" + 
							p.getFinalPoint() + "\t\t" + 
							p.getOtherPoint() + "\t\t" + 
							p.getSumPoint() + "\t\t" +
							(p.getSumPoint() >= 5 ? "X" : ""));
				}
				System.out.println("Số lượng đậu: " + this.passedQuantity + ", rớt: " + this.failedQuantity);
				ss.getTransaction().commit();
			} else {
				System.out.println("Bạn không có tên trong lớp " + className);
			}
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println(e.getMessage());		
		}	
	}
	
	public void showPointOfStudentByClassName(String className, boolean isStudent)
	{
		Session ss = SessionUtil.session();
		try {
			ss.beginTransaction();
			String hql = "from Point p, Student st where st.MSSV = p.MSSV and p.className = :className and st.MSSV=:name";
			Query query = ss.createQuery(hql);
			query.setParameter("className", className);
			query.setParameter("name", Run.clientUser.getUserName());
			List l = query.list();
			if (l.size() > 0) {
				System.out.println("Thông tin điểm lớp " + className);
				System.out.println("STT\tMSSV\tHọ tên\t\tĐiểm GK\t\tĐiểm CK\t\tĐiểm khác\t\tĐiểm tổng\t\tĐậu(x)");
				for(int i = 0; i <= l.size()-1 ; i++) {
					Object[] row = (Object[]) l.get(i);
					Student student = (Student)row[1];
					Point p = (Point) row[0];
					
					if(p.getSumPoint() >= 5) this.passedQuantity++;
					else this.failedQuantity++;
					
					System.out.println((i+1) + "\t" + 
							student.MSSV + "\t" + 
							student.name + "\t\t" + 
							p.getMiddlePoint() + "\t\t" + 
							p.getFinalPoint() + "\t\t" + 
							p.getOtherPoint() + "\t\t" + 
							p.getSumPoint() + "\t\t" +
							(p.getSumPoint() >= 5 ? "X" : ""));
				}
				ss.getTransaction().commit();
			} else {
				System.out.println("Danh sách điểm lớp " + className + " không tồn tại");
			}
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println(e.getMessage());
		}	
	}
	
	public void updatePointsByStudentId(String gradeName,String MSSV, Point point)
	{
		Session ss = SessionUtil.session();
		try {
			ss.beginTransaction();
			String hql = "update Point set middlePoint = :middle, otherPoint = :other, finalPoint = :final, sumPoint = :sum where className = :className and MSSV =:MSSV";
			Query query = ss.createQuery(hql);
			query.setParameter("middle", point.middlePoint);
			query.setParameter("other", point.otherPoint);
			query.setParameter("final", point.finalPoint);
			query.setParameter("sum", point.sumPoint);
			query.setParameter("className", gradeName);
			query.setParameter("MSSV", MSSV);
			int rs = query.executeUpdate();
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			System.out.println(e.getMessage());
			System.out.println("Không tồn tại lớp " + gradeName);
		}	
	}
}
