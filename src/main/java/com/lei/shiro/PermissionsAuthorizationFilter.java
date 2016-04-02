package com.lei.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

public class PermissionsAuthorizationFilter extends AuthorizationFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		String url=((ShiroHttpServletRequest)request).getRequestURI();
		if (SecurityUtils.getSubject().isAuthenticated()) {
			// TODO
			System.out.println("获取访问URL所需要的角色,然后看当前用户是否有此角色");
		} else {
			return false;
		}
		return true;
	}
	

}
