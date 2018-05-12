package com.taotao.service;

import com.taotao.common.pojo.EUResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;

public interface ItemService {

	TbItem getItemById(long itemId);

	EUResult getItemList(int page, int rows);
	
	TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception;
	
}
