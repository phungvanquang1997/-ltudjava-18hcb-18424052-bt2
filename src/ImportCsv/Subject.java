package ImportCsv;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Subject")
public class Subject {
	
	@Id
	@Column(name="id")
	public String subjectId;
	
	@Column(name="name")
	public String subjectName;
	
	@Column(name="grade_name")
	public String className;
	
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	@Column(name="room")
	public String room;
	
	public Subject(String subjectId, String subjectName, String room, String className) {
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.room = room;
		this.className = className;
	}
}
