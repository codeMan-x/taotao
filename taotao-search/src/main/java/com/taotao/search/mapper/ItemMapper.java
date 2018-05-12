package com.taotao.search.mapper;

import java.util.List;

import com.taotao.search.pojo.Item;

public interface ItemMapper {
	// 查找所有商品
	List<Item> getItemList();
	// 根据id查找商品
	Item getItemById(long itemId);
}
