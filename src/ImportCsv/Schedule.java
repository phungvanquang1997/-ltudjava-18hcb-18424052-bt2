package ImportCsv;

import java.util.ArrayList;
import java.util.HashMap;

public class Schedule<T> {
	public ArrayList<Subject> subjectList;
	
	private HashMap<String, ArrayList<Subject>> schedule = new HashMap<String, ArrayList<Subject>>();
	
	public void create(String gradeName, ArrayList<Subject> subjectList)
	{
		this.subjectList = subjectList;
		schedule.put(gradeName, subjectList);
	}
	
	public void showAll()
	{
		System.out.println("| STT " + "|" + " Mã môn " + "|" + " Tên môn " + "|" + " Phòng học |");
		int i = 1;
		try {
			for(Subject sb: subjectList)
			{
				System.out.println(i + "|" + sb.subjectId + "|" + sb.subjectName + "|" + sb.room.getRoom());
				i++;
			}
		} catch (Exception e) {
			System.out.println("Không tồn tại thời khóa biểu nào!");
		}
	}
	
	public void showScheduleByGradeName(String gradeName) {
		ArrayList<Subject> subjectList = this.schedule.get(gradeName);
		System.out.println("| STT " + "|" + " Mã môn " + "|" + " Tên môn " + "|" + " Phòng học |");
		int i = 1;
		try {
			for(Subject sb: subjectList)
			{
				System.out.println(i + "|" + sb.subjectId + "|" + sb.subjectName + "|" + sb.room.getRoom());
				i++;
			}
		} catch (Exception e) {
			System.out.println("Không tồn tại thời khóa biểu của lớp " + gradeName);
		}
	}
}
