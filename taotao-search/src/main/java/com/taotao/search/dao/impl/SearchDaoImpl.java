package com.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.dao.SearchDao;

/**
 * 商品搜索dao
 * 
 * @author xushy
 *
 */
@Repository
public class SearchDaoImpl implements SearchDao {

	@Autowired
	SolrServer solrServer;

	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		// 返回值对象
		SearchResult searchResult = new SearchResult();
		// 根据查询条件查询索引库
		QueryResponse queryResponse = solrServer.query(query);
		// 取查询结果
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		// 取查询结果总数量
		searchResult.setTotalCount(solrDocumentList.getNumFound());
		// 取商品列表
		List<Item> itemList = new ArrayList<>();
		// 取高亮信息
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		for (SolrDocument document : solrDocumentList) {
			Item item = new Item();
			item.setId((String) document.get("id"));
			// 取高亮显示的结果
			List<String> list = highlighting.get(document.get("id")).get("item_title");
			String title = "";
			if (list != null && list.size() > 0) {
				title = list.get(0);
			} else {
				title = (String) document.get("item_title");
			}
			item.setTitle(title);
			item.setImage((String) document.get("item_image"));
			item.setPrice((long) document.get("item_price"));
			item.setSell_point((String) document.get("item_sell_point"));
			item.setCategory_name((String) document.get("item_category_name"));
			itemList.add(item);
		}
		searchResult.setItemList(itemList);
		// 设置总数量
		searchResult.setTotalCount(solrDocumentList.getNumFound());
		return searchResult;
	}

}
