package ImportCsv;

public class Subject {
	public String subjectId;
	public String subjectName;
	public Room room;
	
	public Subject(String subjectId, String subjectName, Room room) {
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.room = room;
	}
}
