package com.taotao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;
import com.taotao.pojo.TbItemParamItem;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	@Override
	public TbItem getItemById(long itemId) {
		// TbItem item = itemMapper.selectByPrimaryKey(itemId);

		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);

		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public EUResult getItemList(int page, int rows) {

		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);

		EUResult result = new EUResult();
		result.setRows(list);

		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		result.setTotal(pageInfo.getTotal());

		return result;
	}

	/**
	 * 添加商品
	 * 
	 * @throws Exception
	 */
	@Override
	public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception {
		// item信息补全
		Long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);// 状态
		item.setCreated(new Date());
		item.setUpdated(new Date());
		// 插入到数据库
		itemMapper.insert(item);
		// 添加商品描述
		TaotaoResult result1 = insertItemDesc(itemId, desc);
		// 添加规格参数
		TaotaoResult result2 = insertItemParam(itemId, itemParam);
		if (result1.getStatus() != 200) {
			throw new Exception();
		}
		if (result2.getStatus() != 200) {
			throw new Exception();
		}
		// 添加商品到索引库
		Map<String, String> params = new HashMap<>();
		params.put("itemId", itemId + "");
		System.out.println(SEARCH_BASE_URL);
		HttpClientUtil.doGet(SEARCH_BASE_URL+"/importItem", params);
		return TaotaoResult.ok();
	}

	private TaotaoResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());

		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}

	private TaotaoResult insertItemParam(Long itemId, String itemParam) {
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());

		itemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
	}

}
