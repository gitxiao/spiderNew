package com.cfrj.spider;

import java.util.Iterator;
import java.util.List;

import cn.muke.ssh.domain.T_Website;
import cn.muke.ssh.service.KeywordService;
import cn.muke.ssh.service.WebsiteService;

import com.cfrj.spider.model.SpiderParams;
import com.cfrj.spider.queue.UrlQueue;

public class SpiderInitializer {
	/**
	 * 初始化配置文件参数
	 * 
	 * conf/spider.properties只能在main线程中才能读取到?
	 */
	public static void initializeParams(){
		SpiderParams.WORKER_NUM = 20;
		SpiderParams.DEYLAY_TIME = 2000;
		SpiderParams.MAX_DEPTH = 1;
//		InputStream in = null;
//		try {
//			in = new BufferedInputStream(new FileInputStream("conf/spider.properties"));
//			Properties properties = new Properties();
//			properties.load(in);
			
			// 从配置文件中读取参数
//			SpiderParams.WORKER_NUM = Integer.parseInt(properties.getProperty("spider.threadNum"));
//			SpiderParams.DEYLAY_TIME = Integer.parseInt(properties.getProperty("spider.fetchDelay"));
//			SpiderParams.MAX_DEPTH = Integer.parseInt(properties.getProperty("spider.pageMaxDepth"));
//			in.close();
//		} 
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * 准备初始的爬取链接
	 * 896836015
	 */
	@SuppressWarnings("unchecked")
	public static void initializeQueue(){
		
//		UrlQueue.addElement("http://lf.hebei.com.cn/yqx/",0);
//		UrlQueue.addElement("http://jizhou.hebei.com.cn",0);
//		UrlQueue.addElement("http://www.hengshui.gov.cn",0);
		
		WebsiteService service = new WebsiteService();
		List<T_Website> list = (List<T_Website>)service.findAll();
		if(list == null){
			return;
		}
		int size = list.size();
		T_Website tw = null;
		for (int i = 0; i < size; i++) {
			tw = list.get(i);
			System.out.println("添加网址: " + tw.getName() + "," + tw.getUrl());
			UrlQueue.addElement(tw.getUrl(),0);
		}
	}
	
	/**
	 * 准备初始的爬取链接
	 * 896836015
	 */
	@SuppressWarnings("unchecked")
	public static void initializeKeyword(){
		
		KeywordService service = new KeywordService();
		List list = (List)service.findAll();
		System.out.println("拿到关键字列表");
		KeywordCtrl.setKeywordsList(list);
	}
}
