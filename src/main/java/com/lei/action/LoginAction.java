package com.lei.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.lei.service.LoginService;
import com.lei.shiro.CaptchaUsernamePasswordToken;
import com.lei.shiro.IncorrectCapchaException;
import com.lei.until.Constants;
import com.lei.viewModel.Json;



@Action(value="systemAction" ,results = { @Result(name = Constants.LOGIN_SUCCESS_URL, location = "/index.jsp"),
		@Result(name = "login",location = "/login.jsp"),
		@Result(name = "loginout",type="redirect",location = "systemAction!loginInit.action")})
public class LoginAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6693743774858859324L;
	private String username;
	private String password;
	private String captcha;
	private String userKey;
	private String userMacAddr;
	
	private LoginService loginService;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getUserMacAddr() {
		return userMacAddr;
	}
	public void setUserMacAddr(String userMacAddr) {
		this.userMacAddr = userMacAddr;
	}
	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	
	public String load() throws Exception{
		
		System.out.println(username+" "+password+" "+captcha+" "+userKey+" "+userMacAddr);
		
		Subject subject=SecurityUtils.getSubject();
		CaptchaUsernamePasswordToken token=new CaptchaUsernamePasswordToken();
		token.setUsername(username);
		token.setPassword(password.toCharArray());
		token.setCaptcha(captcha);
		token.setRememberMe(true);
		Json json=new Json();
		json.setTitle("登录提示");
		try{
            subject.login(token);
            System.out.println("sessionTimeout===>"+subject.getSession().getTimeout());
            json.setStatus(true);	
            
            
        }
        catch (UnknownSessionException use) {
            subject = new Subject.Builder().buildSubject();
            subject.login(token);
            //use.printStackTrace();
            json.setMessage(Constants.UNKNOWN_SESSION_EXCEPTION);
        }
        catch(UnknownAccountException ex){
        	json.setMessage(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
		}
        catch (IncorrectCredentialsException ice) {
           json.setMessage(Constants.INCORRECT_CREDENTIALS_EXCEPTION);
        } 
        catch (LockedAccountException lae) {
          json.setMessage(Constants.LOCKED_ACCOUNT_EXCEPTION);
        }catch (IncorrectCapchaException e) {
        	json.setMessage(Constants.INCORRECT_CAPTCHA_EXCEPTION);
		}
        catch (AuthenticationException ae) {
            json.setMessage(Constants.AUTHENTICATION_EXCEPTION);

        } 
        catch(Exception e){
          json.setMessage(Constants.UNKNOWN_EXCEPTION);
        }
        OutputJson(json,Constants.TEXT_TYPE_PLAIN);
		
		return null;
	}
	
	public String findAllFunctionList() throws Exception
	{
		OutputJson(loginService.findMenuList());
		return null;
	}
	
}
