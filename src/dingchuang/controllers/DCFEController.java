package dingchuang.controllers;
import info.baitian.entity.Guest;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dingchuang.dao.AboutArticleDaoImpl;
import dingchuang.entity.AboutArticle;

@Controller
public class DCFEController {
	
	@Resource
	AboutArticleDaoImpl aboutArticleDaoImpl;
	
	@RequestMapping(value="about")
	public String about(Model model){
		AboutArticle aboutArticle=aboutArticleDaoImpl.getAbout(0);
		model.addAttribute("aboutArticle", aboutArticle);
		return "dc/FE/about";
	}
	
	@RequestMapping(value="productshow/list")
	public String productshowList(){
		return "dc/FE/productshow/list";
	}
	
	@RequestMapping(value="productshow/{id}")
	public String productshowID(@PathVariable int id,Model model){
		model.addAttribute("id", id);
		return "dc/FE/productshow/id";
	}

	@RequestMapping(value="productshow")
	public String productshow(){
		return "dc/FE/productshow";
	}

	@RequestMapping(value = "patent")
	public String patent(HttpServletRequest request,@ModelAttribute Guest guest,Model model) throws Exception {
//		List<MultipartFile> images=guest.getImages();
//		for(MultipartFile file:images){
//			String filename=file.getOriginalFilename();
//			File imageFile=new File(request.getServletContext().getRealPath("/WEB-INF/image"),filename);
//			file.transferTo(imageFile);			
//		}
//		String name=guest.getImages().get(0).getOriginalFilename();
//		String imageUrl="/WEB-INF/image/"+name;
//		model.addAttribute("filename", name);
//		model.addAttribute("imageUrl",imageUrl);
		return "dc/FE/patent";
	}
	
	@RequestMapping(value="news")
	public String news(){
		return "dc/FE/news";
	}
	
	@RequestMapping(value="hr")
	public String hr(){
		return "dc/FE/hr";
	}
	
	@RequestMapping(value="contact")
	public String contact(){
		return "dc/FE/contact";
	}
}
