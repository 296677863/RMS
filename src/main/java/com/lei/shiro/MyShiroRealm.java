package com.lei.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lei.model.Users;
import com.lei.until.Constants;

public class MyShiroRealm extends AuthorizingRealm{
	private SessionFactory hibernateSessionFactory;
	

	public SessionFactory getHibernateSessionFactory() {
		return hibernateSessionFactory;
	}
	@Autowired
	public void setHibernateSessionFactory(SessionFactory hibernateSessionFactory) {
		
		this.hibernateSessionFactory = hibernateSessionFactory;
		System.out.println("hibernateSessionFactory 被注入");
	}
	public Session getCurrentSession(){
		return hibernateSessionFactory.getCurrentSession();
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("开始授权");
		ShiroUser shiroUser=(ShiroUser) principals.fromRealm(getName()).iterator().next();
		
		System.out.println(shiroUser.toString());
		String username = shiroUser.getAccount();
		if (username != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 查询用户授权信息
			// info.addRole("admin");
			String sql = null;
			// 超级管理员默认拥有所有操作权限
			if (Constants.SYSTEM_ADMINISTRATOR.equals(username)) {
				sql = "SELECT p.PERMISSION_ID,p.MYID FROM PERMISSION AS p\n"
						+ "where p.STATUS='A' and p.TYPE='O' and p.ISUSED='Y'";
			} else {
				sql = "SELECT DISTINCT rp.PERMISSION_ID,p.MYID FROM\n"
						+ "ROLE_PERMISSION AS rp\n"
						+ "INNER JOIN ROLE AS r ON rp.ROLE_ID = r.ROLE_ID\n"
						+ "INNER JOIN USER_ROLE AS ur ON rp.ROLE_ID = ur.ROLE_ID\n"
						+ "INNER JOIN USERS AS u ON u.USER_ID = ur.USER_ID\n"
						+ "INNER JOIN PERMISSION AS p ON rp.PERMISSION_ID = p.PERMISSION_ID\n"
						+ "WHERE rp.STATUS='A' and r.STATUS='A' and ur.STATUS='A' and u.STATUS='A' and p.STATUS='A' and p.TYPE='O' and p.ISUSED='Y'\n"
						+ "and u.NAME ='" + username + "'";
			}
			List perList = this.getHibernateSessionFactory().getCurrentSession()
					.createSQLQuery(sql).list();
			if (perList != null && perList.size() != 0) {
				for (Object object : perList) {
					Object[] obj = (Object[]) object;
					info.addStringPermission(obj[1].toString());
				}
				return info;
			}
		}
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authToken) throws AuthenticationException {
		CaptchaUsernamePasswordToken token=(CaptchaUsernamePasswordToken)authToken;
		String username=token.getUsername();
		if(username!=null&&!"".equals(username)&&doCapchaValidate(token)){
			SessionFactory s=this.getHibernateSessionFactory();
			if(s.getCurrentSession()!=null){
				String hql="from Users t where t.status='A' and t.name=:name";
				Users users=(Users)s.getCurrentSession().createQuery(hql)
						.setParameter("name", username).uniqueResult();
				if(users!=null){
					Subject subject=SecurityUtils.getSubject();
					subject.getSession().setAttribute("shiroUser",new ShiroUser(users.getUserId(),users.getAccount()));
					return new SimpleAuthenticationInfo(new ShiroUser(
							users.getUserId(), users.getAccount()),
							users.getPassword(), getName());
				}
				
			}
		}
		return null;
	}

	private boolean doCapchaValidate(CaptchaUsernamePasswordToken token) {
		String captcha =(String)ServletActionContext.getRequest().getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if(captcha!=null&&!captcha.equalsIgnoreCase(token.getCaptcha())){
				throw new IncorrectCapchaException("验证码错误");
		}
		return true;
	}
	

}
