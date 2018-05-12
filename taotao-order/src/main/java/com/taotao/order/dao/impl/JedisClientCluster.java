package com.taotao.order.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.order.dao.JedisClient;

import redis.clients.jedis.JedisCluster;

public class JedisClientCluster implements JedisClient {

	@Autowired
	private JedisCluster cluster;

	@Override
	public String get(String key) {
		String string = cluster.get(key);
		return string;
	}

	@Override
	public String set(String key, String value) {
		String string = cluster.set(key, value);
		return string;
	}

	@Override
	public String Hget(String hkey, String key) {
		String string = cluster.hget(hkey, key);
		return string;
	}

	@Override
	public long Hset(String hkey, String key, String value) {
		Long result = cluster.hset(hkey, key, value);
		return result;
	}

	@Override
	public long incr(String key) {
		Long result = cluster.incr(key);
		return result;
	}

	@Override
	public long expire(String key, int seconds) {
		Long result = cluster.expire(key, seconds);
		return result;
	}

	@Override
	public long ttl(String key) {
		Long result = cluster.ttl(key);
		return result;
	}

	@Override
	public long del(String key) {
		return cluster.del(key);
	}

	@Override
	public long hdel(String hkey, String key) {
		return cluster.hdel(hkey, key);
	}

}
