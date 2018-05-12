package com.taotao.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {
	@Test
	public void doGet() throws ClientProtocolException, IOException {
		// 创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个get对象
		HttpGet get = new HttpGet("http://www.baidu.com");
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		// 取响应结果
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity,"utf-8");
		System.out.println(string);
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		// 关闭httpclient
		response.close();
		httpClient.close();
	}
}
