//package com.cfrj.spider.queue;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map.Entry;
//
//import cn.muke.ssh.domain.T_News;
//
//public class PageMap {
//	
//	private static HashMap<String, T_News> urlMap = new HashMap<String, T_News>();
//
//	public synchronized static void addElement(String url,int depth){
//		if(!isContains(url)){
//			T_News tNews = new T_News(url,depth);
//			urlMap.put(url, tNews);
////			System.out.println("UrlQueue addElement ����ȡurl: " + url);
////			System.out.println("addElement ��Ӻ�");
////			traversal();
//		}else{
////			System.out.println("����ȡ����ַ, ���ٽ������:" + url);
//		}
//	}
//	
//	public static Iterator getIterator(){
//		return urlMap.entrySet().iterator();
//	}
//	
//	public synchronized static void addLastElement(T_News tNews){
//		urlMap.put(tNews.getUrl(), tNews);
//	}
//	
//	public synchronized static boolean isEmpty(){
//		return urlMap.isEmpty();
//	}
//	
//	public static int size(){
//		return urlMap.size();
//	}
//	
//	public static boolean isContains(String url){
//		return urlMap.containsKey(url);
//	}
//	
//	@SuppressWarnings("unchecked")
//	public static void traversal(){
//		//��һ��:
//		Iterator iter = urlMap.entrySet().iterator();
//		System.out.println("urlMap ������ʼ===============================================>>>");
//		while (iter.hasNext()) {
//			Entry entry = (Entry) iter.next();
//			Object key = entry.getKey();
//			Object value = entry.getValue();
//			System.out.println("key,value = " + key + "," + value);
//		}
//		System.out.println("urlMap ��������===============================================<<<");
//		
//		//Ч�ʸ�,�Ժ�һ��Ҫʹ�ô��ַ�ʽ��
//		//�ڶ���:
////		Iterator iter = map.keySet().iterator();
////		while (iter.hasNext()) {
////			Object key = iter.next();
////			Object val = map.get(key);
////		}
//		//Ч�ʵ�,�Ժ�����ʹ�ã�
//		
//	}
//}
