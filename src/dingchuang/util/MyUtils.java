package dingchuang.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.DocumentException;

public class MyUtils {
	public enum ParamEncode{
		urlDecode  //url解码
		,normal    //原形，不做任何编码
		,urlEncode //url编码
		;
	}
	//工具方法--返回json数据
	/**
	 * @param request http请求对象
	 * @param response http响应对象
	 * @param json 待返回的json数据
	 * @throws IOException
	 */
	public static final void JSONResult(HttpServletRequest request,HttpServletResponse response,JSONObject json) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter  out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	//工具方法--返回json数据
	/**
	 * @param request http请求对象
	 * @param response http响应对象
	 * @param json 待返回的json数据
	 * @throws IOException
	 */
	public static final void JSONResult(HttpServletRequest request,HttpServletResponse response,JSONArray json) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter  out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}
	
	//工具方法--返回js提示
	/**
	 * @param request http请求对象
	 * @param response http响应对象
	 * @param json 待返回的json数据
	 * @throws IOException
	 */
	public static final void JSResult(HttpServletRequest request,HttpServletResponse response,String msg) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript;charset=UTF-8");
		PrintWriter  out = response.getWriter();
		out.print(msg);
		out.flush();
		out.close();
	}
	
	//工具方法--返回html提示
	/**
	 * @param request http请求对象
	 * @param response http响应对象
	 * @param html 待返回的html数据
	 * @throws IOException
	 */
	public static final void htmlResult(HttpServletRequest request,HttpServletResponse response,String html) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter  out = response.getWriter();
		out.print(html);
		out.flush();
		out.close();
	}
	
	//工具方法--待执行的js脚本
	/**
	 * @param request http请求对象
	 * @param response http响应对象
	 * @param json 待返回的json数据
	 * @throws IOException
	 */
	public static final void JsScript(HttpServletRequest request,HttpServletResponse response,String js) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter  out = response.getWriter();
		out.println("<script>");
		out.println(js);
		out.println("</script>");
		out.flush();
		out.close();
	}
	
	//工具方法--将parameter设置为attribute带回页面
	/**
	 * 
	 * @param request http请求对象
	 * @param response http响应对象
	 * @param parameters 待返回的json数据
	 * @param params 设置的attr数组
	 */
	public static final void setAttributes(HttpServletRequest request,HttpServletResponse response,Map<String,String[]> parameters,String[] params){
		for(String para:params){
			if(parameters.containsKey(para)){
				request.setAttribute(para, parameters.get(para)==null?null:parameters.get(para)[0]==null?null:parameters.get(para)[0].toString());
			}
		}
	}
	//工具方法--替换字符串strA指定位置n的数据为另一个strB字符串,位数不够补充str
	/**
	 * @param strA 待替换的源字符串
	 * @param n strA中的位置
	 * @param strB 替换为的新字符串
	 * @param str strA字符长度不够n时，右补str，直到位数大于等于n
	 * @return 完成替换(也许有右补str)后的strA串
	 */
	public static final String replaceStr(String strA,int n,String strB,String str){
		if(strA.trim().length()<n){
			StringBuffer tempA = new StringBuffer(strA);
			while(tempA.length()<n){
				tempA.append(str);
			}
			strA = tempA.toString();
		}
		StringBuffer tempStr = new StringBuffer(strA.substring(0,n-1)); 
		tempStr.append(strB).append(strA.substring(n,strA.length()));
		return strA = tempStr.toString();
	}
	
	//工具方法--替换字符串strA指定位置n的数据为另一个strB字符串,位数不够补充str
	/**
	 * @param strA 待替换的源字符串
	 * @param n strA中的位置
	 * @param strB 替换为的新字符串
	 * @param str strA字符长度不够n时，右补str，直到位数大于等于n
	 * @return 完成替换(也许有右补str)后的strA串
	 */
	public static final String replaceStr(String strA,String n,String strB,String str){
		int N = Integer.parseInt(n.trim());
		return replaceStr(strA,N,strB,str);
	}
	
	/**
	 * 工具方法--构造sql语句，将字段column右补字符串str到n长度，然后将index位置的字符替换为newStr新字符串
	 * @param column 字段
	 * @param n 长度
	 * @param str 右补长字符串
	 * @param index 指定位置
	 * @param newStr 替换为的新字符串
	 * @return 构造好的sql
	 */
	public static final String replaceStrSql(String column,int n,String str,int index,String newStr){
		if(str.trim().length()<n){
			StringBuffer tempA = new StringBuffer(str);
			while(tempA.length()<n){
				tempA.append(str);
			}
			str = tempA.toString();
		}
		StringBuffer sb = new StringBuffer(" (case when len(").append(column).append(")<").append(n);
		sb.append(" then stuff(substring(").append(column).append("+'").append(str).append("',1,").append(n)
		.append("),").append(index).append(",1,'").append(newStr).append("') else stuff(").append(column)
		.append(",").append(index).append(",1,'").append(newStr).append("')").append(" end) ");
		return sb.toString();
	}
	/**
	 * 返回指定格式的时间
	 * @param date 时间对象
	 * @param format 时间格式 ,同SimpleDateFormat的format规则,例如：【yyyy年MM月dd日  E】返回【2014年03月13日  星期四】,
	 * 			【yyyy年MM月dd日  hh:mm:ss】返回【2014年03月13日  03:18:14】，【yyyy年MM月dd日  HH:mm:ss】返回【2014年03月13日  15:19:54】
	 * @return
	 */
	public static final String getDateTime(Date date,String format){
		String dateTime = "";
		if(date==null){
			date = new Date();
		}
		dateTime = new SimpleDateFormat(format).format(date);
		return dateTime;
	}
	
	/**
	 * 返回指定格式的时间
	 * @param date 时间对象
	 * @param format 时间格式 ,同SimpleDateFormat的format规则,例如：【yyyy年MM月dd日  E】返回【2014年03月13日  星期四】,
	 * 			【yyyy年MM月dd日  hh:mm:ss】返回【2014年03月13日  03:18:14】，【yyyy年MM月dd日  HH:mm:ss】返回【2014年03月13日  15:19:54】
	 * @return
	 */
	public static final String getDateTime(String date,String format){
		String dateTime = "";
		Date dateD = null;
		if(date==null||date.trim().equals("")){
			dateD = new Date();
		}else{
			try {
				dateD = new SimpleDateFormat().parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}finally{
				dateD = new Date();
			}
		}
		dateTime = new SimpleDateFormat(format).format(dateD);
		return dateTime;
	}
	
	/**
	 * 取得request中参数值--字符型
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static final String getParam(HttpServletRequest request,String paramName,String defaultValue){
		String value = null;
		if(request.getParameter(paramName)!=null&&!request.getParameter(paramName).trim().equals("")){
			value = request.getParameter(paramName).trim();
		}else{
			value = defaultValue;
		}
		return value;
	}
	
	/**
	 * 取得request中参数值--整形
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static final Integer getParam(HttpServletRequest request,String paramName,Integer defaultValue){
		String value = null;
		if(request.getParameter(paramName)!=null&&!request.getParameter(paramName).trim().equals("")){
			value = request.getParameter(paramName).trim();
			return Integer.parseInt(value);
		}else{
			return defaultValue;
		}
	}
	
	/**
	 * 取得request中属性值
	 * @param request
	 * @param Attrname
	 * @return
	 */
	public static final String getAttr(HttpServletRequest request,String Attrname,String defaultValue){
		String value = null;
		if(request.getAttribute(Attrname)!=null&&!request.getAttribute(Attrname).toString().trim().equals("")){
			value = request.getAttribute(Attrname).toString().trim();
		}else{
			value = defaultValue;
		}
		return value;
	}
	
	/**
	 * 取得request中属性对象
	 * @param request
	 * @param Attrname
	 * @return
	 */
	public static final Object getAttrO(HttpServletRequest request,String Attrname,Object defaultValue){
		Object value = null;
		if(request.getAttribute(Attrname)!=null){
			value = request.getAttribute(Attrname);
		}else{
			value = defaultValue;
		}
		return value;
	}
	
	/**
	 * 判断obj是否为空对象，如果是赋值为默认值defaultValue(当obj是String类型时，即便obj不是空对象，如果obj是空串，也赋值默认值defaultValue)
	 * @param obj
	 * @param defaultValue
	 * @return
	 */
	public static final Object isNull(Object obj,Object defaultValue){
		if(obj!=null){
			if(obj instanceof String){
				if(((String)obj).trim().equals("")){
					obj = defaultValue;
				}
			}
		}else{
			obj = defaultValue;
		}
		return obj;
	}
	
	/**
	 * 从request获得的properties的Map对象中获取指定propName的值
	 * @param propName
	 * @param properties
	 * @return
	 */
	public static final String getValueFromMap(String propName,Map<String,String[]> properties){
		return properties.get(propName)==null?null:properties.get(propName)[0]==null||properties.get(propName)[0].trim().equals("")?null:properties.get(propName)[0].trim().toString();
	}
	
	/**
	 * 获得下一个code 例如：005,3，返回006；002008，返回002009；003010008，返回
	 * @param maxCode 当前查到的最编码
	 * @param levelLength 每级的数字位数
	 * @return 当maxCode的最后一级全是9时，下一个code是错误的。
	 */
	public static String getNextCode(String maxCode,String pCode,int levelLength) {
		if(maxCode!=null&&pCode!=null&&!maxCode.startsWith(pCode)){
			maxCode = pCode + maxCode.substring(levelLength);
		}
		if(maxCode != null){
			maxCode = maxCode.trim();
		}else{
			StringBuffer tempCode = new StringBuffer();
			for(int i=0;i<levelLength-1;i++){
				tempCode.append("0");
			}
			tempCode.append("1");
			if(pCode==null){
				maxCode = tempCode.toString();
			}else{
				maxCode = tempCode.insert(0, pCode).toString();
			}
			return maxCode;
		}
		String lastLevelCode = maxCode.substring(maxCode.length()-levelLength);
		StringBuffer preZeroStr = new StringBuffer(maxCode.substring(0,maxCode.length()-levelLength));
		StringBuffer lastLevelStr = new StringBuffer();
		while(lastLevelCode.startsWith("0")){
			lastLevelStr.append("0");
			lastLevelCode = lastLevelCode.substring(1);
		}
		int k = Integer.parseInt(lastLevelCode);
		if(String.valueOf(k+1).length()>levelLength){
			try {
				throw new Exception("maxCode为："+maxCode+",当前层级数据已满，不能继续添加！！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		lastLevelStr.append(String.valueOf(k+1));
		return preZeroStr.append(lastLevelStr.substring(lastLevelStr.length()-levelLength)).toString();
	}
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		if(str==null||str.trim().equals("")){
			return true;
		}
		return false;
	}
	/**
	 * 根据cacheName获得Cache对象
	 * @param cacheName
	 * @return Cache
	 */
//	public static Cache getCache(String cacheName){
//		CacheManager cacheManager = CacheManager.create(Constants.EHCACHE_FILEPATH);
//		return cacheManager.getCache(cacheName);
//	}
	/**
	 * 获得cookie中值
	 * @param name
	 * @return
	 */
	public static String getCookie(HttpServletRequest request,String name){
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().trim().equals(name.trim())){
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}
	/**
	 * 获得cookie的map<name,Cookie>
	 * @return
	 */
	public static HashMap<String, Cookie> getCookieMap(HttpServletRequest request){
		HashMap<String, Cookie> map = new HashMap<String,Cookie>();
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				map.put(cookies[i].getName(), cookies[i]);
			}
		}
		return map;
	}
	/**
	 * 从cookiemap<name,Cookie>中获得指定cookie的name的值
	 * @param map
	 * @param name
	 * @return
	 */
	public static String getCookie(HashMap<String, Cookie> map,String name){
		if(map.containsKey(name)){
			return map.get(name).getValue();
		}else{
			return null;
		}
	}
	
	/**
	 * 判断cookiemap<name,Cookie>中获得指定name的cookie值是否存在
	 * @param map
	 * @param name
	 * @return
	 */
	public static boolean haveCookie(HashMap<String, Cookie> map,String name){
		return map.containsKey(name);
	}
	/**
	 * des解密
	 * @param str
	 * @param key
	 * @return
	 */
	public static String decrypt(String str, String key,String charset) {
		try {
			if (str == null || str.length() < 1) return "";
			DESKeySpec keySpec = new DESKeySpec(key.getBytes(charset));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(keySpec);
			Cipher c1 = Cipher.getInstance("DES");
			c1.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(c1.doFinal(hex2byte(str)),charset);
		} catch ( Exception e ) {
			e.printStackTrace();
			return "";
		}
	}

	private static byte[] hex2byte(String hexStr) {
		try {
			byte[] bts = new byte[hexStr.length() / 2];
			for ( int i = 0, j = 0; j < bts.length; j++ ) {
				bts[j] = (byte) Integer.parseInt(hexStr.substring(i, i + 2), 16);
				i += 2;
			}
			return bts;
		} catch ( Exception e ) {
			return "".getBytes();
		}
	}
	
	/**
	 * 删除指定目录下的指定文件
	 * @param allFileArray 用逗号分隔的相对文件路径字符串
	 * @param dir 目录地址
	 */
	public static void deleteUnusedFile(String allFileArray,String[] addFileArray,String[] minusFileArray,final String dir){
		//所有没有使用的临时文件，也即是待删除的临时文件
		final Set<String> files = new HashSet<String>();
		//所有临时文件
		if(allFileArray!=null&&!"".equals(allFileArray.trim())){		
			String[] fileArray = allFileArray.split(",");
			for(int i=0;i<fileArray.length;i++){
				if(fileArray[i]!=null&&!"".equals(fileArray[i])){
					files.add(fileArray[i].trim());
				}
			}
		}
		//额外的临时文件--例如：编辑前使用到的文件
		if(addFileArray!=null&&addFileArray.length>0){		
			for(int i=0;i<addFileArray.length;i++){
				if(addFileArray[i]!=null&&!"".equals(addFileArray[i])){
					files.add(addFileArray[i].trim());
				}
			}
		}
		//使用的临时文件--例如：编辑后真正使用的文件
		if(minusFileArray!=null&&minusFileArray.length>0){		
			for(int i=0;i<minusFileArray.length;i++){
				if(minusFileArray[i]!=null&&!"".equals(minusFileArray[i])){
					if(files.contains(minusFileArray[i].trim())){
						files.remove(minusFileArray[i].trim());
					}
				}
			}
		}
		if(files.size()>0){//是否有待删除的临时文件
			new Thread(new Runnable() {
				public void run() {
						Iterator<String> it = files.iterator();
						while(it.hasNext()){
							String tempFilePath = it.next() ;
							if(tempFilePath!=null&&!"".equals(tempFilePath.trim())){
								if(!tempFilePath.startsWith("http://")){
									tempFilePath = dir+tempFilePath;
									File tempFile = new File(tempFilePath);
									if(tempFile.exists()&&tempFile.isFile()){
										tempFile.delete();
									}
								}
							}
						}
					}
			}).start();
		}
	}
	
	/**
	 * 从字符串中获得所有文件路径
	 * @param htmlText
	 * @return
	 * @throws DocumentException 
	 */
	public static String[] getAllFilePath(String htmlText,String[] addFilePath) throws DocumentException{
		Set<String> set = new HashSet<String>();
		if(htmlText==null){
			//return null;
		}else{
			//目前只解析出img的文件路径
			Pattern pattern = Pattern.compile("(<img.*src=['\"]([^'\">]+)['\"].*/>)");//Pattern.compile("(<img\\s*\\w*src=['\"]([^'\">]+)['\"]\\s*\\w*/>)");
			Matcher matcher = pattern.matcher(htmlText);
			while(matcher.find()){
				set.add(matcher.group(2));//获得文件路径
			}
			//解析出附件的文件路径
			Pattern pattern2 = Pattern.compile("(<a.*href=['\"]([^'\">]+)['\"].*>)");//Pattern.compile("(<img\\s*\\w*src=['\"]([^'\">]+)['\"]\\s*\\w*/>)");
			Matcher matcher2 = pattern2.matcher(htmlText);
			while(matcher2.find()){
				set.add(matcher2.group(2));//获得文件路径
			}
		}
		if(addFilePath!=null&&addFilePath.length>0){
			for(int i=0;i<addFilePath.length;i++){
				if(addFilePath[i]!=null&&!"".equals(addFilePath[i].trim())){
					set.add(addFilePath[i].trim());
				}
			}
		}
		return set.toArray(new String[set.size()]);
	}
	
	/**
	 * 从html字符串去掉所有标签和换行，返回纯文本
	 * @param htmlText
	 * @return
	 * @throws DocumentException 
	 */
	public static String html2Str(String htmlText){
		if(htmlText==null){
			return "";
		}
		htmlText = htmlText.replaceAll("<[^>]+>", "");
		htmlText = htmlText.replaceAll("\\s*", "");
		htmlText = htmlText.replaceAll("&[^&^;]+;", "");
		return htmlText;
	}
	
	/**
	 * 删除所有待删除记录中使用到的文件
	 * @param list 记录的文件字段组成的数组的列表
	 * @param dir 文件的相对目录
	 */
	public static void deleteFileBeforeRemoveRecords(List<?> list,final String dir){
		//所有没有使用的临时文件，也即是待删除的临时文件
		final Set<String> files = new HashSet<String>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] tColumns = null;
				if(list.get(i) instanceof String){
					tColumns = new String[]{(String)(list.get(i))};
				}else{
					tColumns = (Object[]) list.get(i);
				}
				if(tColumns!=null&&tColumns.length>0){
					for(int j=0;j<tColumns.length;j++){
						if(tColumns[j]!=null&&!"".equals(((String)tColumns[j]).trim())){
							files.add(((String)tColumns[j]).trim());
						}
					}
				}
			}
		}
		if(files.size()>0){//是否有待删除的临时文件
			new Thread(new Runnable() {
				public void run() {
						Iterator<String> it = files.iterator();
						while(it.hasNext()){
							String tempFilePath = it.next() ;
							if(tempFilePath!=null&&!"".equals(tempFilePath.trim())){
								if(!tempFilePath.startsWith("http://")){
									tempFilePath = dir+tempFilePath;
									File tempFile = new File(tempFilePath);
									if(tempFile.exists()&&tempFile.isFile()){
										tempFile.delete();
									}
								}
							}
						}
					}
			}).start();
		}
	}
	/**
	 * 将输入流转化为字符串
	 * @param is
	 * @return
	 */
	public static final String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				if(!sb.toString().equals("")){
					sb.append("/n");
				}
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	/**
	 * 通过cookie验证是否登录成功--慕课空间方式
	 * @param cookieMap
	 * @return
	 */
