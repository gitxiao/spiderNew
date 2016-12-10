package com.cfrj.spider;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.cfrj.spider.model.SpiderParams;
import com.cfrj.spider.queue.UrlQueue;
import com.cfrj.spider.worker.SpiderWorker;

public class SpiderStarter {

	private static int workerNum = 0;
	private static boolean working = false;
	public static void main(String[] args){
		startSpider(true);
	}
	
	private static Thread spiderThread = new Thread(){
		public void run(){
			/**
			 * 一直维持线程最大数量,当有一个结束后,重新开启一个
			 */
			try {
				while (working) {
					if(!UrlQueue.isEmpty()){
						if(workerNum < SpiderParams.WORKER_NUM){
							System.out.println("增加一个线程, 线程数: workerNum = " + workerNum);
							new Thread(new SpiderWorker(0)).start();
							workerNum ++;
							Thread.sleep(500);
						}
					}
				}
				if(!working){
					//TODO 应该结束所有正在运行的爬虫线程
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	public static void startSpider(boolean start){
		if(start){
			if(working){
				System.out.println("爬虫正在运行,要先结束,才能重新开始");
				startSpider(false);
				try {
					Thread.sleep(1000);
					startSpider(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("爬虫停止状态,直接开始运行");
				working = start;
				// 初始化配置参数
				initializeParams();
				
				// 初始化爬取队列
				initializeQueue();
				
				spiderThread.start();
			}
		}else{
			System.out.println("爬虫停止运行");
			working = false;
			spiderThread.stop();
		}
		
	}
	
	/**
	 * 一个线程结束后,线程数量减1
	 */
	public static void wokerEnd(){
		workerNum --;
		if(workerNum <= 0){
			startSpider(false);
		}
	}
	
	
	/**
	 * 初始化配置文件参数
	 * 
	 * conf/spider.properties只能在main线程中才能读取到?
	 */
	private static void initializeParams(){
		SpiderParams.WORKER_NUM = 20;
		SpiderParams.DEYLAY_TIME = 2000;
		SpiderParams.MAX_DEPTH = 2;
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
	private static void initializeQueue(){
		
		UrlQueue.addElement("http://www.mohurd.gov.cn",0);
		UrlQueue.addElement("http://www.hebei.com.cn",0);
		UrlQueue.addElement("http://jizhou.hebei.com.cn",0);
		UrlQueue.addElement("http://www.hengshui.gov.cn",0);
		UrlQueue.addElement("http://www.54hs.com",0);
		UrlQueue.addElement("http://www.hsnsbd.gov.cn",0);
		UrlQueue.addElement("http://www.mwr.gov.cn",0);
		UrlQueue.addElement("http://www.hebwater.gov.cn",0);
		UrlQueue.addElement("http://www.xjslt.gov.cn",0);
		UrlQueue.addElement("http://www.xzwater.gov.cn",0);
		UrlQueue.addElement("http://www.hebei.com.cn",0);
		UrlQueue.addElement("http://www.qhsl.gov.cn",0);
		
	}
}
