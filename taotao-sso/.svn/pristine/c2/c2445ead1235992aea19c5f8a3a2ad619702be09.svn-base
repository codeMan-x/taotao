package com.taotao.sso.dao;

public interface JedisClient {
	String get(String key);

	String set(String key, String value);

	String Hget(String hkey, String key);

	long Hset(String hkey, String key, String value);

	long incr(String key);

	long expire(String key, int seconds);

	long ttl(String key);
	
	long del(String key);
	
	long hdel(String hkey, String key);
}
