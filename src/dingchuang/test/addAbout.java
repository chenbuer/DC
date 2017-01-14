package dingchuang.test;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dingchuang.entity.AboutArticle;

public class addAbout {	
	SessionFactory sessionFactory;
	Session session;
	Transaction transaction;
	@Before
	public void before(){ 
		    
		Configuration cfg=new Configuration().configure();
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
		sessionFactory=cfg.buildSessionFactory(serviceRegistry);
		session=sessionFactory.getCurrentSession();
		transaction=session.beginTransaction();
	}
	@After
	public void after(){
		transaction.commit();
	}
	@Test
	public void addAbout(){
		AboutArticle aboutArticle=new AboutArticle("得分","neirong这样");
		session.save(aboutArticle);
	}
}
