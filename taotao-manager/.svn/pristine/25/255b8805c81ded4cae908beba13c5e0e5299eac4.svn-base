package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemParamService;

/**
 * 商品规格参数模板
 * 
 * @author xushy
 *
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);

		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);

		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {

		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

	/**
	 * 规格参数列表
	 */
	@Override
	public EUResult getItemParamList(int page, int rows) {

		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(page, rows);

		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);

		EUResult result = new EUResult();
		result.setRows(list);

		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
		result.setTotal(pageInfo.getTotal());

		return result;
	}

}
