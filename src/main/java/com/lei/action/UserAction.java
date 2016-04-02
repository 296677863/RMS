package com.lei.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.lei.model.Users;
import com.lei.service.UserService;
import com.lei.until.Constants;
import com.lei.until.PageUtil;
import com.lei.viewModel.GridModel;
import com.lei.viewModel.Json;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/user")
@Action(value="userAction")
public class UserAction extends BaseAction implements ModelDriven<Users>{

    
    /**
     * @author lei
     * 
     */
    private Users users;
    
    private UserService userService;
    
    private String isCheckedIds;
    
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
    
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getIsCheckedIds() {
        return isCheckedIds;
    }

    public void setIsCheckedIds(String isCheckedIds) {
        this.isCheckedIds = isCheckedIds;
    }




    private static final long serialVersionUID = 1L;
    
    public String findAllUserList(){
        
        Map<String,Object> map=new HashMap<String,Object>();
        if(null!=searchValue&&!"".equals(searchValue)){
            map.put(searchName, Constants.GET_SQL_LIKE+searchValue+Constants.GET_SQL_LIKE);
        }
        PageUtil pageUtil=new PageUtil(page,rows,searchAnds, searchColumnNames, searchConditions, searchVals);
        GridModel gridModel=new GridModel();
        
        gridModel.setRows(userService.findAllUserList(map, pageUtil));
        gridModel.setTotal(userService.getCount(map,pageUtil));
        OutputJson(gridModel);
        return null;
    }
    public String saveUserRoles(){
        Json json=new Json();
        if (userService.saveUserRoles(getModel().getUserId(), isCheckedIds)) {
            json.setStatus(true);
            json.setMessage("数据更新成功！");
        }else {
            json.setMessage("提交失败了！");
        }
        OutputJson(json);
        return null;
    }
    public String findUsersRolesList(){
       OutputJson(userService.findUsersRolesList(getModel().getUserId()));
       return null;
    }

    @Override
    public Users getModel() {
        
        if(null==users){
            users=new Users();
        }
        return users;
    }
    
    

}
