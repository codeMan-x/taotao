package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {

		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);

		List<EUTreeNode> result = new ArrayList<>();
		for (TbContentCategory category : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(category.getId());
			node.setText(category.getName());
			node.setState(category.getIsParent() ? "closed" : "open");
			result.add(node);
		}

		return result;
	}

	@Override
	public TaotaoResult insertCategory(long parentId, String name) {

		TbContentCategory category = new TbContentCategory();
		category.setName(name);
		category.setParentId(parentId);
		category.setIsParent(false);
		category.setStatus(1);
		category.setSortOrder(1);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		contentCategoryMapper.insert(category);
		// 更新父节点
		TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		// 返回结果
		return TaotaoResult.ok(category);
	}

	@Override
	public TaotaoResult deleteCategory(long id) {

		// 递归删除
		TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
		deleteCats(category);

		// 如果父节点没有子节点，修改parent
		// 得到parentID
		long parentId = category.getParentId();
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
			parent.setIsParent(false);
			contentCategoryMapper.updateByPrimaryKey(parent);
		}

		return TaotaoResult.ok();
	}

	private void deleteCats(TbContentCategory category) {
		if (category.getIsParent()) {
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(category.getId());
			List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
			// 遍历节点的子节点，并删除
			for (TbContentCategory child : list) {
				deleteCats(child);
			}
			// 删除完子节点后，更新为叶子节点
			contentCategoryMapper.deleteByPrimaryKey(category.getId());
		} else {
			contentCategoryMapper.deleteByPrimaryKey(category.getId());
		}
	}

	@Override
	public TaotaoResult updateCategory(long id, String name) {

		TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
		category.setName(name);
		contentCategoryMapper.updateByPrimaryKey(category);

		return TaotaoResult.ok();
	}

}
