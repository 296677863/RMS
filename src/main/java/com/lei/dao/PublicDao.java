package com.lei.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.lei.model.Permission;
import com.lei.model.Role;
import com.lei.model.RolePermission;
import com.lei.model.UserRole;




public interface PublicDao<T> {
	
	public void update(T o);
	
	public void delete(T o);
	public void saveOrUpdate(T o);

	public List findBySQL(String sql);

	public T get(Class<T> class1, Serializable id);

	public List<Role> find(String hql, Map<String, Object> param, Integer page,
			Integer rows);

	public List<Role> find(String hql, Map<String, Object> param);
	
	public Long count(String hql,Map<String,Object> param);

    public List<T> find(String hql);

    public Serializable save(T o);

    public void deleteToUpdate(T o);

  

}
