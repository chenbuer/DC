package dingchuang.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dingchuang.entity.Product;
import dingchuang.util.HibernateUtil;

public class ProductDAO {

	// 要是不存在返回NULL
	public Product getById(int id) {
		String sql = "FROM Product where id=" + id;
		Session session = HibernateUtil.currentSession();
		session.beginTransaction();
		List<Product> news = (List<Product>) session.createQuery(sql).list();
		HibernateUtil.closeSession();
		if (news.size() <= 0)
			return null;
		else
			return news.get(0);
	}

	public void save(Product product) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		session.save(product);
		tx.commit();
		HibernateUtil.closeSession();
	}

	public List<Product> listAllNews() {
		String hql = "FROM Product";
		Session session = HibernateUtil.currentSession();
		session.beginTransaction();
		List<Product> product = (List<Product>) session.createQuery(hql).list();
		HibernateUtil.closeSession();
		return product;
	}

	public void delete(Product product) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		session.delete(product);
		tx.commit();
		session.close();
		HibernateUtil.closeSession();
	}

	public void update(Product newProduct) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		session.update(newProduct);
		tx.commit();
		HibernateUtil.closeSession();
	}

}
