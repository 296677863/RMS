package com.lei.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lei.dao.PublicDao;
import com.lei.model.Role;
import com.lei.model.UserRole;
import com.lei.model.Users;
import com.lei.service.UserService;
import com.lei.shiro.ShiroUser;
import com.lei.until.Constants;
import com.lei.until.PageUtil;
import com.lei.viewModel.UserRoleModel;

@Service("userService")
public class UserServiceImpl implements UserService {
    
    @SuppressWarnings("rawtypes")
    public PublicDao publicDao;
    
    private PublicDao publicDaoSQL;
    
    @Autowired
    public void setPublicDao(@SuppressWarnings("rawtypes") PublicDao publicDao){
        this.publicDao =publicDao;
    }
    
    




   @Autowired
    public void setPublicDaoSQL(PublicDao publicDaoSQL) {
        this.publicDaoSQL = publicDaoSQL;
    }




    @Override
    public List <Users>findAllUserList(Map<String, Object> map, PageUtil pageUtil) {
        
        String hql="from Users u where u.status='A'";
        hql+=Constants.getSearchConditionsHQL("u", map);
        
        hql+=Constants.getGradeSearchConditionsHQL("u",pageUtil);
        List<Users> list=publicDao.find(hql,map,pageUtil.getPage(),pageUtil.getRows());
        for(Users users:list){
            users.setUserRoles(null);
        }
        return list;
    }

    @Override
    public Long getCount(Map<String, Object> map, PageUtil pageUtil) {
        
        String hql="Select count(*) from  Users u where u.status='A'";
        hql+=Constants.getSearchConditionsHQL("u", map);
        hql+=Constants.getGradeSearchConditionsHQL("u", pageUtil);
        
        return publicDao.count(hql, map);
    }


    @Override
    public List<UserRoleModel> findUsersRolesList(Integer userId) {
       String sql="Select ur.USER_ID,ur.ROLE_ID from \n"+
       "USER_ROLE as ur where ur.status='A' and ur.USER_ID="+userId;
       List list=publicDaoSQL.findBySQL(sql);
       List<UserRoleModel> listm=getUserRoleModeList(userId,list);
        return listm;
    }






    private List<UserRoleModel> getUserRoleModeList(Integer userId, List list) {
        List<UserRoleModel> listm=new ArrayList<UserRoleModel>();
        for (Object object : list)
        {
            Object[] obj=(Object[])object;
            UserRoleModel userRoleModel=new UserRoleModel();
            userRoleModel.setUserId(userId);
            userRoleModel.setRoleId(obj[1]==null?null:Integer.valueOf(obj[1].toString()));
            listm.add(userRoleModel);
        }
        return listm;
    }






    @Override
    public boolean saveUserRoles(Integer userId, String isCheckedIds) {
        Users user = (Users) publicDao.get(Users.class, userId);
        Set<UserRole> set = user.getUserRoles();
        Map<Integer, UserRole> map=new HashMap<Integer, UserRole>(); 
        for (UserRole userRole : set)
        {
            map.put(userRole.getRole().getRoleId(), userRole);
            userRole.setLastmod(new Date());
            userRole.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
            publicDaoSQL.deleteToUpdate(userRole);
        }
        if (!"".equals(isCheckedIds)&&isCheckedIds.length()!=0)
        {
            String[] ids=isCheckedIds.split(",");
            ShiroUser currUser = Constants.getCurrendUser();
            for (String id : ids)
            {
                Integer tempId = Integer.valueOf(id);
                Role role = (Role)publicDaoSQL.get(Role.class, Integer.valueOf(id));
                UserRole userRole=null;
                if (map.containsKey(tempId))
                {
                    userRole=map.get(tempId);
                    userRole.setStatus(Constants.PERSISTENCE_STATUS);
                    userRole.setCreater(currUser.getUserId());
                    userRole.setModifyer(currUser.getUserId());
                    publicDaoSQL.update(userRole);
                }else {
                    userRole=new UserRole();
                    userRole.setCreated(new Date());
                    userRole.setLastmod(new Date());
                    userRole.setRole(role);
                    userRole.setUsers(user);
                    userRole.setCreater(currUser.getUserId());
                    userRole.setModifyer(currUser.getUserId());
                    userRole.setStatus(Constants.PERSISTENCE_STATUS);
                    publicDaoSQL.save(userRole);
                }
            }
        }
        return true;
    }

}
