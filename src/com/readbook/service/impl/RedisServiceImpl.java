package com.readbook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.readbook.service.RedisService;
import com.readbook.utils.JedisClient;
@Service
public class RedisServiceImpl implements RedisService{
	@Autowired
	JedisClient jedisClient;
	@Override
	public void addPicUrlMapper(String key, String url) {
		jedisClient.set(key, url);
	}
	@Override
	public String getPicUrl(String key) {
		return jedisClient.get(key);
	}

}
