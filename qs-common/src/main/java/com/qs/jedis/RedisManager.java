 package com.qs.jedis;


import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class RedisManager {
	
	private String host = "127.0.0.1";
	
	private int port = 6379;
	
	// 0 - never expire
	private int expire = 0;
	
	//timeout for jedis try to connect to redis server, not expire time! In milliseconds
	private int timeout = 0;
	
	private String password = "";
	
	
	private int database=2;
	
	private static JedisPool jedisPool = null;
	
	public RedisManager(){
		
	}
	
	/**
	 * 初始化方法
	 */
	public void init(){
		if(jedisPool == null){
			if(password != null && !"".equals(password)){
				jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password,database);
			}else if(timeout != 0){
				jedisPool = new JedisPool(new JedisPoolConfig(), host, port,timeout,null,database);
			}else{
				jedisPool = new JedisPool(new JedisPoolConfig(), host, port,Protocol.DEFAULT_TIMEOUT, null,
						database, null);
			}
			
		}
	}
	
	/**
	 * get value from redis
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key){
		byte[] value = null;
		Jedis jedis = jedisPool.getResource();
		try{
			value = jedis.get(key);
		}finally{
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * set 
	 * @param key
	 * @param value
	 * @return
	 */
	public byte[] set(byte[] key,byte[] value){
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.set(key,value);
			if(this.expire != 0){
				jedis.expire(key, this.expire);
		 	}
		}finally{
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * set 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public byte[] set(byte[] key,byte[] value,int expire){
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.set(key,value);
			if(expire != 0){
				jedis.expire(key, expire);
		 	}
		}finally{
			jedisPool.returnResource(jedis);
		}
		return value;
	}
	
	/**
	 * del
	 * @param key
	 */
	public void del(byte[] key){
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.del(key);
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * flush
	 */
	public void flushDB(){
		Jedis jedis = jedisPool.getResource();
		try{
			jedis.flushDB();
		}finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	/**
	 * size
	 */
	public Long dbSize(){
		Long dbSize = 0L;
		Jedis jedis = jedisPool.getResource();
		try{
			dbSize = jedis.dbSize();
		}finally{
			jedisPool.returnResource(jedis);
		}
		return dbSize;
	}

	/**
	 * keys
	 * @param regex
	 * @return
	 */
	public Set<byte[]> keys(String pattern){
		Set<byte[]> keys = null;
		Jedis jedis = jedisPool.getResource();
		try{
			keys = jedis.keys(pattern.getBytes());
		}finally{
			jedisPool.returnResource(jedis);
		}
		return keys;
	}
	
	/**
	 * 向集合中添加缓存信息
	 * @param setName
	 * @param elementValue
	 */
	public Long sadd(String setName,String elementValue){
		Long addCount = 0l;
		Jedis jedis = jedisPool.getResource();
		try{
			addCount = jedis.sadd(setName, elementValue);
		}finally{
			jedisPool.returnResource(jedis);
		}
		return addCount;
	}
	
	/**
	 * 向集合中移除缓存信息
	 * @param setName
	 * @param elementValue
	 */
	public Long srem(String setName,String elementValue){
		Long remCount = 0l;
		Jedis jedis = jedisPool.getResource();
		try{
			remCount = jedis.srem(setName, elementValue);
		}finally{
			jedisPool.returnResource(jedis);
		}
		return remCount;
	}
	
	/**
	 * 获取集合中的所有元素
	 * @param setName
	 * @return
	 */
	public Set<String> smembers(String setName){
		Set<String> elements = null;
		Jedis jedis = jedisPool.getResource();
		try{
			elements = jedis.smembers(setName);
		}finally{
			jedisPool.returnResource(jedis);
		}
		return elements;
	}
	
	/****
	 * 设置键值对  包含过期时间 单位秒
	 * @return
	 */
	public String set(final String key, final String value, final long time) {
		String result=null;
		Jedis jedis = jedisPool.getResource();
		try{
			result=jedis.set(key, value, "NX", "EX", time);
		}finally{
			jedisPool.returnResource(jedis);
		}
	   return result;
	}
	/***
	 * 根据Key得到Value  ,字符串类型
	 * @param key
	 * @return
	 */
	 public String get(final String key) {
		 String result=null;
			Jedis jedis = jedisPool.getResource();
			try{
				result=jedis.get(key);
			}finally{
				jedisPool.returnResource(jedis);
			}
		   return result;
	 }
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public static JedisPool getJedisPool() {
		return jedisPool;
	}

	public static void setJedisPool(JedisPool jedisPool) {
		RedisManager.jedisPool = jedisPool;
	}
	
	
	
}
