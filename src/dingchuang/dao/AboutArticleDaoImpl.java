package dingchuang.dao;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dingchuang.entity.AboutArticle;

@Transactional
@Service
public class AboutArticleDaoImpl implements AboutArticleDao {

	@Resource
	public SessionFactory sessionFactory;
	@Override
	public void update(AboutArticle aboutArticle) {
		Session session =sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		
		session.update(aboutArticle);
		
		tx.commit();
		session.close();
		

	}
	@Override
	public AboutArticle getAbout(int id) {		
		Session session =sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		
		
		AboutArticle aboutArticle=new AboutArticle();
	    String hql = "FROM AboutArticle as p WHERE p.id = ?";  
	    Query q = session.createQuery(hql);  
	    q.setInteger(0, id);  
	    List<AboutArticle> list = q.list();  
	    Iterator<AboutArticle> iteator = list.iterator();  
	    if(iteator.hasNext()){  
	    	aboutArticle = (AboutArticle)iteator.next();  
	    }  
	    
	    
	    tx.commit();
		session.close();
		
	    return aboutArticle;  
		
	}

}
