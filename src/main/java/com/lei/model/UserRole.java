package com.lei.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name="USER_ROLE",catalog="rpmsystem")
@DynamicUpdate(true)
@DynamicInsert(true)
public class UserRole implements Serializable{

	/**
	 * author lei Date 2015-6-10
	 */
	private static final long serialVersionUID = 3531577372522971742L;
	
	
	private Integer id;//用户角色id
	private Users users;//用户
	private Role role;//角色
	private String status;//状态
	private Date created;//创建 的时间
	private Date lastmod;//修改时间
	private Integer creater;//创建者
	private Integer modifyer;//修改者
	
	public UserRole() {
		super();
	}

	public UserRole(Users users) {
		super();
		this.users = users;
	}

	public UserRole(Users users, Role role, String status, Date created,
			Date lastmod, Integer creater, Integer modifyer) {
		super();
		this.users = users;
		this.role = role;
		this.status = status;
		this.created = created;
		this.lastmod = lastmod;
		this.creater = creater;
		this.modifyer = modifyer;
	}
	@Id
	@GeneratedValue
	@Column(name="ID",unique=true,nullable=false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USER_ID",nullable=false)
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ROLE_ID")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	public Integer getModifyer() {
		return modifyer;
	}

	public void setModifyer(Integer modifyer) {
		this.modifyer = modifyer;
	}
	
	
	
	
	
	


}
