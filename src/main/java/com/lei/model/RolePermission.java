package com.lei.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "ROLE_PERMISSION", catalog = "rpmsystem")
@DynamicUpdate(true)
@DynamicInsert(true)
public class RolePermission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8174285122848260787L;
	
	
	private Integer id; //权限id
	
	private Role role;  //角色
	private Permission permission ; //权限
	private String status ;//状态
	private Date created ;
	private Date lastmod;
	private Integer creater;
	private Integer modifyer;
	
	public RolePermission(){}
	
	public RolePermission(Role role,Permission permission){
		this.role=role;
		this.permission=permission;
	}

	public RolePermission(Role role, Permission permission, String status,
			Date created, Date lastmod, Integer creater, Integer modifyer) {
		super();
		this.role = role;
		this.permission = permission;
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

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="ROLE_ID",nullable=false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="PERMISSION_ID",nullable=false)
	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Column(name="STATUS",length=1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED",length=10)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LASTMOD",length=10)
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
	@Column(name="MODIFYE")
	public Integer getModifyer() {
		return modifyer;
	}

	public void setModifyer(Integer modifyer) {
		this.modifyer = modifyer;
	}
	
	
	

}
