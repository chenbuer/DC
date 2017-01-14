package dingchuang.controllers;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DCDBController {
	
	@RequestMapping(value="createTables")
	public String createTables(){
		//��ȡ�����ļ�hibernate.cfg.xml
		Configuration cfg = new Configuration().configure();
		//����SchemeExportʵ��
		SchemaExport sExport = new SchemaExport(cfg);
		//�������ݿ��
		sExport.create(true, true);
		return "dc/db/createtables";
	}
}
