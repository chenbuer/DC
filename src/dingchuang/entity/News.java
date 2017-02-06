package dingchuang.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * News entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DC_News")
public class News implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 991850207756832832L;
	private Integer id;
	private String title;
	private String descr;
	private String content;
//	private String sfrom;
//	private String editor;
//	private String createTime;
//	private Integer hits;
//	private Integer sort;
//	private String imgUrl;
//	private Integer createUser;
//	private Integer rec;//新闻是否推荐到首页，1--推荐到首页，2||null--不推荐

	/** default constructor */
	public News() {
	}

	/** minimal constructor */
	public News(Integer id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}


	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 16)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "title", nullable = false)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "descr", length = 65535)
	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

		
}