//	public static boolean loginSuccess(HashMap<String, Cookie> cookieMap) {
//		if(!MyUtils.haveCookie(cookieMap, "fid")){//fid没有则必然没有登录成功
//			return false;
//		}
//		if(!MyUtils.haveCookie(cookieMap, MyUtils.getCookie(cookieMap, "fid")+"userinfo")){//如果[fid]userinfo的cookie没有，则登录不成功（其中[fid]表示fid的cookie值）
//			return false;
//		}
//		if(!MyUtils.haveCookie(cookieMap, MyUtils.getCookie(cookieMap, "fid")+"UID")){//如果[fid]UID的cookie没有，则登录不成功（其中[fid]表示fid的cookie值）
//			return false;
//		}
//		if(!MyUtils.haveCookie(cookieMap, MyUtils.getCookie(cookieMap, "fid")+"enc")){//如果[fid]enc的cookie没有，则登录不成功（其中[fid]表示fid的cookie值）
//			return false;
//		}
//		//暂时认为能走到这里的都是登录成功的，也即是：如果fid、[fid]userinfo、[fid]UID和[fid]enc都存在则登录成功，不会去【http://passport.chaoxing.com/veryfyUser.shtml?uid=&enc=】验证cookie合法性
//		/**为了防止cookie的模拟，启用验证
//		* 去【http://passport.chaoxing,com/veryfyUser.shtml?uid=&enc=】验证cookie合法性
//		**/
//		String result = null;
//		String tUrl = "http://passport.chaoxing.com/veryfyUser.shtml?uid="+MyUtils.getCookie(cookieMap, MyUtils.getCookie(cookieMap, "fid")+"UID")+"&enc="+MyUtils.getCookie(cookieMap, MyUtils.getCookie(cookieMap, "fid")+"enc");
//		Object returnObj = HttpClientUtil.newInstance().post(tUrl);
//		if(returnObj instanceof Integer){
//			try {
//				throw new Exception("("+tUrl+")网络或者服务异常，请查证！");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}else{
//			result = (String)returnObj;
//		}
//		if(result==null||result.trim().equals("false")){//如果不合法，则登录不成功
//			return false;
//		}
//		return true;
//	}
	/**
	 * 通过cookie获得用户登录信息--慕课空间方式
	 * @param cookieMap
	 * @return
	 */
	public static JSONObject getUserInfo(HashMap<String, Cookie> cookieMap){
		JSONObject userInfo = null;
		String userinfo = MyUtils.getCookie(cookieMap, MyUtils.getCookie(cookieMap, "fid")+"userinfo");
		if(userinfo!=null&&!userinfo.trim().equals("")){
			try {
				userinfo = MyUtils.decrypt(userinfo, "402880E63011013011F10BB3005E","utf-8");
				userInfo = JSONObject.fromObject(userinfo);
			} catch (Exception e) {
				e.printStackTrace();
				userInfo = null;
			}
		}
		return userInfo;
	}
	
	/**
	 * 通过cookie验证是否登录成功--慕课空间方式
	 * @param cookieMap
	 * @return
	 */
