package info.baitian.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;



public class Guest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private List<MultipartFile> images;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Guest(String name, List<MultipartFile> images) {
		super();
		this.name = name;
		this.images = images;
	}
	public Guest() {
	}
	public List<MultipartFile> getImages() {
		return images;
	}
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	
	
	
}
