package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.utils.TaotaoResult;

public interface ContentCategoryService {
	List<EUTreeNode> getCategoryList(long parentId);

	TaotaoResult insertCategory(long parentId, String name);

	TaotaoResult deleteCategory(long id);

	TaotaoResult updateCategory(long id, String name);
}
