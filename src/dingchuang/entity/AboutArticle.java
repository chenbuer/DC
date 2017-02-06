package dingchuang.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="DC_AboutArticle")
public class AboutArticle {

	@Id
	private int aid;
	private String aTitle;
	@Type(type="text")
	private String aContent;
	
	
	
	
	public AboutArticle(String aTitle, String aContent) {
		super();
		this.aTitle = aTitle;
		this.aContent = aContent;
	}




	public int getAid() {
		return aid;
	}




	public void setAid(int aid) {
		this.aid = aid;
	}




	public String getaTitle() {
		return aTitle;
	}




	public void setaTitle(String aTitle) {
		this.aTitle = aTitle;
	}




	public String getaContent() {
		return aContent;
	}




	public void setaContent(String aContent) {
		this.aContent = aContent;
	}




	public AboutArticle() {
	}
	
	

}
