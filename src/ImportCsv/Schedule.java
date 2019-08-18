package ImportCsv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class Schedule<T> {
	public ArrayList<Subject> subjectList;
	
	private HashMap<String, ArrayList<Subject>> schedule = new HashMap<String, ArrayList<Subject>>();
	
	public void create(String gradeName, ArrayList<Subject> subjectList)
	{
		this.subjectList = subjectList;
		schedule.put(gradeName, subjectList);
	}
	
	public void showScheduleByGradeName(String gradeName) {
		Session ss = SessionUtil.session();
		List<Subject> l = null;
		try {
			ss.beginTransaction();
			String hql = "FROM Subject where className = :gradeName";
			Query query = ss.createQuery(hql);
			query.setParameter("gradeName", gradeName);
			l = query.list();
			ss.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ss.getTransaction().rollback();
		}
		System.out.println("| STT " + "|" + " Mã môn " + "|" + " Tên môn " + "|" + " Phòng học |");
		try {
			for(int i = 0; i < l.size()-1 ; i++) {
				Subject st = l.get(i);						
				System.out.println((i+1) + "\t" + st.getSubjectId() + "\t" + st.getSubjectName() + "\t" + st.getRoom());
			}		
		} catch (Exception e) {
			System.out.println("Không tồn tại thời khóa biểu của lớp " + gradeName);
		}
	}
}
