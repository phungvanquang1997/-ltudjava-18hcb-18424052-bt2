package ImportCsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;

@Entity
@Table(name="GRADE")
public class Grade {
	
	@Id
	@Column(name="id")
	private int classId;
	
	@Column(name="name")
	private String className;
	
	public Grade()
	{

	}
	
	public Grade(String className)
	{
		this.className = className;
	}
	
	
	public String getClassName()
	{
		return this.className;
	}
	
	
	public List findStudentsInClasses(String className)
	{
		Session session = SessionUtil.session();
		try {
			String hql = "FROM Grade g , Student s WHERE s.gradeId = g.className and g.className = :name";
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameter("name", "18HCB");
			List l = query.list();
			Object[] row = (Object[]) l.get(0);
			Student g = (Student)row[1];
			session.getTransaction().commit();
			return l;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
		}
		return new ArrayList();
	}
	
}

