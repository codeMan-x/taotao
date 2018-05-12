package com.taotao.portal.service;

import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.Item;
import com.taotao.portal.pojo.ItemInfo;

/**
 * 调用服务
 * @author xushy
 *
 */
public interface ItemService {
	// 加载商品基本信息
	ItemInfo getItemById(Long itemId);
	// 加载商品描述
	String getItemDescById(Long itemId);
	// 加载规格参数
	String getItemParamById(Long itemId);
}
