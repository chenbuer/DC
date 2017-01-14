package dingchuang.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/be/news")
public class DCBENewsController {
	
	@RequestMapping(value="list")
	public String newsList(){
		return "dc/BE/news";
	}
	
//	@RequestMapping("newsData")
//	public void newsMain(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		PagerBean pb = new PagerBean();
//		Map<String,String[]> properties = request.getParameterMap();
//		pb.setCurPage(MyUtils.getParam(request, "page", 1));
//		pb.setPageSize(MyUtils.getParam(request, "rows", 15));
//		newsService.queryWithPage(pb, properties/*, loginUnit*/);
//		JSONObject data = new JSONObject();
//		data.accumulate("total", pb.getCountSize());
//		data.accumulate("rows", JSONArray.fromObject(pb.getList()));
//		MyUtils.JSONResult(request, response, data);
//	}

}
