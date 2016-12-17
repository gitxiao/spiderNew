package com.cfrj.spider;

import com.cfrj.spider.model.SpiderParams;
import com.cfrj.spider.queue.UrlQueue;
import com.cfrj.spider.worker.SpiderWorker;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class SpiderThread implements Runnable{

	static SpiderThread st = new SpiderThread();
	static Thread spider;
	private static int workerNum = 0;
	private static boolean working = false;
	private static int temp = 0;
	public void run() {
		/**
		 * 一直维持线程最大数量,当有一个结束后,重新开启一个
		 */
		try {
			while (working) {
				if(!UrlQueue.isEmpty()){
					if(workerNum < SpiderParams.WORKER_NUM){
						System.out.println("增加一个线程, 线程数: workerNum = " + workerNum);
						new Thread(new SpiderWorker(0)).start();
						addWorker();
						Thread.sleep(500);
					}
				}
				System.out.println("主线程 temp,working = " + temp + "," + working);
				Thread.sleep(100);
			}
			if(!working){
				//TODO 应该结束所有正在运行的爬虫线程
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private static Thread spiderThread = new Thread(){
//		public void run(){
//			/**
//			 * 一直维持线程最大数量,当有一个结束后,重新开启一个
//			 */
//			try {
//				while (working) {
//					if(!UrlQueue.isEmpty()){
//						if(workerNum < SpiderParams.WORKER_NUM){
//							System.out.println("增加一个线程, 线程数: workerNum = " + workerNum);
//							new Thread(new SpiderWorker(0)).start();
//							addWorker();
//							Thread.sleep(500);
//						}
//					}
//					System.out.println("主线程");
//					Thread.sleep(100);
//				}
//				if(!working){
//					//TODO 应该结束所有正在运行的爬虫线程
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	};
	
	public static void startSpider(boolean start){
		if(start){
			if(working){
				System.out.println("爬虫正在运行中, 不需要重新开始");
//				startSpider(false);
//				try {
//					Thread.sleep(1000);
//					startSpider(true);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}else{
				System.out.println("爬虫停止状态,直接开始运行");
				working = start;
				// 初始化配置参数
				SpiderInitializer.initializeParams();
				
				// 初始化爬取队列
				int size1 = SpiderInitializer.initializeQueue();
				// 初始化关键字
				int size2 = SpiderInitializer.initializeKeyword();
				
				if(size1 >= 1 && size2 >= 1){
					spider = new Thread(st);
					new Thread(st).start();
				}
			}
		}else{
			System.out.println("爬虫停止运行=================================================================================================================");
			temp = 1;
			working = false;
			if(spider != null){
				spider.stop();
			}
			spider = null;
		}
		
	}
	
	/**
	 * 一个线程结束后,线程数量减1
	 */
	public static void wokerEnd(){
		workerNum --;
		System.out.println("线程数量: workerNum = " + workerNum);
		if(workerNum <= 0){
			try {
				System.out.println("线程已全部结束,等待5秒后结束总线程--------------------------------------------");
				Thread.sleep(5000);
				if(workerNum <= 0){
					System.out.println("5秒倒计时结束,结束总线程--------------------------------------------");
					startSpider(false);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 创建一个新的爬虫线程
	 */
	public static void addWorker(){
		workerNum ++;
	}
}
