package com.lei.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/*
 * author lei Date 2015-6-10
 */


@Entity
@Table(name="USERS",catalog="rpmsystem")
@DynamicUpdate(true)
@DynamicInsert(true)
public class Users implements Serializable{
	private static final long serialVersionUID = 452413743310340458L;
	
	
	private Integer userId;//用户id
	private String myid;  //
	private String account; //
	private String name;   //用户名
	private Integer organizeId; //用户组织id
	private String organizeName; //用户组织名
	private Integer dutyId; //用户职责id
	private Integer titleId; // 
	
	private String password; //用户密码
	private String email; //用户email;
	private String lang ;
	
	private String theme;  
	private Date firstVisit;  //第一次登入的时间
	private Date previousVisit;// 上一次登入的时间
	private Date lastVisit;  //最后登入的时间
	
	private Integer loginCount ;  //登入的次数
	private Integer isemployee;//是否是员工
	private String status; //当前的状态
	private String  ip; //用户登入的ip 地址
	private String description;//用户的描述
	
	
	private  Integer questionId;  //提问的id
	private String answer;  //回答
	private Integer isonline;
	private Date created;  //创建的时间
	private Date lastmod;//最后修改的时间
	private Integer creater;//创建人
	private Integer modifer;//修改人
	private String tel;//电话
	
	
	private Set<UserRole>  userRoles=new HashSet<UserRole>(0);
	public Users(){}

	public Users(Integer userId, String myid, String account, String name,
			Integer organizeId, String organizeName, Integer dutyId,
			Integer titleId, String password, String email, String lang,
			String theme, Date firstVisit, Date previousVisit, Date lastVisit,
			Integer loginCount, Integer isemployee, String status, String ip,
			String description, Integer questionId, String answer,
			Integer isonline, Date created, Date lastmod, Integer creater,
			Integer modifer, String tel, Set<UserRole> userRoles) {
		super();
		this.userId = userId;
		this.myid = myid;
		this.account = account;
		this.name = name;
		this.organizeId = organizeId;
		this.organizeName = organizeName;
		this.dutyId = dutyId;
		this.titleId = titleId;
		this.password = password;
		this.email = email;
		this.lang = lang;
		this.theme = theme;
		this.firstVisit = firstVisit;
		this.previousVisit = previousVisit;
		this.lastVisit = lastVisit;
		this.loginCount = loginCount;
		this.isemployee = isemployee;
		this.status = status;
		this.ip = ip;
		this.description = description;
		this.questionId = questionId;
		this.answer = answer;
		this.isonline = isonline;
		this.created = created;
		this.lastmod = lastmod;
		this.creater = creater;
		this.modifer = modifer;
		this.tel = tel;
		this.userRoles = userRoles;
	}


	@Id
	@GeneratedValue
	@Column(name="USER_ID",unique=true,nullable=false)
	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name="MYID",length=50)
	public String getMyid() {
		return myid;
	}


	public void setMyid(String myid) {
		this.myid = myid;
	}

	@Column(name="ACCOUNT",length=50)
	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	@Column(name = "NAME", length = 50)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Column(name = "ORGANIZE_ID")
	public Integer getOrganizeId() {
		return organizeId;
	}


	public void setOrganizeId(Integer organizeId) {
		this.organizeId = organizeId;
	}

	@Column(name = "ORGANIZE_NAME")
	public String getOrganizeName() {
		return organizeName;
	}


	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}


	@Column(name = "DUTY_ID")
	public Integer getDutyId() {
		return dutyId;
	}


	public void setDutyId(Integer dutyId) {
		this.dutyId = dutyId;
	}

	@Column(name = "TITLE_ID")
	public Integer getTitleId() {
		return titleId;
	}


	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}


	@Column(name = "PASSWORD", length = 128)
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL", length = 200)
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name = "LANG", length = 20)
	public String getLang() {
		return lang;
	}


	public void setLang(String lang) {
		this.lang = lang;
	}

	@Column(name = "THEME", length = 20)
	public String getTheme() {
		return theme;
	}


	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FIRST_VISIT", length = 10)
	public Date getFirstVisit() {
		return firstVisit;
	}


	public void setFirstVisit(Date firstVisit) {
		this.firstVisit = firstVisit;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PREVIOUS_VISIT", length = 10)
	public Date getPreviousVisit() {
		return previousVisit;
	}


	public void setPreviousVisit(Date previousVisit) {
		this.previousVisit = previousVisit;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_VISITS", length = 10)
	public Date getLastVisit() {
		return lastVisit;
	}


	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	@Column(name = "LOGIN_COUNT")
	public Integer getLoginCount() {
		return loginCount;
	}


	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	@Column(name = "ISEMPLOYEE")
	public Integer getIsemployee() {
		return isemployee;
	}


	public void setIsemployee(Integer isemployee) {
		this.isemployee = isemployee;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "IP", length = 20)
	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "DESCRIPTION", length = 2000)
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "QUESTION_ID")
	public Integer getQuestionId() {
		return questionId;
	}


	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Column(name = "ANSWER", length = 100)
	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "ISONLINE")
	public Integer getIsonline() {
		return isonline;
	}


	public void setIsonline(Integer isonline) {
		this.isonline = isonline;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", length = 10)
	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTMOD", length = 10)
	public Date getLastmod() {
		return lastmod;
	}


	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

	@Column(name = "CREATER")
	public Integer getCreater() {
		return creater;
	}


	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	@Column(name = "MODIFYER")
	public Integer getModifer() {
		return modifer;
	}


	public void setModifer(Integer modifer) {
		this.modifer = modifer;
	}

	@Column(name = "TEL", length = 30)
	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}


	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	
	
	
	
	
	

}
