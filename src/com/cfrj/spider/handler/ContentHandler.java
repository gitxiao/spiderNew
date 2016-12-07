package com.cfrj.spider.handler;

import com.cfrj.spider.model.FetchedPage;
import com.cfrj.spider.queue.UrlQueue;


public class ContentHandler {
	public boolean check(FetchedPage fetchedPage){
		// ���ץȡ��ҳ���������ȡ���ݣ��򽫵�ǰURL�������ȡ���У��Ա�������ȡ
		if(isAntiScratch(fetchedPage)){
			UrlQueue.addLastElement(fetchedPage.gettNews());
			return false;
		}
		
		return true;
	}
	
	private boolean isStatusValid(int statusCode){
		if(statusCode >= 200 && statusCode < 400){
			return true;
		}
		return false;
	}
	
	private boolean isAntiScratch(FetchedPage fetchedPage){
		// 403 forbidden
		if((!isStatusValid(fetchedPage.getStatusCode())) && fetchedPage.getStatusCode() == 403){
			return true;
		}
		
		// ҳ�����ݰ����ķ���ȡ����
		if(fetchedPage.getContent().contains("<div>��ֹ����</div>")){
			return true;
		}
		
		//TODO, ����Ӧ�÷��ط������ԵĴ���, �����ٴ���ȡʱ��Ȼ�ᱻ����
		return false;
	}
}
