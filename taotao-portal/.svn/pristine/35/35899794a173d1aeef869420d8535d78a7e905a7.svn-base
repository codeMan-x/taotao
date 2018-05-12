package com.taotao.portal.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContentService;

/**
 * 调用服务层
 * 
 * @author xushy
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;

	@Override
	public String getContentList() {
		// 调用服务层服务
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		try {
			// 把字符串转换成taotaoresult
			TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);
			// 取内容列表
			List<TbContent> list = (List<TbContent>) taotaoResult.getData();
			// 创建一个页面要求的json字符串
			List<Map> resultList = new ArrayList<Map>();
			for (TbContent content : list) {
				Map map = new HashMap<>();
				map.put("src", content.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", content.getPic2());
				map.put("heightB", 240);
				map.put("widthB", 550);
				map.put("href", content.getUrl());
				map.put("alt", content.getSubTitle());
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
