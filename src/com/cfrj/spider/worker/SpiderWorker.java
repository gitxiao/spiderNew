package com.cfrj.spider.worker;

import java.util.logging.Logger;

import cn.muke.ssh.dao.T_NewsDao;
import cn.muke.ssh.domain.T_News;
import cn.muke.ssh.service.NewsService;

import com.cfrj.spider.SpiderStarter;
import com.cfrj.spider.fetcher.PageFetcher;
import com.cfrj.spider.handler.ContentHandler;
import com.cfrj.spider.model.FetchedPage;
import com.cfrj.spider.model.SpiderParams;
import com.cfrj.spider.parser.ContentParser;
import com.cfrj.spider.queue.UrlQueue;
import com.cfrj.spider.queue.VisitedUrlQueue;


public class SpiderWorker implements Runnable{
	private static final NewsService newsService = new NewsService();
	private static final Logger Log = Logger.getLogger(SpiderWorker.class.getName());
	private PageFetcher fetcher;
	private ContentHandler handler;
	private ContentParser parser;
	private int threadIndex;
	
	public SpiderWorker(int threadIndex){
		this.threadIndex = threadIndex;
		this.fetcher = new PageFetcher();
		this.handler = new ContentHandler();
		this.parser = new ContentParser();
	}
	
	@SuppressWarnings("unchecked")
	public void run() {
		// 登录
		
		
		// 当待抓取URL队列不为空时，执行爬取任务
		// 注： 当队列内容为空时，也不代表爬取任务已经结束了
		//     因为有可能是UrlQueue暂时空，其他worker线程还没有将新的URL放入队列
		//	        所以，这里可以做个等待时间，再进行抓取（二次机会）
		
		try {
			while(!UrlQueue.isEmpty()){
				// 从待抓取队列中拿URL
				T_News tNews = UrlQueue.outElement();
				
				//拿到的url判断是否已经存在于已抓取队列,如果存在,则跳过. 深度为0的url不能跳过,深度为0的是目标网站
				if(tNews.getDepth() != 0 && VisitedUrlQueue.isContains(tNews.getUrl())){
					continue;
				}
//			System.out.println("爬取url------------------------------------------------------:" + url);
				
				// 抓取URL指定的页面，并返回状态码和页面内容构成的FetchedPage对象
				FetchedPage fetchedPage = fetcher.getContentFromUrl(tNews);
				
				// 检查爬取页面的合法性，爬虫是否被禁止
				if(!handler.check(fetchedPage)){
					// 切换IP等操作
					// TODO
					
					Log.info("爬虫被禁止: Spider-" + threadIndex + ": switch IP to ");
					continue;
				}
				
				// 解析页面，获取目标数据
				T_News targetData = parser.parse(fetchedPage);
				
				// 存储目标数据到数据存储（如DB）、存储已爬取的Url到VisitedUrlQueue
				if(targetData != null){
//					DataStorage.saveNews(targetData);
					newsService.save(targetData);
				}
				
				// delay
				try {
					if(UrlQueue.isEmpty()){
						Thread.sleep(SpiderParams.DEYLAY_TIME);			//等待新抓取的url进入队列
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			SpiderStarter.wokerEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		fetcher.close();
		Log.info("Spider-" + threadIndex + ": stop...");
	}

		
}
