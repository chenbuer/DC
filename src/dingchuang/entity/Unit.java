package dingchuang.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

/**
 * Unit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "unit")
public class Unit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8326576532957059544L;
	private Integer id;
	private String name;
	private String descr;
	private String websiteAddr;
	private String path;
	private Integer fid;
	private String domain;
	private String sid;
	private String spaceUrl;
	private Timestamp creTime;
	private Timestamp updTime;
	/*private Date begTime;
	private Date endTime;*/
	private Integer status;
	private String passpUrl;
	private String exitUrl;
	private Integer loginType;
	private String periodId;
	private Boolean isSchool;
	private Integer pid;
	private String pName;
	private Boolean isVirtual;
	private Boolean openRegistry;
	private String icon;
	private String logo;
//	private Set<Dept> depts = new HashSet<Dept>(0);
	private String state;
	private String banner;
	/*private Integer groupId;*/

	// Constructors

	/** default constructor */
	public Unit() {
	}

	/** minimal constructor */
	public Unit(String name, String path, Timestamp creTime, Timestamp updTime) {
		this.name = name;
		this.path = path;
		this.creTime = creTime;
		this.updTime = updTime;
	}

	/** full constructor */
	public Unit(String name, String descr, String path, Integer fid,
			String domain, String sid, String spaceUrl, Timestamp creTime,
			Timestamp updTime, Integer status,
			String passpUrl, String exitUrl, Integer loginType,
			String periodId, Boolean isSchool, Integer pid, Boolean isVirtual/*,
			Set<Dept> depts*/) {
		this.name = name;
		this.descr = descr;
		this.path = path;
		this.fid = fid;
		this.domain = domain;
		this.sid = sid;
		this.spaceUrl = spaceUrl;
		this.creTime = creTime;
		this.updTime = updTime;
		this.status = status;
		this.passpUrl = passpUrl;
		this.exitUrl = exitUrl;
		this.loginType = loginType;
		this.periodId = periodId;
		this.isSchool = isSchool;
		this.pid = pid;
		this.isVirtual = isVirtual;
//		this.depts = depts;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "descr", length = 65535)
	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Column(name = "path", nullable = false)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "fid")
	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	@Column(name = "domain")
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name = "sid")
	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	@Column(name = "spaceUrl")
	public String getSpaceUrl() {
		return this.spaceUrl;
	}

	public void setSpaceUrl(String spaceUrl) {
		this.spaceUrl = spaceUrl;
	}

	@Column(name = "creTime", nullable = false, length = 19)
	public Timestamp getCreTime() {
		return this.creTime;
	}

	public void setCreTime(Timestamp creTime) {
		this.creTime = creTime;
	}

	@Column(name = "updTime", nullable = false, length = 19)
	public Timestamp getUpdTime() {
		return this.updTime;
	}

	public void setUpdTime(Timestamp updTime) {
		this.updTime = updTime;
	}

	/*@Temporal(TemporalType.DATE)
	@Column(name = "begTime", length = 10)
	public Date getBegTime() {
		return this.begTime;
	}

	public void setBegTime(Date begTime) {
		this.begTime = begTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "endTime", length = 10)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}*/

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "passpUrl")
	public String getPasspUrl() {
		return this.passpUrl;
	}

	public void setPasspUrl(String passpUrl) {
		this.passpUrl = passpUrl;
	}

	@Column(name = "exitUrl")
	public String getExitUrl() {
		return this.exitUrl;
	}

	public void setExitUrl(String exitUrl) {
		this.exitUrl = exitUrl;
	}

	@Column(name = "loginType")
	public Integer getLoginType() {
		return this.loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	@Column(name = "periodId")
	public String getPeriodId() {
		return this.periodId;
	}

	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}

	@Column(name = "isSchool")
	public Boolean getIsSchool() {
		return this.isSchool;
	}

	public void setIsSchool(Boolean isSchool) {
		this.isSchool = isSchool;
	}

	@Column(name = "pid")
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

//	@Column(name = "isVirtual")
//	public Boolean getIsVirtual() {
//		if(!this.isVirtual){
//			this.websiteAddr = "http://"+this.domain==null?Constants.UNITDOMAIN+"?"+Constants.UNIT+"="+this.id:"http://"+this.domain;
//		}
//		return this.isVirtual;
//	}

	public void setIsVirtual(Boolean isVirtual) {
		this.isVirtual = isVirtual;
	}

	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "unit")
//	@Transient
//	public Set<Dept> getDepts() {
//		return this.depts;
//	}
//
//	public void setDepts(Set<Dept> depts) {
//		this.depts = depts;
//	}

	@Transient
	public String getWebsiteAddr() {
		return websiteAddr;
	}

	public void setWebsiteAddr(String websiteAddr) {
		this.websiteAddr = websiteAddr;
	}

	@Column(name = "openRegistry")
	public Boolean getOpenRegistry() {
		return openRegistry;
	}

	@Column(name = "icon")
	public String getIcon() {
		return icon;
	}

	@Column(name = "logo")
	public String getLogo() {
		return logo;
	}

	public void setOpenRegistry(Boolean openRegistry) {
		this.openRegistry = openRegistry;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Formula("(CASE WHEN  exists(select 1 from unit a  where a.pid = id )  THEN 'closed' " +
            " ELSE 'open'  END)")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column(name="banner")
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	@Formula("(CASE WHEN exists(select 1 from unit u where u.id=pid) THEN (select u.name from unit u where u.id=pid)" +
			" ELSE '未知机构' END )")
	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}
	/*@Column(name="groupId")
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}*/
	
}