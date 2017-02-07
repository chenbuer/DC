package dingchuang.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DC_News")
public class News implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 16)
	private Integer id;

	@Column(name = "title", nullable = false, unique = true)
	private String title;

	@Column(name = "descr", nullable = false, length = 65535)
	private String descr;// 摘要

	@Column(name = "content", length = 65535)
	private String content;

	@Column(name = "createTime", columnDefinition = "DATETIME")
	private Date createTime;

	@Column(name = "rec")
	private String rec;// 新闻是否推荐到首页，1--推荐到首页，2||null--不推荐

	public News() {
	}

	public News(Integer id, String title, String descr, String content,
			Date createTime, String rec) {
		super();
		this.id = id;
		this.title = title;
		this.descr = descr;
		this.content = content;
		this.createTime = createTime;
		this.rec = rec;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRec() {
		return rec;
	}

	public void setRec(String rec) {
		this.rec = rec;
	}

}