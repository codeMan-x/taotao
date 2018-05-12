package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CataResult;
import com.taotao.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${INDEX_CATEGORY_REDIS_KEY}")
	private String INDEX_CATEGORY_REDIS_KEY;

	@Override
	public CataResult getItemCatList() {
		// 添加缓存查询
		try {
			String result = jedisClient.get(INDEX_CATEGORY_REDIS_KEY);
			if (!StringUtils.isBlank(result)) {
				CataResult cateResult = JsonUtils.jsonToPojo(result, CataResult.class);
				return cateResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		CataResult cataResult = new CataResult();
		cataResult.setData(getCatList(0));
		// 存入缓存
		try {
			String cacheString = JsonUtils.objectToJson(cataResult);
			jedisClient.set(INDEX_CATEGORY_REDIS_KEY, cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cataResult;
	}

	private List<?> getCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List resultList = new ArrayList<>();
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			// 判断是否为父节点
			if (tbItemCat.getIsParent()) {
				if (count >= 15) {
					break;
				}
				count++;
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName(
							"<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/" + tbItemCat.getId() + ".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				resultList.add(catNode);
				// 如果是叶子节点
			} else {
				resultList.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
			}
		}
		return resultList;
	}

}
