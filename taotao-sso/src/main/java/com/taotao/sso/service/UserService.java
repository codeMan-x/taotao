package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {
	// 校验数据
	TaotaoResult checkData(String content, Integer type);

	// 用户注册
	TaotaoResult createUser(TbUser user);

	// 用户登录
	TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

	// 通过token查询用户信息
	TaotaoResult getUserByToken(String token);
}
