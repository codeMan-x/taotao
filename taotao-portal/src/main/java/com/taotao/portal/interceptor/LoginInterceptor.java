package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.Impl.UserServiceImpl;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 在handler执行之前处理
		// 判断用户是否登录
		// 从cookie中取token；从根据token取用户信息
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		// 取不到用户信息；跳转到登录，把用户请求url传递给登录界面，返回false
		TbUser user = userService.getUserByToken(token);
		if (user == null) {
			response.sendRedirect(
					userService.SSO_DOMAIN_BASE_URL + userService.SSO_PAGE_LOGIN + "?redirect=" + request.getRequestURL());
			return false;
		}
		// 取得到用户信息，放行
		// 把用户信息放入request
		request.setAttribute("user", user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
