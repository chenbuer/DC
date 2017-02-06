package dingchuang.controllers;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dingchuang.dao.NewsDAO;
import dingchuang.dao.ProductDAO;
import dingchuang.entity.Json;
import dingchuang.entity.Product;

@Controller
@RequestMapping(value="/be/product")
public class DCBEProductController extends BaseController{

	private ProductDAO productDAO = new ProductDAO();
	
	/**
	 *后台管理的总的页面 
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String goList() {
		return "dc/BE/product";
	}

	@RequestMapping("/listProduct")
	public String listNews(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Product> news = productDAO.listAllNews();
		writeJson(news, response);
		return null;
	}

	@RequestMapping("/addProduct")
	public String addUser(Product product, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Json json = new Json();// 用于向前端发送消息
		if (productDAO.getById(product.getId()) != null) {
			json.setMsg("添加新闻失败，该ID已经存在！");
		} else {
			productDAO.save(product);
			json.setMsg("添加新闻成功！");
			json.setSuccess(true);
		}
		writeJson(json, response);
		return null;
	}
	
	 @RequestMapping("/delProduct")  
	 public String delProduct(HttpServletRequest request,HttpServletResponse response) throws Exception{  
	    Json json = new Json();//用于向前端发送消息  
	    int newsId=Integer.parseInt(request.getParameter("id")) ;  
	    try{  
	    	productDAO.delete((Product)productDAO.getById(newsId));  
	        json.setMsg("删除成功！");  
	        json.setSuccess(true);  
	        writeJson(json,response);  
	        return null;  
	    }catch (Exception e){  
	        json.setMsg("删除失败！"+e.getMessage());  
	        writeJson(json,response);  
	        return null;  
	    }  
	}  
	 
	 @RequestMapping("/editProduct")  
	 public String editProduct(Product newProduct, HttpServletRequest request,HttpServletResponse response) throws Exception{  
	    Json json = new Json();//用于向前端发送消息  
//	    int newsId=Integer.parseInt(request.getParameter("id")) ;  //没用
	    try{  
	    	productDAO.update(newProduct);  
	        json.setMsg("删除成功！");  
	        json.setSuccess(true);  
	        writeJson(json,response);  
	        return null;  
	    }catch (Exception e){  
	        json.setMsg("删除失败！"+e.getMessage());  
	        writeJson(json,response);  
	        return null;  
	    }  
	}  
}