//	public static boolean loginSuccess4YQ(HashMap<String, Cookie> cookieMap) {
//		if(!MyUtils.haveCookie(cookieMap, "orgFid")){//fid没有则必然没有登录成功
//			return false;
//		}
//		if(!MyUtils.haveCookie(cookieMap, MyUtils.getCookie(cookieMap, "orgFid")+"userinfo")){//如果[fid]userinfo的cookie没有，则登录不成功（其中[fid]表示fid的cookie值）
//			return false;
//		}
//		if(!MyUtils.haveCookie(cookieMap, MyUtils.getCookie(cookieMap, "orgFid")+"UID")){//如果[fid]UID的cookie没有，则登录不成功（其中[fid]表示fid的cookie值）
//			return false;
//		}
//		if(!MyUtils.haveCookie(cookieMap, MyUtils.getCookie(cookieMap, "orgFid")+"enc")){//如果[fid]enc的cookie没有，则登录不成功（其中[fid]表示fid的cookie值）
//			return false;
//		}
//		//暂时认为能走到这里的都是登录成功的，也即是：如果fid、[fid]userinfo、[fid]UID和[fid]enc都存在则登录成功，不会去【http://passport.chaoxing.com/veryfyUser.shtml?uid=&enc=】验证cookie合法性
//		/**为了防止cookie的模拟，启用验证
//		* 去【http://passport.chaoxing,com/veryfyUser.shtml?uid=&enc=】验证cookie合法性
//		**/
//		String result = null;
//		String tUrl = "http://passport.chaoxing.com/veryfyUser.shtml?uid="+MyUtils.getCookie(cookieMap, MyUtils.getCookie(cookieMap, "orgFid")+"UID")+"&enc="+MyUtils.getCookie(cookieMap, MyUtils.getCookie(cookieMap, "orgFid")+"enc");
//		Object returnObj = HttpClientUtil.newInstance().post(tUrl);
//		if(returnObj instanceof Integer){
//			try {
//				throw new Exception("("+tUrl+")网络或者服务异常，请查证！");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}else{
//			result = (String)returnObj;
//		}
//		if(result==null||result.trim().equals("false")){//如果不合法，则登录不成功
//			return false;
//		}
//		return true;
//	}
	/**
	 * 通过cookie获得用户登录信息--慕课空间方式
	 * @param cookieMap
	 * @return
	 */
	public static JSONObject getUserInfo4YQ(HashMap<String, Cookie> cookieMap){
		JSONObject userInfo = null;
		String userinfo = MyUtils.getCookie(cookieMap, MyUtils.getCookie(cookieMap, "orgFid")+"userinfo");
		if(userinfo!=null&&!userinfo.trim().equals("")){
			try {
				userinfo = MyUtils.decrypt(userinfo, "402880E63011013011F10BB3005E","utf-8");
				userInfo = JSONObject.fromObject(userinfo);
			} catch (Exception e) {
				e.printStackTrace();
				userInfo = null;
			}
		}
		return userInfo;
	}
	
	/**
	 * 补充用户信息
	 * @param 用户id  uid
	 * @param 用户对象  fu
	 */
