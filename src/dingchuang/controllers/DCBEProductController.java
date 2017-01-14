package dingchuang.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/be")
public class DCBEProductController {
	
	
	@RequestMapping(value="productshow/list")
	public String productshowList(){
		return "dc/BE/productshow/list";
	}
	@RequestMapping(value="productshow/{id}")
	public String productshowID(@RequestParam int id,Model model){
		model.addAttribute("id", id);
		return "dc/FE/productshow/id";
	}

}
