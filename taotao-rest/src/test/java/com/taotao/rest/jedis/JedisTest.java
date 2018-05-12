package com.taotao.rest.jedis;

import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.rest.dao.JedisClient;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JedisClient.class);
	
	@Test
	public void testJedisSingle() {
		// 创建jedis对象
		Jedis jedis = new Jedis("192.168.94.102", 6379);
		// 调用jedis方法
		jedis.set("abc", "jedisTest");
		String string = jedis.get("abc");
		System.out.println(string);
		// 关闭jedis
		jedis.close();
	}

	/**
	 * 使用连接池
	 */
	@Test
	public void jedisPoolTest() {
		JedisPool jedisPool = new JedisPool();
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get("abc");
		System.out.println(string);
		jedis.close();
		jedisPool.close();
	}
	
	@Test
	public void jedisClusterTest() throws IOException {
		// 日志
		LOGGER.debug("调用rediscluster开始........");
		
		// 创建一个hashSet 节点的集合
		HashSet<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.94.102", 7001));
		nodes.add(new HostAndPort("192.168.94.102", 7002));
		nodes.add(new HostAndPort("192.168.94.102", 7003));
		nodes.add(new HostAndPort("192.168.94.102", 7004));
		nodes.add(new HostAndPort("192.168.94.102", 7005));
		nodes.add(new HostAndPort("192.168.94.102", 7006));
		LOGGER.info("创建jedis class对象");
		JedisCluster cluster = new JedisCluster(nodes);
		LOGGER.debug("设置key的值为1000");
		cluster.set("key","1000");
		LOGGER.debug("从redis中取key值");
		String string = cluster.get("key");
		System.out.println(string);
		LOGGER.debug("关闭链接......");
		cluster.close();
	}
	
	@Test
	public void springJedisSingleTest() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:sping/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = pool.getResource();
		String string = jedis.get("key");
		System.out.println(string);
		jedis.close();
		pool.close();
	}
	
	@Test
	public void springJedisClusterTest() throws IOException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster cluster =  (JedisCluster) applicationContext.getBean("redisClient");
		cluster.set("hello1", "helloredis");
		String string = cluster.get("hello1");
		System.out.println(string);
		cluster.close();
	}
}