//	public static void setUserFromRemot(String uid,User user,String ip) {
//		String md5str = uid+"uWwjeEKsri";
//		JSONObject obj = new JSONObject();
//		try {
//			md5str = DigestUtils.md5DigestAsHex(md5str.getBytes());
//			String reqstr ="http://passport2.chaoxing.com/api/userinfo?uid="+uid.trim()+"&enc="+md5str+"&ip="+ip;
//			String returnstr=HttpClientUtil.newInstance().get(reqstr);
//			obj = JSONObject.fromObject(returnstr);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		user.setName(obj.getString("uname"));
//		if(obj.containsKey("realname")&&obj.getString("realname")!=null&&!"".equals(obj.getString("realname"))){
//			user.setRealname(obj.getString("realname"));
//		}
//		if(obj.containsKey("nickname")&&obj.getString("nickname")!=null&&!"".equals(obj.getString("nickname"))){
//			user.setNickname(obj.optString("nickname", "realname"));
//		}
//		user.setEmail(obj.optString("email"));
//		user.setPhone(obj.optString("phone"));
//		try {
//			String roleStr = obj.getString("roleid");
//			user.setRole(roleStr==null||roleStr.trim().equals("")||roleStr.trim().equalsIgnoreCase("1")?1:(roleStr.trim().equalsIgnoreCase("3")?3:2));
//		} catch (Exception e) {
//			e.printStackTrace();
//			user.setRole(1);
//		}
//	}
	
	/**
	 * 字符串处理
	 * @param 字符串对象
	 * @return 处理后的字符串对象
	 */
	public static String notNull(String str) {
		return str == null ? "" : str;
	}
	
	 /** 
     * 对象转数组 
     * @param obj 
     * @return 
     */  
    public static byte[] toByteArray (Object obj) {     
        byte[] bytes = null;     
        ByteArrayOutputStream bos = new ByteArrayOutputStream();     
        try {       
            ObjectOutputStream oos = new ObjectOutputStream(bos);        
            oos.writeObject(obj);       
            oos.flush();        
            bytes = bos.toByteArray ();     
            oos.close();        
            bos.close();       
        } catch (IOException ex) {       
            ex.printStackTrace();  
        }     
        return bytes;   
    }  
    
	
    /**
     * 获得显示标签的所有页码
     * @param cur 当前页
     * @param max 最大页
     * @param before 当前页前面显示页码数目
     * @param after 当前页后面显示页码数目
     * @param autoReplenish 是否自动补偿长度到before+after+1
     * @return
     */
    public static int[] createPageLabels(int cur,int max,int before,int after,boolean autoReplenish){
    	int[] pageLabels;
    	int leng = before+1+after;
    	int start = cur-before;
    	int end = cur+after;
    	if(start<1){
    		start = 1;
    		if(autoReplenish){
    			end = start+leng-1;
    		}
    	}
    	if(end>max){
    		end = max;
    		if(autoReplenish){
    			start = end-leng+1;
    			if(start<1){
    				start = 1;
    			}
    		}
    	}
    	pageLabels = new int[end-start+1];
    	for(int i= start,j=0;i<=end;i++,j++){
    		pageLabels[j]=i;
    	}
    	return pageLabels;
    }
    
    /**
     * 将整形数组转换为用指定分隔符分隔的字符串
     * @param array 整形数组
     * @param separate 分隔符
     * @return
     */
    public static String intArrayToString(int[] array,String separate){
    	StringBuffer sb = new StringBuffer();
    	for(int i=0;i<array.length;i++){
    		if(i==0){
    			sb.append(array[i]);
    		}else{
    			sb.append(",").append(array[i]);
    		}
    	}
    	return sb.toString();
    }
    /**
     * 将用B为单位的文件大小数字转换为用K,M,G做单位的文件大小表示法,
     * 自动晋级选择合适的单位，并保留两位小数
     * @return
     */
    public static String retutnFileSizeStr(float size){
    	String sizeStr = null;
    	String[] sizeUnit = new String[]{"b","kb","mb","gb"};
    	int i=0;
    	for(;i<sizeUnit.length;i++){
    		if(size/1024>1){
    			size = size/1024;
    		}else{
    			break;
    		}
    	}
    	sizeStr = new DecimalFormat("#.00").format(size)+sizeUnit[i];
    	return sizeStr;
    }
    
    /**
	 * 补充用户信息
	 * @param 用户id  uid
	 * @param 用户对象  fu
	 */
