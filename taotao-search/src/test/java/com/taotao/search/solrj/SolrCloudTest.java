package com.taotao.search.solrj;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrCloudTest {

	@Test
	public void testAddDocument() throws Exception {
		// 创建一个和solr集群的链接;
		// 参数是zookeeper的地址列表，使用逗号分隔
		String zkHost = "192.168.94.103:2181,192.168.94.103:2182,192.168.94.103:2183";
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		// 指定默认collection
		solrServer.setDefaultCollection("collection2");
		// 创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		// 向文档中添加域
		document.addField("id", "test529");
		document.addField("item_title", "测试商品529");
		// 把文档添加到索引库
		solrServer.add(document);
		// 提交
		solrServer.commit();
	}

	public void testDeleteDocument() throws Exception {
		// 创建一个和solr集群的链接;
		// 参数是zookeeper的地址列表，使用逗号分隔
		String zkHost = "192.168.94.103:2181,192.168.94.103:2182,192.168.94.103:2183";
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		// 指定默认collection
		solrServer.setDefaultCollection("collection2");
		
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
}
