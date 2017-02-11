package dingchuang.dao;

import java.util.List;

import org.hibernate.Query;
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
		//保存的时候要主要title是唯一的
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		session.save(news);
		tx.commit();
		HibernateUtil.closeSession();
	}

	public List<News> listAllNews() {
		String hql = "FROM News";
		Session session = HibernateUtil.currentSession();
//		session.beginTransaction();
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
		newNews=qryNewsWithTitle(newNews.getTitle());//带上来的时候没有ID，所以不能直接update，需要先查一遍
		Session session=HibernateUtil.currentSession();
		Transaction tx=session.beginTransaction();
		session.update(newNews);
		tx.commit();
		HibernateUtil.closeSession();
	}

	public boolean hasSameTitle(String title) {
		Session session = HibernateUtil.currentSession();
		String hql="FROM News where title=:newtitle";
		Query query=session.createQuery(hql);
		query.setParameter("newtitle", title);
		if(query.list().size()>0)
			return true;
		else
			return false;
	}

	public News qryNewsWithTitle(String title) {
		Session session=HibernateUtil.currentSession();
		String hql="FROM News where title=:title";
		Query query=session.createQuery(hql);
		query.setParameter("title", title);
		List<News> retNews=query.list();
		HibernateUtil.closeSession();
		return retNews.get(0);
	}
	

}