//	public static JSONObject getUserFromPassport2(String uid,String ip) {
//		String md5str = uid+"uWwjeEKsri";
//		JSONObject obj = new JSONObject();
//		try {
//			md5str = DigestUtils.md5DigestAsHex(md5str.getBytes());
//			String reqstr ="http://passport2.chaoxing.com/api/userinfo?uid="+uid.trim()+"&enc="+md5str+"&ip="+ip;
//			String returnstr=HttpClientUtil.newInstance().get(reqstr);
//			obj = JSONObject.fromObject(returnstr);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//		return obj;
//	}
    
	
	// 根据 Agent 判断是否是智能手机
	public static boolean checkAgent(HttpServletRequest request) {
		String agent = request.getHeader("User-Agent");
		StringTokenizer st = new StringTokenizer(agent,";");
		st.nextToken();
		/*//得到用户的浏览器名
		String userbrowser = st.hasMoreTokens()?st.nextToken():null;
		//得到用户的操作系统名
		String useros = st.hasMoreTokens()?st.nextToken():null;*/
		//String flag = "电脑";
		boolean isMobileDevice = false;
		if (agent != null&&!agent.trim().equals("")) {
			String[] keywords = { "Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser" };
	
			// 排除 Windows 桌面系统
			if (!agent.contains("Windows NT") || (agent.contains("Windows NT") && agent.contains("compatible; MSIE 9.0;"))) {
				// 排除 苹果桌面系统
				if (!agent.contains("Windows NT") && !agent.contains("Macintosh")) {
					for (int i = 0; i < keywords.length; i++) {
						if (agent.contains(keywords[i])) {
							//flag = "移动设备";
							isMobileDevice = true;
							break;
						}
					}
				}
			}
		}
		return isMobileDevice;
	}

	/** 
	* 汉字转换位汉语拼音首字母，英文字符不变 
	* @param chines 汉字 
	* @return 拼音 
	*/  
