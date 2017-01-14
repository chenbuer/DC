package dingchuang.test;



import org.apache.commons.dbcp.BasicDataSource;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import dingchuang.entity.AboutArticle;

public class addAboutWithHibernateInSpring {	
	private ClassPathXmlApplicationContext ctx = null;
	
	
	@Test
	public void addAbout(){
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//      System.out.println(ctx);
            //检查数据库连接
		BasicDataSource dataSource = ctx.getBean(BasicDataSource.class);

//     System.out.println(dataSource.getConnection().toString());
   //检查hibernate配置
    SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
    System.out.println(sessionFactory);

    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    //测试数据库
    AboutArticle aboutArticle=new AboutArticle("xxx题目","neirong这样");
	session.save(aboutArticle);
    tx.commit();
    session.close();
	}
}
