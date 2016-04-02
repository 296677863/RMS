package com.lei.service;

import java.util.List;
import java.util.Map;

import com.lei.until.PageUtil;
import com.lei.viewModel.UserRoleModel;

public interface UserService {
   
    List findAllUserList(Map<String, Object> map, PageUtil pageUtil);

    Long getCount(Map<String, Object> map, PageUtil pageUtil);

    List<UserRoleModel> findUsersRolesList(Integer userId);
    
    boolean saveUserRoles(Integer userId, String isCheckedIds );

}
