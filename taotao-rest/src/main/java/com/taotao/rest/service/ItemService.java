package com.taotao.rest.service;

import com.taotao.common.utils.TaotaoResult;
/**
 * 商品信息管理service
 * @author xushy
 *
 */
public interface ItemService {
	// 取商品基本信息
	TaotaoResult getItemBaseInfo(long itemId);
	// 取商品描述信息
	TaotaoResult getItemDesc(long itemId);
	// 取商品规格参数
	TaotaoResult getItemParam(long itemId);
}
