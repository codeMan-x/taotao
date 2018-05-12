package com.taotao.service;

import com.taotao.common.pojo.EUResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	EUResult getContentList(Integer page, Integer rows, long categoryId);
	
	// 增加内容
	TaotaoResult insertContent(TbContent content);
}
