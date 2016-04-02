package com.lei.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.lei.model.Role;
import com.lei.service.PermissionAssignmentService;
import com.lei.until.Constants;
import com.lei.viewModel.GridModel;
import com.lei.viewModel.Json;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/permission")
@Action(value="permissionAssignmentAction")
public class PermissionAssignmentAction extends BaseAction implements ModelDriven<Role>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2694150089830456709L;
	private PermissionAssignmentService permissionAssignmentService;
	private Integer id;
	private String checkedIds;
	private Role role;
	
	public Role getRole(){
		return role;
	}
	
	public void setRole(Role role){
		this.role=role;
	}
	
	
	public String getCheckedIds(){
		return checkedIds;
	}
	
	public void setCheckedIds(String checkedIds){
		this.checkedIds=checkedIds;
	}
	
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id=id;
	}

	
	@Autowired
	public void setPermissionAssignmentService(PermissionAssignmentService permissionAssignmentService){
		this.permissionAssignmentService=permissionAssignmentService;
	}
	
	public String findAllRoleList() throws Exception{
		Map<String,Object> map=searchRole();
		GridModel gridModel=new GridModel();
		gridModel.setRows(permissionAssignmentService.findAllRoleList(map,page,rows,true));
		gridModel.setTotal(permissionAssignmentService.getCount(map));
		OutputJson(gridModel);
		return null;
	}
	
	private Map<String, Object> searchRole() {
	    Map<String, Object> map = new HashMap<String, Object>();
        if (null != searchValue && !"".equals(searchValue)) {
            map.put(searchName, Constants.GET_SQL_LIKE + searchValue
                    + Constants.GET_SQL_LIKE);
        }
        return map;
		
	}
	
	public String findAllRoleListNotPage(){
	    Map<String,Object> map=searchRole();
	    GridModel gridModel=new GridModel();
	    gridModel.setRows(permissionAssignmentService.findAllRoleList(map, null, null, false));
	    OutputJson(gridModel);
	    return null;
	}
	
	
	public String findAllFunctionList(){
	    OutputJson(permissionAssignmentService.findAllFunctionsList(id));
	    return null;
	}
	
	public String getRolePermission(){
	    
	    OutputJson(permissionAssignmentService.getRolePermission(getModel().getRoleId()));
	    return null;
	}
	
	public String savePermission(){
	    Json json = new Json();
        Role r = getModel();
        Integer roleId = r.getRoleId();
        Integer userId = Constants.getCurrendUser().getUserId();
        if (permissionAssignmentService.savePermission(userId,roleId, checkedIds)) {
            json.setStatus(true);
            json.setMessage("分配成功！查看已分配权限请重新登录！");
        } else {
            json.setMessage("分配失败！");
        }
        OutputJson(json);
        return null;
	}

	@Override
	public Role getModel() {
		
		if(null==role){
			role=new Role();
		}
		return role;
	}
	

}
