package dingchuang.controllers;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DCDBController {
	
	@RequestMapping(value="createTables")
	public String createTables(){
		//读取配置文件hibernate.cfg.xml
		Configuration cfg = new Configuration().configure();
		//创建SchemeExport实例
		SchemaExport sExport = new SchemaExport(cfg);
		//创建数据库表
		sExport.create(true, true);
		return "dc/db/createtables";
	}
}
