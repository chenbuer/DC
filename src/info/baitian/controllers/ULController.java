package info.baitian.controllers;

import info.baitian.entity.Guest;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ULController {
	@RequestMapping(value="input")
	public String input(){
		return "input";
	}

	@RequestMapping(value = "save")
	public String save(HttpServletRequest request,@ModelAttribute Guest guest,Model model) throws Exception {
		List<MultipartFile> images=guest.getImages();
		for(MultipartFile file:images){
			String filename=file.getOriginalFilename();
			File imageFile=new File(request.getServletContext().getRealPath("/WEB-INF/image"),filename);
			file.transferTo(imageFile);			
		}
		String name=guest.getImages().get(0).getOriginalFilename();
		String imageUrl="/WEB-INF/image/"+name;
		model.addAttribute("filename", name);
		model.addAttribute("imageUrl",imageUrl);
		return "fileDetail";
	}
}
