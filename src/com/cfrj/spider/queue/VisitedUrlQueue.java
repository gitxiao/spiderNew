package com.cfrj.spider.queue;

import java.util.HashMap;
import java.util.Map;

import cn.muke.ssh.domain.T_Visited;
import cn.muke.ssh.service.VisitedService;


public class VisitedUrlQueue {
	// 已抓取url队列
//	private static LinkedList<String> visitedUrlQueue = new LinkedList<String>();
	private static Map<String,String> visitedPages = new HashMap<String,String>();
	private static Map<String,String> exceptionPages = new HashMap<String,String>();
//	private static T_Visited visited = new T_Visited();
	public static final VisitedService visitedService = new VisitedService();
	public synchronized static void addElement(String url,String desc){
		System.out.println("爬取网页: " + desc + "	 " + url + ",爬取数量:" + size());
		visitedPages.put(url, desc);			//优化速度时可删除
		T_Visited visited = new T_Visited(url);
//		DataStorage.saveVisited(visited);
		visitedService.save(visited);
	}
	
	/**
	 * 异常网页是否应该保存到数据库?
	 * @param url
	 * @param desc
	 */
	public synchronized static void addElementWithException(String url,String desc){
//		System.out.println("异常网页: " + desc + "	 " + tNews.getUrl());
		exceptionPages.put(url, desc);
	}
	
	public synchronized static boolean isEmpty(){
		return visitedPages.isEmpty();
	}
	
	public static int size(){
		return visitedPages.size();
	}
	
	public static boolean isContains(String url){
		//TODO 需要查询数据库
		T_Visited tVisited = visitedService.findVisitedByUrl(url);
		boolean visited = tVisited != null; 
		if(visited){
			System.out.println("已爬 url = " + url);
		}
		return visited;
//		return visited || exceptionPages.containsKey(url);
//		return visitedPages.containsKey(url) || exceptionPages.containsKey(url);
	}
}
