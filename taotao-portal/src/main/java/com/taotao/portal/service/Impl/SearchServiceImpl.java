package com.taotao.portal.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.portal.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Override
	public SearchResult search(String queryString, int page) {

		// 查询参数
		Map<String, String> params = new HashMap<>();
		params.put("q", queryString);
		params.put("page", page + "");
		try {
			// 调用服务
			String result = HttpClientUtil.doGet(SEARCH_BASE_URL, params);
			// 把字符串转换成java对象
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, SearchResult.class);
			if (taotaoResult.getStatus() == 200) {
				SearchResult searchResult = (SearchResult) taotaoResult.getData();
				return searchResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
