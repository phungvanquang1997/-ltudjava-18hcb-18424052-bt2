package ImportCsv;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public int id;
	
	@Column(name="name")
	public String name;
	
	@Column(name="MSSV")
	public String MSSV;
	
	@Column(name="gender")
	public String gender;
	
	@Column(name="CMND")
	public String CMND;
	
	@Column(name="grade_name")
	public String gradeId;
	
	public Student() {
		
	}
	
	public Student(String name, String MSSV, String gender, String CMND, String gradeId)
	{
		this.CMND = CMND;
		this.gender = gender;
		this.MSSV = MSSV;
		this.name = name;
		this.gradeId = gradeId;
	}
	
	public String getMSSV() {
		return MSSV;
	}

	public void setMSSV(String mSSV) {
		MSSV = mSSV;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() 
	{ 
		return this.name;
	}
	
}
