package com.taotao.portal.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

/**
 * 购物车service
 * 
 * @author xushy
 *
 */
@Service
public class CartServiceImpl implements CartService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;

	@Override
	public TaotaoResult addCartItem(Long itemId, Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		// 判断购物车有没有此商品
		List<CartItem> itemList = getCartItemList(request);
		CartItem cartItem = null;
		for (CartItem cItem : itemList) {
			// 购物车存在此商品
			if (cItem.getId() == itemId) {
				cItem.setNum(cItem.getNum() + num);
				cartItem = cItem;
				break;
			}
		}

		// 购物车不存在此商品
		if (cartItem == null) {
			cartItem = new CartItem();
			// 根据商品id查询商品基本信息；调用服务
			String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
			// json 转成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItem.class);
			if (taotaoResult.getStatus() == 200) {
				TbItem item = (TbItem) taotaoResult.getData();
				cartItem.setId(item.getId());
				cartItem.setTitle(item.getTitle());
				cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
				cartItem.setNum(num);
				cartItem.setPrice(item.getPrice());
			}
			// 添加到购物车
			itemList.add(cartItem);
		}
		// 把购物车列表重新写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		return TaotaoResult.ok();
	}

	// 取购物车商品
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		// 从cookie中取商品
		String cartjson = CookieUtils.getCookieValue(request, "TT_CART", true);
		if (cartjson == null) {
			return new ArrayList<>();
		}
		try {
			List<CartItem> list = JsonUtils.jsonToList(cartjson, CartItem.class);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> itemList = getCartItemList(request);
		return itemList;
	}

	/**
	 * 删除购物车商品
	 */
	@Override
	public TaotaoResult deleteCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {
		// 从cookie中取出商品列表
		List<CartItem> itemList = getCartItemList(request);
		for (CartItem cartItem : itemList) {
			if (cartItem.getId() == itemId) {
				itemList.remove(cartItem);
				break;
			}
		}
		// 把购物车重新写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(itemList), true);
		return TaotaoResult.ok();
	}

}
