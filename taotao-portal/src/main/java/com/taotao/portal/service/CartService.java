package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

/**
 * 购物车
 * 
 * @author xushy
 *
 */
public interface CartService {
	TaotaoResult addCartItem(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);

	TaotaoResult deleteCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response);
}
