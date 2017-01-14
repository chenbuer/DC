package dingchuang.controllers;
import info.baitian.entity.Guest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.glass.ui.Application;

import dingchuang.dao.AboutArticleDaoImpl;
import dingchuang.entity.AboutArticle;

@Controller
@RequestMapping(value="/be")
public class DCBEController {
	
	@Resource
	AboutArticleDaoImpl aboutArticleDaoImpl;
	
	@RequestMapping(value="about")
	public String about(Model model){
		AboutArticle aboutArticle=aboutArticleDaoImpl.getAbout(0);
		model.addAttribute("aboutArticle", aboutArticle);
		return "dc/BE/about";
	}
	
	@RequestMapping(value="about/update")
	public String aboutSave(Model model,HttpServletRequest request){
		model.addAttribute("baseUrl",request.getServletContext().getContextPath());
		model.addAttribute("aboutArticle", new AboutArticle());
		AboutArticle aboutArticle=aboutArticleDaoImpl.getAbout(0);
		model.addAttribute("aboutArticle", aboutArticle);
		return "dc/BE/about_update";
	}
	
	@RequestMapping(value="about/update_success")
	public String updateSuccess(@ModelAttribute AboutArticle aboutAritcle){
		aboutArticleDaoImpl.update(aboutAritcle);
		return "redirect:/be/about";
	}
	
	
	
	
	@RequestMapping(value="productshow")
	public String productshow(){
		return "dc/BE/productshow";
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
		return "dc/BE/patent";
	}
	
	@RequestMapping(value="news")
	public String news(){
		return "dc/BE/news";
	}
	
	@RequestMapping(value="hr")
	public String hr(){
		return "dc/BE/hr";
	}
	
	@RequestMapping(value="contact")
	public String contact(){
		return "dc/BE/contact";
	}
}
