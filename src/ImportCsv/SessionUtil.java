package ImportCsv;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtil {
	public static Session session() {
		@SuppressWarnings("deprecation")
		SessionFactory ss = new Configuration().configure("hibernate.cfg.xml")
		.buildSessionFactory();
		Session session = ss.openSession();
		return session;
	}
}
