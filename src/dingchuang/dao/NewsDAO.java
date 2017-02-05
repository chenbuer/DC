package dingchuang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dingchuang.entity.News;
import dingchuang.util.HibernateUtil;

public class NewsDAO {

	// 要是不存在返回NULL
	public News getById(int id) {
		String sql = "FROM News where id=" + id;
		Session session = HibernateUtil.currentSession();
		session.beginTransaction();
		List<News> news = (List<News>) session.createQuery(sql).list();
		HibernateUtil.closeSession();
		if (news.size() <= 0)
			return null;
		else
			return news.get(0);
	}

	public void save(News news) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		session.save(news);
		tx.commit();
		HibernateUtil.closeSession();
	}

	public List<News> listAllNews() {
		String hql = "FROM News";
		Session session = HibernateUtil.currentSession();
		session.beginTransaction();
		List<News> news = (List<News>) session.createQuery(hql).list();
		HibernateUtil.closeSession();
		return news;
	}
	
	public void delete(News news) {
		Session session=HibernateUtil.currentSession();
		Transaction tx=session.beginTransaction();
		session.delete(news);
		tx.commit();
		session.close();
		HibernateUtil.closeSession();
	}

	public void update(News newNews) {
		Session session=HibernateUtil.currentSession();
		Transaction tx=session.beginTransaction();
		session.update(newNews);
		tx.commit();
		HibernateUtil.closeSession();
	}
	

}