//    public static String converterToFirstSpell(String chines){         
//        String pinyinName = "";  
//        char[] nameChar = chines.toCharArray();  
//        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
//        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
//        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
//        for (int i = 0; i < nameChar.length; i++) {  
//            if (nameChar[i] > 128) {  
//                try {  
//                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);  
//                } catch (BadHanyuPinyinOutputFormatCombination e) {  
//                    e.printStackTrace();  
//                }  
//            }else{  
//                pinyinName += nameChar[i];  
//            }  
//        }  
//        return pinyinName;  
//    }  
	   
    /** 
    * 汉字转换位汉语拼音，英文字符不变 
    * @param chines 汉字 
    * @return 拼音 
    */  
//    public static String converterToSpell(String chines){          
//        String pinyinName = "";  
//        char[] nameChar = chines.toCharArray();  
//        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
//        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
//        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
//        for (int i = 0; i < nameChar.length; i++) {  
//            if (nameChar[i] > 128) {  
//                try {  
//                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];  
//                } catch (BadHanyuPinyinOutputFormatCombination e) {  
//                    e.printStackTrace();  
//                }  
//            }else{  
//                pinyinName += nameChar[i];  
//            }  
//        }  
//        return pinyinName;  
//    }
	
//	public static void main(String[] args) throws DocumentException {
//		//System.out.println(dabolou_getStatus("53aa9cd953708fec17defbe7").toString());
//		System.out.println(converterToFirstSpell("一年级"));
//		System.out.println(converterToSpell("一年级"));
//	}
	public static String getUriFromStr(String queryStr) {
		if(queryStr==null||queryStr.trim().equals("")){
			return "/";
		}else{
			int ind = queryStr.indexOf("?");
			return ind>-1?queryStr.substring(0, ind):queryStr;
		}
	}

	/**
	 * 获取参数列表，放到数组中去，形如：[{name:name1,value:value1},{name:name2,value:value2},...]
	 * @param queryStr url完整字符串
	 * @param paramEncode 编码类型，-1表示url解码，0表示不做任何处理，1表示url编码
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static JSONArray getParamsFromStr(String queryStr,ParamEncode paramEncode) {
		JSONArray array = new JSONArray();
		if(queryStr==null||queryStr.trim().equals("")){
			return array;
		}else{
			int ind = queryStr.indexOf("?");
			if(ind>-1){
				String paramArray[] = queryStr.substring(ind+1).split("&");
				for(String paramStr :paramArray){
					if(paramStr!=null&&!paramStr.trim().equals("")){
						String paramKV[] = paramStr.split("=");
						JSONObject obj = new JSONObject();
						String value = null;
						if(paramEncode==ParamEncode.urlDecode){
							value =  paramKV.length==2?URLDecoder.decode(paramKV[1]):"";
						}else if(paramEncode==ParamEncode.normal){
							value =   paramKV.length==2?paramKV[1]:"";
						}else if(paramEncode==ParamEncode.urlEncode){
							value =  paramKV.length==2?URLEncoder.encode(paramKV[1]):"";
						}
						obj.put("name", paramKV[0]);
						obj.put("value",value);
						array.add(obj);
					}
				}
			}
			return array;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getParam(String url, String paramName,T defaultValue) {
		if(url==null||url.trim().equals("")){
			return defaultValue;
		}else{
			int ind = url.indexOf("?");
			if(ind>-1){
				String paramArray[] = url.substring(ind+1).split("&");
				for(String paramStr :paramArray){
					if(paramStr!=null&&!paramStr.trim().equals("")){
						String paramKV[] = paramStr.split("=");
						if(paramKV[0].trim().equals(paramName.trim())){
							return (T) paramKV[1];
						}
							
					}
				}
			}
			return defaultValue;
		}
	}
	
	public static String getFileSize(long filesize){
		float size = filesize;
		String[] sizeUnit = new String[]{"B","KB","MB","GB"};
		int i = 0;
		while(size>1024){
			size = size/1024;
			i++;
		}
		return String.format("%.2f",size)+sizeUnit[i];
	}
	
	public static Long getParam(HttpServletRequest request, String paramName,
			Long defaultValue) {
		String value = null;
		if(request.getParameter(paramName)!=null&&!request.getParameter(paramName).trim().equals("")){
			value = request.getParameter(paramName).trim();
			return Long.parseLong(value);
		}else{
			return defaultValue;
		}
	}
	
	/**
	 * 检查参数值是否合法，这里是检查参数值是否符合SQL的正常检索值，防止SQL拼接或注入
	 * @param paramValues
	 * @return 不合法返回false，合法返回true
	 */
	public static boolean validateParam4SQL(String... paramValues){
		String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"  
	            + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";  
		Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);  
	    for(String paramValue:paramValues){
	    	if (paramValue!=null&&!paramValue.trim().equals("")&&sqlPattern.matcher(paramValue).find()) {
	    		return false;
	    	}
	    }
	    return true;
	}
	
	/**
	 * 根据url,模拟浏览器发送请求返回head
	 * @param url
	 * @return
	 */
	@SuppressWarnings("finally")
