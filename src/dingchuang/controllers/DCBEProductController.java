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
	 *��̨������ܵ�ҳ�� 
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
		Json json = new Json();// ������ǰ�˷�����Ϣ
		if (productDAO.getById(product.getId()) != null) {
			json.setMsg("�������ʧ�ܣ���ID�Ѿ����ڣ�");
		} else {
			productDAO.save(product);
			json.setMsg("������ųɹ���");
			json.setSuccess(true);
		}
		writeJson(json, response);
		return null;
	}
	
	 @RequestMapping("/delProduct")  
	 public String delProduct(HttpServletRequest request,HttpServletResponse response) throws Exception{  
	    Json json = new Json();//������ǰ�˷�����Ϣ  
	    int newsId=Integer.parseInt(request.getParameter("id")) ;  
	    try{  
	    	productDAO.delete((Product)productDAO.getById(newsId));  
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
	 
	 @RequestMapping("/editProduct")  
	 public String editProduct(Product newProduct, HttpServletRequest request,HttpServletResponse response) throws Exception{  
	    Json json = new Json();//������ǰ�˷�����Ϣ  
//	    int newsId=Integer.parseInt(request.getParameter("id")) ;  //û��
	    try{  
	    	productDAO.update(newProduct);  
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
