package dingchuang.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class BaseController {

	public void writeJsonByFilter(Object object, HttpServletResponse response) {
		try {
			String json;
			json = JSON.toJSONString(object);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeJson(Object object, HttpServletResponse response) {
		writeJsonByFilter(object, response);
	}
}