//	public static List<CookieBean> simpleClientGetHeaders(String url,HttpServletRequest request){
//		String body = null;
//		HttpClient client = new HttpClient();
//		GetMethod method = new GetMethod(url);
//		method.addRequestHeader("ua",request.getHeader("user-agent"));
//		InputStream in = null ;
//		List<CookieBean> list = new ArrayList<CookieBean>();
//		try {
//			int status = client.executeMethod(method);
//			if(status == HttpStatus.SC_OK){
//				StringBuffer contentBuffer = new StringBuffer();
//			    in = method.getResponseBodyAsStream();
//	            BufferedReader reader = new BufferedReader(new InputStreamReader(in,method.getResponseCharSet()));
//	            String inputLine = null;
//	            while((inputLine = reader.readLine()) != null)
//	            {
//	            	contentBuffer.append(inputLine);
//	            }
//	            body = contentBuffer.toString();
//	            JSONObject json = null;
//	            try {
//					json = JSONObject.fromObject(body);
//					if(!json.getBoolean("result")){
//						return null;
//					}
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	            if(json==null){
//	            	return null;
//	            }
//			}
//			Header[] headers = method.getResponseHeaders();
//			for(int i=0;i<headers.length;i++){
//				String headername = headers[i].getName();
//				String headervalue = headers[i].getValue();
//				if(headername.equals("Set-Cookie")&&headervalue.indexOf("Domain=.chaoxing.com")!=-1){
//					CookieBean cb = new CookieBean();
//					String[] cookies = headervalue.split(";");
//					cb.setDomain(".chaoxing.com");
//					cb.setPath("/");
//					String[] names = cookies[0].split("=");
//					cb.setName(names[0]);
//					cb.setValue(names[1]);
//					for(int j=0;j<cookies.length;j++){
//						if(cookies[j].indexOf("Expires")!=-1){
//							String[] exps = cookies[j].split("=");
//							cb.setExpires(exps[1]);
//						}
//					}
//					list.add(cb);
//				}
//			}
//		} catch (HttpException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			try {
//				if(in!=null){
//					in.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//				in = null;
//			}
//			return list;
//		}
//	}
	
//	public static void sendMsg(HttpServletRequest request, HttpServletResponse response,String msg) throws IOException{
//		StringBuffer html = new StringBuffer();
//		html.append("<title>提示-").append(PropertiesReadTool.getContextProperty(Constants.UNITTITLE)).append("</title>");
//		html.append("<link rel='icon' href='http://").append(PropertiesReadTool.getContextProperty(Constants.UNITDOMAIN)).append("/favicon.ico'  type='image/x-icon'  />");
//		html.append("<link rel='shortcut icon' href='http://").append(PropertiesReadTool.getContextProperty(Constants.UNITDOMAIN)).append("/favicon.ico' type='image/x-icon'  />");
//		html.append("<div style='color:red;font-size:20;position:absolute;top:40%;left:40%;text-align:center;'>");
//		html.append(msg);
//		html.append("</div>");
//		MyUtils.htmlResult(request, response, html.toString());
//	}

	/**
	 * 根据域名，获得顶级域名，这里不支持ip，因为ip不支持cookie跨域
	 * @param domain
	 * @return
	 */
	public static String getTopDomain(String domain) {
		String[] domains = domain.split("\\.");
		String topDomain = domains[domains.length-2] +"."+ domains[domains.length-1];
		return topDomain;
	}
	
	/**
	 * 移除登录消息标记
	 * @param fromUrl
	 * @return
	 */
	public static String removeLoginMsg(String fromUrl){
		int index = fromUrl.indexOf("?");
		String resultstr = null;
		if(index>-1){
			String que = fromUrl.substring(0,index);
			resultstr = que + "?";
			String str = fromUrl.substring(index+1);
			String[] pas = str.split("&");
			for(String pam : pas){
				if(pam.equals("")){
					continue;
				}else{
					if(!pam.split("=")[0].equals("loginfailed")&&!pam.split("=")[0].equals("msg")&&!pam.split("=")[0].equals("ly")){
						if(resultstr.endsWith("?")){
							resultstr+=pam.split("=")[0]+"="+pam.substring(pam.split("=")[0].length()+1);
						}else{
							resultstr+="&"+pam.split("=")[0]+"="+pam.substring(pam.split("=")[0].length()+1);
						}
					}
				}
			}
		}else{
			resultstr = fromUrl;
		}
		return resultstr;
	}

//	public static JSONObject doc2JSONObj(Document doc) {
//		JSONObject docJSON = new JSONObject();
//		for(Fieldable field:doc.getFields()){
//			String value = field.stringValue();
//			value = value==null?"":value;
//			if(value.length()>500){
//				docJSON.accumulate(field.name(),value.substring(0, 500));
//			}else{
//				docJSON.accumulate(field.name(),value);
//			}
//		}
//		return docJSON;
//	}
	
//	public static JSONArray docList2JSONArray(List<Document> list) {
//		JSONArray docJSONArray = new JSONArray();
//		for(Document doc:list){
//			docJSONArray.add(doc2JSONObj(doc));
//		}
//		return docJSONArray;
//	}
//	
//	public static List<JSONObject> docList2List(List<Document> list) {
//		List<JSONObject> newList = new ArrayList<JSONObject>();
//		for(Document doc:list){
//			newList.add(doc2JSONObj(doc));
//		}
//		return newList;
//	}
//	
//	public static  List<?> docList2List(List<Document> list,Object t) {
//		List newList = new ArrayList();
//		for(Document doc:list){
//			if(t instanceof Course){
//				Course course = new Course();
//				JSONObject courseJSON = doc2JSONObj(doc);
//				course.setId(new CourseId(courseJSON.getString("id.courseId"), courseJSON.getString("id.unitId")));
//				course.setAuthor(courseJSON.optString("author"));
//				course.setAuthorOrg(courseJSON.optString("authorOrg"));
//				course.setAuthorPositional(courseJSON.optString("authorPositional"));
//				course.setClassfyId(courseJSON.optInt("classfyId"));
//				course.setDescr(courseJSON.optString("descr"));
//				course.setIsLocal(courseJSON.optBoolean("isLocal"));
//				course.setName(courseJSON.optString("name"));
//				course.setPeriod(courseJSON.optString("period"));
//				course.setPicUrl(courseJSON.optString("picUrl"));
//				course.setUrl(courseJSON.optString("url"));
//				newList.add(course);
//			}else{
//				newList.add(JSONObject.toBean(doc2JSONObj(doc),t.getClass()));
//			}
//		}
//		return newList;
//	}
	/**  
     * 读取配置文件  
     *   
     * @return  
     */  
    public static Properties getProperties(String file) {  
        Properties pro = null;  
        // 从文件mdxbu.properties中读取网元ID和模块ID信息  
        FileInputStream in = null;  
        try {  
            in = new FileInputStream(file);  
            pro = new Properties();  
            pro.load(in);  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return pro;  
    }  
  
    /**  
     * 保存属性到文件中  
     *   
     * @param pro  
     * @param file  
     */  
    public static void saveProperties(Properties pro, String file) {  
        if (pro == null) {  
            return;  
        }  
        FileOutputStream oFile = null;  
        try {  
            oFile = new FileOutputStream(file, false);  
            pro.store(oFile, "modify properties file");  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (oFile != null) {  
                    oFile.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /**  
     * 修改属性文件  
     *   
     * @param key  
     * @param value  
     */  
    public static void updateProperties(String key, String value, String file) {  
        // key为空则返回  
        if (key == null || "".equalsIgnoreCase(key)) {  
            return;  
        }  
        Properties pro = getProperties(file);  
        if (pro == null) {  
            pro = new Properties();  
        }  
        pro.put(key, value);  
  
        // 保存属性到文件中  
        saveProperties(pro, file);  
    }  
    
    /**
	 * 根据url,模拟浏览器发送请求返回head
	 * @param url
	 * @return
	 */
	@SuppressWarnings("finally")
//	public static JSONObject simpleClientGetHeaders(String url,HttpServletResponse response){
//		String body = null;
//		HttpClient client = new HttpClient();
//		GetMethod method = new GetMethod(url);
//		InputStream in = null ;
//		List<CookieBean> list = new ArrayList<CookieBean>();
//		JSONObject json = null;
//		try {
//			int status = client.executeMethod(method);
//			if(status == HttpStatus.SC_OK){
//				StringBuffer contentBuffer = new StringBuffer();
//			    in = method.getResponseBodyAsStream();
//	            BufferedReader reader = new BufferedReader(new InputStreamReader(in,method.getResponseCharSet()));
//	            String inputLine = null;
//	            while((inputLine = reader.readLine()) != null)
//	            {
//	            	contentBuffer.append(inputLine);
//	            }
//	            body = contentBuffer.toString();
//	            try {
//					json = JSONObject.fromObject(body);
//					if(!json.getBoolean("result")){
//						return null;
//					}
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	            if(json==null){
//	            	return null;
//	            }
//			}
//			Header[] headers = method.getResponseHeaders();
//			for(int i=0;i<headers.length;i++){
//				String headername = headers[i].getName();
//				String headervalue = headers[i].getValue();
//				if(headername.equals("Set-Cookie")&&headervalue.indexOf("Domain=.tjjy.com.cn")!=-1){
//					String[] cookies = headervalue.split(";");
//					String[] names = cookies[0].split("=");
//					MyUtils.write(names[0],names[1], ".chaoxing.com", response);
//				}
//			}
//		} catch (HttpException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			try {
//				if(in!=null){
//					in.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//				in = null;
//			}
//			return json;
//		}
//	}
	public static final int MAXAGE = 60 * 60 * 24;
	public static void write(String key,String value,String domain,HttpServletResponse resp){
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(MAXAGE);
		cookie.setDomain(domain);
		cookie.setPath("/");
		resp.addCookie(cookie);
	}
}
