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
	
	/**
	 *��̨������ܵ�ҳ�� 
	 * @return
	 */
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
		if(newsDAO.hasSameTitle(news.getTitle())){
			json.setMsg("������ͬ���Ѿ������������ˣ�");
			json.setSuccess(false);
			writeJson(json, response);
			return null;
		}
		newsDAO.save(news);
		json.setMsg("������ųɹ���");
		json.setSuccess(true);
		writeJson(json, response);
		return null;
	}
	
	 @RequestMapping("/delNews")  
	 public String delNews(HttpServletRequest request,HttpServletResponse response) throws Exception{  
	    Json json = new Json();//������ǰ�˷�����Ϣ  
	    int newsId=Integer.parseInt(request.getParameter("id")) ;  
	    try{  
	    	newsDAO.delete((News)newsDAO.getById(newsId));  
	        json.setMsg("ɾ���ɹ���");  
	        json.setSuccess(true);  
	        writeJson(json,response);  
	        return null;  
	    }catch (Exception e){  
	        json.setMsg("ɾ��ʧ�ܣ�"+e.getMessage());  
	        writeJson(json,response);  
	        return null;  
	    }  
	}  
	 
	 @RequestMapping("/editNews")  
	 public String editNews(News newNews, HttpServletRequest request,HttpServletResponse response) throws Exception{  
	    Json json = new Json();//������ǰ�˷�����Ϣ  
//	    int newsId=Integer.parseInt(request.getParameter("id")) ;  //û��
	    try{  
	    	newsDAO.update(newNews);  
	        json.setMsg("ɾ���ɹ���");  
	        json.setSuccess(true);  
	        writeJson(json,response);  
	        return null;  
	    }catch (Exception e){  
	        json.setMsg("ɾ��ʧ�ܣ�"+e.getMessage());  
	        writeJson(json,response);  
	        return null;  
	    }  
	}  
}
