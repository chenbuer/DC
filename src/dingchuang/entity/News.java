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

	@Column(name = "descr", length = 65535)
	private String descr;// 摘要

	@Column(name = "content", nullable = false, length = 65535)
	private String content;

	@Column(name = "createTime", columnDefinition = "DATETIME")
	private Date createTime;

	@Column(name = "rec")
	private Integer rec;// 新闻是否推荐到首页，1--推荐到首页，2||null--不推荐

	public News() {
	}

	public News(Integer id, String title, String descr, String content,
			Date createTime, Integer rec) {
		super();
		this.id = id;
		this.title = title;
		this.descr = descr;
		this.content = content;
		this.createTime = createTime;
		this.rec = rec;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getRec() {
		return rec;
	}

	public void setRec(Integer rec) {
		this.rec = rec;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}