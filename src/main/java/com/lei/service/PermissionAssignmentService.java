package com.lei.service;



import java.util.List;
import java.util.Map;

import com.lei.model.Permission;
import com.lei.model.Role;
import com.lei.viewModel.TreeGrid;


public interface PermissionAssignmentService {

	

	Long getCount(Map<String, Object> param);

	boolean savePermission(Integer userId, Integer roleId, String checkedIds);

	List<Permission> getRolePermission(Integer roleId);

	boolean persistenceRole(Map<String, List<Role>> map);

	List<Role> findAllRoleList(Map<String, Object> param, Integer page,
			Integer rows, boolean isPage);
	
	

	boolean persistenceRole(Role r);

	boolean persistenceRole(Integer roleId);

    List<TreeGrid> findAllFunctionsList(Integer id);
}
