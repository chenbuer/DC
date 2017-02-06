package dingchuang.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dingchuang.entity.News;
import dingchuang.util.HibernateUtil;

@Controller
@RequestMapping(value="/index")
public class DCIndexController extends BaseController {
	
	@RequestMapping(value="newsQuery")
	public void newsQuery(HttpServletRequest request,HttpServletResponse response){
		Session session=HibernateUtil.currentSession();
		Transaction tx=session.beginTransaction();
		String hql="FROM News";
		List<News> threeNews=session.createQuery(hql).setMaxResults(3).list();
		tx.commit();
		HibernateUtil.closeSession();
		
		writeJson(threeNews, response);
	}
}
