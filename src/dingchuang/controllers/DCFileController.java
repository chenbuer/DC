package dingchuang.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DCFileController {
	// ��־�������
	private static Log log = LogFactory.getLog(DCFileController.class);
	// �ļ�Ŀ¼����
	private String fileDir;
	// �ļ���׺����
	private String fileExt;
	// ��ǰվ��������
	private String pageCtx;
	// վ����ʵ·��
	private String relPath;
	// �ϴ��ļ�����·��
	private String savePath;
	// �����ϴ��ļ���׺MAP����
	private static final HashMap<String, String> extMap = new HashMap<String, String>();
	// �����ϴ��ļ���СMAP����
	private static final HashMap<String, Long> sizeMap = new HashMap<String, Long>();
	// �ϴ��ļ���Ÿ�Ŀ¼
	private String filePath = "/attached/";

	static {
		// ��ʼ��׺����MAP����
		extMap.put("image", "gif,jpg,jpeg,png,bmp,GIF,JPG,JPEG,PNG,BMP");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,txt,zip,rar");
		// ��ʼ�ļ���СMAP����
		sizeMap.put("image", 10 * 1024 * 1024l);
		sizeMap.put("flash", 30 * 1024 * 1024 * 1024l);
		sizeMap.put("media", 30 * 1024 * 1024 * 1024l);
		sizeMap.put("file", 10 * 1024 * 1024 * 1024l);
	}

	/**
	 * @category �ļ���ͼƬ�ϴ�
	 * @param imgFile
	 * @param request
	 * @param dir
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/file/upload")
	public @ResponseBody Map<String, Object> upload(MultipartFile imgFile,
			HttpServletRequest request, String dir) throws IOException {
		// ��ʼ��ر���
		Map<String, Object> execute = new HashMap<String, Object>();
		ServletContext context = request.getSession().getServletContext();
		pageCtx = context.getContextPath().concat(filePath);
		relPath = context.getRealPath(filePath);
		fileDir = dir;
		if (null == dir || dir.isEmpty()) {
			fileDir = "file";
		}
		// ����Ƿ����ϴ��ļ�
		if (null == imgFile) {
			execute.put("error", 1);
			execute.put("message", "��ѡ���ϴ��ļ�.");
			//return execute;
		}
		// ����ϴ��ļ�����Ŀ¼�Ƿ���ڻ��д
		if (!isExistOrRwFolder()) {
			execute.put("error", 1);
			execute.put("message", "�ϴ��ļ�����Ŀ¼�����ڻ�\n��û��д��Ȩ��,����.");
			//return execute;
		}
		// ���Ŀ¼�����Ƿ���ȷ
		if (!extMap.containsKey(fileDir)) {
			execute.put("error", 1);
			execute.put("message", "Ŀ¼������ȷ,����.");
			//return execute;
		}
		// ������ļ���׺��
		// String tempFileName = FilenameUtils.getName(imgFile
		// .getOriginalFilename());
		fileExt = FilenameUtils.getExtension(imgFile.getOriginalFilename());
		// ����ϴ��ļ�����
		if (!Arrays.<String> asList(extMap.get(fileDir).split(",")).contains(
				fileExt)) {
			execute.put("error", 1);
			execute.put("message", "�ϴ��ļ��ĸ�ʽ���ܾ�,\nֻ����" + extMap.get(fileDir)
					+ "��ʽ���ļ�.");
			//return execute;
		}
		// ����ϴ��ļ��Ĵ�С
		long maxSize = sizeMap.get(fileDir);
		if (imgFile.getSize() > maxSize) {
			execute.put("error", 1);
			String size = null;
			if (maxSize < 1024) {
				size = maxSize + "B";
			}
			if (maxSize > 1024 && maxSize < 1024 * 1024) {
				size = maxSize / 1024 + "KB";
			}
			if (maxSize > 1024 * 1024) {
				size = maxSize / (1024 * 1024) + "MB";
			}
			execute.put("message", "�ϴ��ļ���С��������,ֻ��\n���ϴ�С�� " + size + " ���ļ�.");
			return execute;
		}
		// �����µ��ļ���,�������ڷ���
		newSavePath();
		// �����ϴ��ļ���ָ�����Ŀ¼
		copy(imgFile.getInputStream(), savePath);
		// ������ļ����·��
		int point = savePath.lastIndexOf("/") - 8;
		StringBuilder url = new StringBuilder(pageCtx);
		url.append(fileDir).append("/");
		url.append(savePath.substring(point));
		// �����ϴ��ļ������·����ǰ��
		execute.put("error", 0);
		execute.put("url", url.toString());
		return execute;
	}
	
	/** 
     * @category �����ļ��ϴ��������Լ�Ŀ¼���� 
     * @param dir 
     * @param request 
     * @param path 
     * @param order 
     * @return 
     */  
    @RequestMapping("/file/manager")  
    public Map<String, Object> manager( String dir,HttpServletRequest request,  
             String path,  String order) {  
        // ��ʼ��ر���  
        Map<String, Object> execute = new HashMap<String, Object>();  
        ServletContext context=request.getSession().getServletContext();  
        pageCtx = context.getContextPath().concat(filePath);  
        relPath = context.getRealPath(filePath);  
        fileDir = dir;  
        if (null == dir || dir.isEmpty()) {  
            fileDir = "file";  
        }  
        if (!extMap.containsKey(fileDir)) {  
            execute.put("error", 1);  
            execute.put("message", "Ŀ¼������ȷ,����.");  
            return execute;  
        }  
        String tempPath = null == path ? fileDir.concat("/") : fileDir.concat("/"+path);  
        String curPath = pageCtx.concat(tempPath);  
        String curFileDir = relPath.concat("/"+tempPath);  
        String curDir = path;  
        String moveupDir = "";  
        // ��鵱ǰĿ¼�Ƿ�Ϊ��Ŀ¼  
        if (!"".equals(path)) {  
            String str = curDir.substring(0, curDir.length() - 1);  
            moveupDir = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";  
        }          
        // ���..����  
        if(path.indexOf("..") >= 0){  
            execute.put("error", 1);  
            execute.put("message", "������ʹ��..�������һ��.");  
            return execute;  
        }  
        //���һ���ַ�����/  
        if (!"".equals(path) && !path.endsWith("/")) {  
            execute.put("error", 1);  
            execute.put("message", "�ļ�·�����Ϸ�.");  
            return execute;  
        }  
        // ��鵱ǰĿ¼  
        File curPathFile = new File(curFileDir);  
        if (!curPathFile.isDirectory()) {  
            execute.put("error", 1);  
            execute.put("message", "��ǰĿ¼������.");  
            return execute;  
        }  
        //����Ŀ¼ȡ���ļ���Ϣ  
        @SuppressWarnings("rawtypes")  
        List<HashMap> fileList = new ArrayList<HashMap>();  
        if (curPathFile.listFiles() != null) {  
            for (File file : curPathFile.listFiles()) {  
                HashMap<String, Object> hash = new HashMap<String, Object>();  
                String fileName = file.getName();  
                if (file.isDirectory()) {  
                    hash.put("is_dir", true);  
                    hash.put("has_file", (file.listFiles() != null));  
                    hash.put("filesize", 0L);  
                    hash.put("is_photo", false);  
                    hash.put("filetype", "");  
                } else if (file.isFile()) {  
                    fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();  
                    hash.put("is_dir", false);  
                    hash.put("has_file", false);  
                    hash.put("filesize", file.length());  
                    hash.put("is_photo", Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt));  
                    hash.put("filetype", fileExt);  
                }  
                hash.put("filename", fileName);  
                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));  
                fileList.add(hash);  
            }  
        }  
        // �ļ�����ʽ  
        String tempOrder = order != null ? order.toLowerCase() : "name";  
        if ("size".equals(tempOrder)) {  
            Collections.sort(fileList, new SizeComparator());  
        } else if ("type".equals(tempOrder)) {  
            Collections.sort(fileList, new TypeComparator());  
        } else {  
            Collections.sort(fileList, new NameComparator());  
        }  
        // �����������ļ���Ϣ����  
        execute.put("moveup_dir_path", moveupDir);  
        execute.put("current_dir_path", curDir);  
        execute.put("current_url", curPath);  
        execute.put("total_count", fileList.size());  
        execute.put("file_list", fileList);          
        return execute;  
    }  
    
	/** 
     * �ж��ļ��ϴ�������ļ����Ƿ���ڻ��д 
     * @return ��������ҿ�д����"true",���򷵻�"false" 
     */  
    public boolean isExistOrRwFolder(){  
        if(null == relPath || relPath.isEmpty()) {  
            return false;  
        }  
        File folder = new File(relPath);  
        //�ļ�·���������򴴽�Ŀ¼  
        if(!folder.exists()) {  
            folder.mkdirs();  
        }  
        if(!folder.isDirectory())  
            return false;  
        if(!folder.canWrite())  
            return false;  
        return true;  
    }  
    
    /** 
     * @category �����µ��ļ���,�Ұ����ڷ������ 
     */  
    public void newSavePath() {  
        StringBuilder tempPath = new StringBuilder(relPath);  
        tempPath.append("/").append(fileDir).append("/");  
        SimpleDateFormat folderNameFormat = new SimpleDateFormat("yyyyMMdd");  
        tempPath.append(folderNameFormat.format(new Date()));  
        File temp = new File(tempPath.toString());  
        if(!temp.exists())  
            temp.mkdirs();  
        SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyyMMddkkmmss_S");  
        tempPath.append("/").append(fileNameFormat.format(new Date()));  
        tempPath.append(".").append(fileExt);  
        savePath = tempPath.toString().replaceAll("\\\\", "/");      
    }  
    
    /** 
     * @category �����ļ� 
     * @param src Դ�ļ� 
     * @param tar Ŀ��·�� 
     */  
    public void copy(InputStream src, String tar) {  
        // �ж�Դ�ļ���Ŀ��·���Ƿ�Ϊ��  
        if (null == src   
                || null == tar   
                || tar.isEmpty()) {  
            return;  
        }  
        //InputStream srcIs = null;  
        OutputStream tarOs = null;  
        try {  
            //srcIs = new FileInputStream(src);  
            File tarFile = new File(tar);  
            tarOs = new FileOutputStream(tarFile);  
            byte[] buffer = new byte[4096];  
            int n = 0;  
            while (-1 != (n = src.read(buffer))) {  
                tarOs.write(buffer, 0, n);  
            }  
        } catch (IOException e) {             
            //log.error("Copy File is Fali, Because "+e);
        	System.out.println("Copy File is Fali, Because "+e);
        } finally {  
            try {  
                if (null != src) {  
                    src.close();  
                }  
                if (null != tarOs) {  
                    tarOs.close();  
                }  
            } catch (IOException e) {  
//                log.error("Close Stream is Fail, Because "+e);
            	System.out.println("Close Stream is Fail, Because "+e);
            }  
        }  
    }  
    
    /** 
     * @category ��ѯ���������ļ��������ļ��������� 
     */  
    @SuppressWarnings("rawtypes")  
    public class NameComparator implements Comparator {  
  
        //��д�Ƚ���  
        @Override  
        public int compare(Object a, Object b) {  
            HashMap hashA = (HashMap) a;  
            HashMap hashB = (HashMap) b;  
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {  
                return -1;  
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {  
                return 1;  
            } else {  
                return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));  
            }  
        }  
    } 
    
    /** 
     * @category ��ѯ���������ļ��������ļ���С���� 
     */  
    @SuppressWarnings("rawtypes")  
    public class SizeComparator implements Comparator {  
  
        //��д�Ƚ���  
        @Override  
        public int compare(Object a, Object b) {  
            HashMap hashA = (HashMap) a;  
            HashMap hashB = (HashMap) b;  
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {  
                return -1;  
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {  
                return 1;  
            } else {  
                if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {  
                    return 1;  
                } else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {  
                    return -1;  
                } else {  
                    return 0;  
                }  
            }  
        }  
    }
    
    /** 
     * @category ��ѯ���������ļ��������ļ��������� 
     */  
    @SuppressWarnings("rawtypes")  
    public class TypeComparator implements Comparator {  
  
        //��д�Ƚ���  
        @Override  
        public int compare(Object a, Object b) {  
            HashMap hashA = (HashMap) a;  
            HashMap hashB = (HashMap) b;  
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {  
                return -1;  
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {  
                return 1;  
            } else {  
                return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));  
            }  
        }  
    }  
}
