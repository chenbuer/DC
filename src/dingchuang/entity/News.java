package dingchuang.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * News entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news")
public class News implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 991850207756832832L;
	private Integer id;
	private Integer unitId;
	private String title;
	private String descr;
	private String content;
	private String sfrom;
	private String editor;
	private String createTime;
	private Integer hits;
	private Integer sort;
	private String imgUrl;
	private Integer createUser;
	private Integer rec;//新闻是否推荐到首页，1--推荐到首页，2||null--不推荐

	/** default constructor */
	public News() {
	}

	/** minimal constructor */
	public News(Integer id, Integer unitId, String title, String content,
			String sfrom, String editor, String createTime, Integer sort,
			Integer createUser) {
		this.id = id;
		this.unitId = unitId;
		this.title = title;
		this.content = content;
		this.sfrom = sfrom;
		this.editor = editor;
		this.createTime = createTime;
		this.sort = sort;
		this.createUser = createUser;
	}

	/** full constructor */
	public News(Integer id, Integer unitId, String title, String descr,
			String content, String sfrom, String editor, String createTime,
			Integer hits, Integer sort, String imgUrl, Integer createUser) {
		this.id = id;
		this.unitId = unitId;
		this.title = title;
		this.descr = descr;
		this.content = content;
		this.sfrom = sfrom;
		this.editor = editor;
		this.createTime = createTime;
		this.hits = hits;
		this.sort = sort;
		this.imgUrl = imgUrl;
		this.createUser = createUser;
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

	@Column(name = "unitId", nullable = false, length = 100)
	public Integer getUnitId() {
		return this.unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
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

	@Column(name = "sfrom", nullable = false)
	public String getSfrom() {
		return this.sfrom;
	}

	public void setSfrom(String sfrom) {
		this.sfrom = sfrom;
	}

	@Column(name = "editor", nullable = false)
	public String getEditor() {
		return this.editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "hits")
	public Integer getHits() {
		return this.hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	@Column(name = "sort", nullable = false)
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "imgUrl")
	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Column(name = "createUser", nullable = false)
	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	@Transient
	public Integer getRec() {
		return rec;
	}

	public void setRec(Integer rec) {
		this.rec = rec;
	}

	
}