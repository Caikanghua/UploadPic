package com.readbook.utils;

import java.util.Random;

public class IDUtils {
	/**
	 * ͼƬ������
	 */
	public static String genImageName() {
		//ȡ��ǰʱ��ĳ�����ֵ��������
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//������λ�����
		Random random = new Random();
		int end3 = random.nextInt(999);
		//���������λǰ�油0
		String str = millis + String.format("%03d", end3);
		
		return str;
	}
	
	/**
	 * id����
	 */
	public static String getId() {
		//ȡ��ǰʱ��ĳ�����ֵ��������
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//������λ�����
		Random random = new Random();
		int end2 = random.nextInt(99);
		//���������λǰ�油0
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return String.valueOf(id);
	}
	/**
	 * id����
	 */
	public static int getIntId() {
		//ȡ��ǰʱ��ĳ�����ֵ��������
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//������λ�����
		Random random = new Random();
		int end2 = random.nextInt(99);
		//���������λǰ�油0
		String str = millis + String.format("%02d", end2);
		int id = new Integer(str);
		return id;
	}
	
	public static void main(String[] args) {
		for(int i=0;i< 100;i++)
		System.out.println(getId());
	}
}
