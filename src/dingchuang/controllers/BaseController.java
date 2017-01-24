package dingchuang.controllers;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import dingchuang.util.DCSimplePropertyPreFilter;

public class BaseController {

	private HttpServletRequest getRequest() {
		// TODO Auto-generated method stub
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}

	public void writeJsonByFilter(Object object, HttpServletResponse response,
			String[] includesProperties, String[] excludesProperties) {
		try {
			DCSimplePropertyPreFilter filter = new DCSimplePropertyPreFilter();// excludes������includes
			if (excludesProperties != null && excludesProperties.length > 0) {
				filter.getExcludes().addAll(
						Arrays.<String> asList(excludesProperties));
			}
			if (includesProperties != null && includesProperties.length > 0) {
				filter.getIncludes().addAll(
						Arrays.<String> asList(includesProperties));
			}
			// logger.info("����תJSON��Ҫ�ų�������[" + excludesProperties + "]Ҫ����������[" +
			// includesProperties + "]");
			String json;
			String User_Agent = getRequest().getHeader("User-Agent");
			if (StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 6") > -1) {
				// ʹ��SerializerFeature.BrowserCompatible���Ի�����е����Ķ������л�Ϊ\\uXXXX���ָ�ʽ���ֽ������һЩ�������ܼ���IE6
				json = JSON.toJSONString(object, filter,
						SerializerFeature.WriteDateUseDateFormat,
						SerializerFeature.DisableCircularReferenceDetect,
						SerializerFeature.BrowserCompatible);
			} else {
				// ʹ��SerializerFeature.WriteDateUseDateFormat���������л����ڸ�ʽ������Ϊyyyy-MM-dd
				// hh24:mi:ss
				// ʹ��SerializerFeature.DisableCircularReferenceDetect���Թر����ü�������
				json = JSON.toJSONString(object, filter,
						SerializerFeature.WriteDateUseDateFormat,
						SerializerFeature.DisableCircularReferenceDetect);
			}
			// logger.info("ת�����JSON�ַ�����" + json);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeJson(Object object ,HttpServletResponse response) {  
        writeJsonByFilter(object, response,null, null);  
    }
}
