package com.qs.webside.agent.controller;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.exception.SystemException;
import com.qs.common.ip2region.DbSearcher;
import com.qs.common.util.DateUtil;
import com.qs.pub.game.model.CommonAgents;
import com.qs.pub.game.model.MemberBusiness;
import com.qs.pub.game.service.ICommonAgentService;
import com.qs.webside.member.mapper.MemberInviteMapper;
import com.qs.webside.member.model.MemberAgents;
import com.qs.webside.member.model.MemberInvite;
import com.qs.webside.shiro.ShiroAuthenticationManager;
import com.qs.webside.sys.model.ResourceEntity;
import com.qs.webside.sys.model.RoleEntity;
import com.qs.webside.sys.model.UserEntity;
import com.qs.webside.sys.service.LoginInfoService;
import com.qs.webside.sys.service.ResourceService;
import com.qs.webside.sys.service.RoleService;
import com.qs.webside.sys.service.UserService;
import com.qs.webside.util.EndecryptUtils;
import com.qs.webside.util.TreeUtil;

import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: IndexController
 * @Description: 登录、注册、退出、验证码
 * @author gaogang
 * @date 2016年7月12日 下午3:20:47
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class IndexController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private LoginInfoService loginInfoService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private Producer captchaProducer;
	
	@Autowired
	private DbSearcher ipSearcher;
	@Autowired
	private ICommonAgentService commonAgentService;
	
	@Resource
	private MemberInviteMapper memberInviteMapper;
	@Value("${wepay.appid}")
	private String appId;

	@Value("${wepay.appSecret}")
	private String secret;

	/**
	 * 用户登录
	 * 商务授权验证登入验证
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "wxLoginNotify.html")
	public String wxLoginNotify(HttpServletRequest request) {
		
		try{
			//页面授权connectQrconnect
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			String code=request.getParameter("code");

			SnsToken snsToken=SnsAPI.oauth2AccessToken(appId, secret,code);

			User user=SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(),"zh_CN");
			
			String openid=user.getOpenid();
			CommonAgents commonAgents=commonAgentService.selectByPrimaryKey(openid);


			if (commonAgents.getSitemid() !=null){
				return "redirect:/index.html";
			}else {
				request.setAttribute("error", "未授权！");
				return "/wxLoginNotify";
			}
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
        
	}

	@RequestMapping(value = "login.html", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String login(HttpServletRequest request) {
		try{
			request.removeAttribute("error");
			return "/login";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
	}
	@RequestMapping(value = "1login.html", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public void userLogin( HttpServletRequest request) {

		String url="http://1680a7z380.iask.in/qs-agent/login.html";//重定向地址
		String getUril=SnsAPI.connectOauth2Authorize(appId, url,true, "state");
		System.out.println(getUril);
		
	}
	
	/**
	 * 用户登录
	 * 认证过程：
	 * 1、想要得到Subject对象,访问地址必须在shiro的拦截地址内,不然会报空指针
	 * 2、用户输入的账号和密码,存到UsernamePasswordToken对象中,然后由shiro内部认证对比
	 * 3、认证执行者交由ShiroDbRealm中doGetAuthenticationInfo处理
	 * 4、当以上认证成功后会向下执行,认证失败会抛出异常
	 * 
	 * @param accountName	账户名称
	 * @param password	密码
	 * @return
	 */
	@RequestMapping(value = "login.html", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public String callBackUserLogin(String accountName, String password,String captcha
			, Boolean rememberMe, HttpServletRequest request) {

		UsernamePasswordToken token = null;
		try {
			//String expected = ShiroAuthenticationManager.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
			//获取用户页面输入的验证码
			//if(!StringUtils.equalsIgnoreCase(expected, captcha))

			if (accountName == null) {
				request.setAttribute("error", "请输入账号！");
				return "/login";
			}
			if (password == null) {
				request.setAttribute("error", "请输入密码！");
				return "/login";
			}

			if(false){
				request.setAttribute("error", "验证码错误！");
				return "/login";
			}else{
				// 想要得到Subject对象,访问地址必须在shiro的拦截地址内,不然会报空指针
				Subject subject = SecurityUtils.getSubject();


				String code=request.getParameter("code");

				//SnsToken snsToken=SnsAPI.oauth2AccessToken(appId, secret,code);
				//User user=SnsAPI.userinfo(snsToken.getAccess_token(), snsToken.getOpenid(),"zh_CN");
				String openid="oHFYXwk1W4AXd-7cGo6n6I0wrdBQ";
				//CommonAgents commonAgents=commonAgentService.selectByPrimaryKey(openid);

				accountName=openid;

				token = new UsernamePasswordToken(accountName, "qs2017");
				//记住用户登录状态
				token.setRememberMe(rememberMe);
				subject.login(token);
				if (subject.isAuthenticated()) {

					request.removeAttribute("error");
				} else {
					token.clear();
					request.setAttribute("error", "用户名或密码不正确！");
					return "/login";
				}
			}
		}catch (UnknownAccountException uae)
		{
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "账户不存在！");
			return "/login";
		}
		catch (IncorrectCredentialsException ice)
		{
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "密码错误！");
			return "/login";
		}catch (LockedAccountException e) {
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "您的账户已被锁定,请与管理员联系或10分钟后重试！");
			return "/login";
		} catch (ExcessiveAttemptsException e) {
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "您连续输错5次,帐号将被锁定10分钟!");
			return "/login";
		}catch(ExpiredCredentialsException eca)
		{
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "账户凭证过期！");
			return "/login";
		}catch (AuthenticationException e) {
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "账户验证失败！");
			return "/login";
		}catch (Exception e)
		{
			if(null != token)
			{
				token.clear();
			}
			request.setAttribute("error", "登录异常，请联系管理员！");
			return "/login";
		}
		return "redirect:/index.html";
	}


	/**
	 * 登入后数据页面 
	 * @return
	 */
	@RequestMapping(value = "index.html", method = RequestMethod.GET)
	public String index(Model model) {
		try
		{
			// 获取登录的bean
			MemberAgents memberBusiness = (MemberAgents)SecurityUtils.getSubject().getPrincipal();
			MemberInvite memberInvite = memberInviteMapper.selectByMid(memberBusiness.getMid());
			List<ResourceEntity> list = resourceService.findResourcesMenuByRoleId(com.qs.common.constant.Constants.Business.roleId);//根据角色id获取资源
			List<ResourceEntity> treeList = new TreeUtil().getChildResourceEntitys(list, null);
			model.addAttribute("list", treeList);
			model.addAttribute("menu", JSON.toJSONString(treeList));
			// 登陆的信息回传页面
			model.addAttribute("userEntity", memberBusiness);
			model.addAttribute("memberInvite", memberInvite);
			return "/index";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}

	}
	
	
	/**
	 * 用户注册
	 * 
	 * @param userEntity
	 * @return
	 */
	@RequestMapping(value = "register.html", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public String register(UserEntity userEntity) {
		try {
			String password = userEntity.getPassword();
			// 加密用户输入的密码，得到密码和加密盐，保存到数据库
			UserEntity user = EndecryptUtils.md5Password(userEntity.getAccountName(), userEntity.getPassword(), 2);
			//设置添加用户的密码和加密盐
			userEntity.setPassword(user.getPassword());
			userEntity.setCredentialsSalt(user.getCredentialsSalt());
			//设置创建者姓名
			userEntity.setCreatorName(userEntity.getUserName());
			userEntity.setCreateTime(new Date(System.currentTimeMillis()));
			//设置锁定状态：未锁定；删除状态：未删除
			userEntity.setLocked(0);
			userEntity.setDeleteStatus(0);
			//通过注册页面注册的用户统一设置为普通用户
			RoleEntity roleEntity = roleService.findByName("普通用户");
			userEntity.setRole(roleEntity);
			// 保存用户注册信息
			userService.insert(userEntity, password);
			return "login";
		}catch(Exception e)
		{
			throw new SystemException(e);
		}
		
	}
	

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "logout.html", method = RequestMethod.GET)
	public String logout() {
		// 注销登录
		ShiroAuthenticationManager.logout();
		return "redirect:login.html";
	}
	
	
	@RequestMapping(value = "captcha.html", method = RequestMethod.GET)
    public void kaptcha(HttpServletRequest req, HttpServletResponse rsp) {
		ServletOutputStream out = null;
		try {
	        rsp.setDateHeader("Expires", 0);
	        rsp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	        rsp.addHeader("Cache-Control", "post-check=0, pre-check=0");
	        rsp.setHeader("Pragma", "no-cache");
	        rsp.setContentType("image/jpeg");
	
	        String capText = captchaProducer.createText();
	        //将验证码存入shiro 登录用户的session
	        ShiroAuthenticationManager.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
	
	        BufferedImage image = captchaProducer.createImage(capText);
	        out = rsp.getOutputStream();
	        ImageIO.write(image, "jpg", out);
	        out.flush();
        }catch(IOException e)
		{
			throw new SystemException(e);
		} finally {
            try {
            	if(null != out)
            	{
            		out.close();
            	}
			} catch (IOException e) {
				logger.error("关闭输出流异常:"+e.getMessage());
			}
        }
    }
	
	/**
	 * 用户填写信息弹窗
	 */
	
	@RequestMapping(value = "userData.html")
	public String userData(Model model) {
		System.out.println("userdata");
		return "WEB-INF/view/index/userData";
	}

}
