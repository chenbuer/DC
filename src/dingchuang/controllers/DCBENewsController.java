package dingchuang.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

import dingchuang.dao.NewsDAO;
import dingchuang.entity.Json;
import dingchuang.entity.News;

@Controller
@RequestMapping(value = "/be/news")
public class DCBENewsController extends BaseController {

	private NewsDAO newsDAO = new NewsDAO();

	@RequestMapping(value = "/list")
	public String goList() {
		return "dc/BE/news";
	}

	@RequestMapping("/listNews")
	public String listNews(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<News> news = newsDAO.listAllNews();
		writeJson(news, response);
		return null;
	}

	@RequestMapping("/addNews")
	public String addUser(News news, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Json json = new Json();// ������ǰ�˷�����Ϣ
		if (newsDAO.getById(news.getId()) != null) {
			json.setMsg("�½��û�ʧ�ܣ��û��Ѵ��ڣ�");
		} else {
			newsDAO.save(news);
			json.setMsg("�½��û��ɹ���");
			json.setSuccess(true);
		}
		writeJson(json, response);
		return null;
	}
}
