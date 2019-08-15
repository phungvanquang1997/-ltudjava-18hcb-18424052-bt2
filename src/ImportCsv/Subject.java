package ImportCsv;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
@Table(name="Subject")
public class Subject {
	
	@Id
	@Column(name="id")
	public String subjectId;
	
	@Column(name="name")
	public String subjectName;
	public Room room;
	
	public Subject(String subjectId, String subjectName, Room room) {
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.room = room;
	}
}
