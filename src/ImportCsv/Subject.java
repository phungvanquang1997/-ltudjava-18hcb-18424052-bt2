package ImportCsv;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
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
	
	@Column(name="room")
	public String room;
	
	public Subject(String subjectId, String subjectName, String room) {
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.room = room;
	}
}
