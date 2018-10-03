package com.readbook.service;

public interface RedisService {
	void addPicUrlMapper(String key, String url);
	
	String getPicUrl(String key);
}
