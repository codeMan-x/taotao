package com.taotao.search.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {
	@Test
	public void addDocument() throws SolrServerException, IOException {
		// 创建链接链接solr服务
		SolrServer solrServer = new HttpSolrServer("http://192.168.94.103:8080/solr/");
		// 创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "测试商品001");
		document.addField("item_price", 1233);
		// 把文档对象写入索引库
		solrServer.add(document);
		// 提交
		solrServer.commit();
	}

	public void deleteDocument() throws SolrServerException, IOException {
		// 创建链接链接solr服务
		SolrServer solrServer = new HttpSolrServer("http://192.168.94.103:8080/solr/");
		solrServer.deleteById("test001");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
	
	@Test
	public void queryDocument() throws SolrServerException {
		SolrServer solrServer = new HttpSolrServer("http://192.168.94.103:8080/solr/");
		// 查询，创建一个查询对象
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setStart(20);
		solrQuery.setRows(50);
		QueryResponse response = solrServer.query(solrQuery);
		SolrDocumentList results = response.getResults();
		System.out.println("共查询到记录：" + results.getNumFound() + "条");
		for (SolrDocument document : results) {
			System.out.println(document.get("id"));
			System.out.println(document.get("item_title"));
			System.out.println(document.get("item_image"));
			System.out.println(document.get("item_price"));
		}
	}
}
