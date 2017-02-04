package dingchuang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dingchuang.entity.News;
import dingchuang.util.HibernateUtil;

public class NewsDAO {

	// 要是不存在返回NULL
	public News getById(int id) {
		String sql = "FROM News where id=" + id + ";";
		Session session = HibernateUtil.currentSession();
		session.beginTransaction();
		News news = (News) session.createQuery(sql);
		HibernateUtil.closeSession();
		if (news == null)
			return null;
		else
			return news;
	}

	public void save(News news) {
		Session session=HibernateUtil.currentSession();
		Transaction tx= session.beginTransaction();
		session.save(news);
		tx.commit();
		HibernateUtil.closeSession();
	}

	public List<News> listAllNews() {
		String hql="FROM News";
		Session session=HibernateUtil.currentSession();
		session.beginTransaction();
		List<News> news=(List<News>) session.createQuery(hql);
		HibernateUtil.closeSession();
		return news;
	}
}
