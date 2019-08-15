package ImportCsv;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Student")
public class Student {
	@Id
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
	
	public Point point;
	
	public Student() {
		
	}
	
	public Student(String name, String MSSV, String gender, String CMND)
	{
		this.CMND = CMND;
		this.gender = gender;
		this.MSSV = MSSV;
		this.name = name;
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

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student(String name, String mSSV, String gender, String cMND, Point point) {
		this.name = name;
		this.MSSV = mSSV;
		this.gender = gender;
		this.CMND = cMND;
		this.point = point;
	}

	public String getName() 
	{ 
		return this.name;
	}
}