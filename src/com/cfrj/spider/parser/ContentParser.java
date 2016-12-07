package com.cfrj.spider.parser;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.muke.ssh.domain.T_News;

import com.cfrj.spider.model.FetchedPage;
import com.cfrj.spider.queue.UrlQueue;
import com.cfrj.spider.queue.VisitedUrlQueue;


public class ContentParser {
	public T_News parse(FetchedPage fetchedPage){
		Document doc = Jsoup.parse(fetchedPage.getContent(),"http://" + fetchedPage.getUrlHeader());
		
		String title = getTitle(fetchedPage.getContent());
		if(title == null || title.equals("")){
			Elements elemTitle = doc.getElementsByTag("title");
			title = elemTitle.html();
		}
		if(title != null){
			title = this.outTag(title);
			fetchedPage.gettNews().setTitle(title);
		}
		
//		System.out.println("fetchedPage.getContent() :" + fetchedPage.getContent());
//		System.out.println("fetchedPage.getUrl() :" + fetchedPage.getUrl());
//		System.out.println("fetchedPage.getUrl() :" + fetchedPage.getUrl());
//		System.out.println("标题:" + title + ",elemTitle.get(0) = " + elemTitle.get(0));
		
		//TODO 深度为多少的时候不需要存储? 待定
		//深度为0的url不需要保存进数据库, 因为肯定每次都需要重新爬取
		if(fetchedPage.gettNews().getDepth() > 0){
			VisitedUrlQueue.addElement(fetchedPage.gettNews().getUrl(),title);			
		}
		
//		Element elemContent = doc.getElementById("content");
//		String content = elemContent.html();
		
		parseUrls(fetchedPage);
		
		// 如果当前页面包含目标数据
		if(containsTargetData(fetchedPage.getUrl(), doc)){
			// 解析并获取目标数据并持久化
			// TODO
			return fetchedPage.gettNews();
		}else{
			return null;
		}
	}
	
	public void parseUrls(FetchedPage fetchedPage){
		// 根据当前页面和URL获取下一步爬取的URLs
		// TODO
		String newUrl = null;
		String urlDesc = null;
		String aLink = null;
		Pattern patternA = Pattern.compile("<a[\\s\\S]+?</a>");
		Matcher matcherA = patternA.matcher(fetchedPage.getContent());
		while(matcherA.find()){
			aLink = matcherA.group();
			newUrl = getUrlFromALink(fetchedPage,aLink);
			urlDesc = getDescOfALink(aLink);
//			System.out.println("aLink = " + aLink);
//			System.out.println("newUrl = " + newUrl);
//			System.out.println("urlDesc = " + urlDesc);
//			System.out.println(urlDesc + ":	" + newUrl);
			
			//http://www.mohurd.gov.cn" style=
//			if("http://www.mohurd.gov.cn\" style=".equals(newUrl)){
//				System.out.println("fetchedPage.gettNews().getUrl() = " + fetchedPage.gettNews().getUrl());
//				System.out.println("fetchedPage.getContent() = " + fetchedPage.getContent());
//			}
			if(newUrl != null){								
				UrlQueue.addElement(newUrl,fetchedPage.gettNews().getDepth() + 1);
			}
		}
	}
	
	Random random = new Random();
	private boolean containsTargetData(String url, Document contentDoc){
		// 通过URL判断
		// TODO
		
//		System.out.println(contentDoc.toString());
		// 通过content判断，比如需要抓取class为grid_view中的内容
//		if(contentDoc.getElementsByClass("grid_view") != null){
//			System.out.println(contentDoc.getElementsByClass("grid_view").toString());
//			return true;
//		}
		
		float ran = random.nextFloat();
		return ran < 0.02;
	}
	
	
	/**
	 * 从超链接中拿出新的url地址
	 * @param aLink
	 * @return
	 */
	private String getUrlFromALink(FetchedPage fetchedPage,String aLink){
		String newUrl = null;
		Pattern patternHref = Pattern.compile("href=\"(.*?)\"");
		Matcher matcherHref = patternHref.matcher(aLink);
		String href = null;
		if(matcherHref.find()){
			href = matcherHref.group(1).trim();
			if(href.length() < 2 || href.startsWith("javascript:")){
				return null;
			}
			if(href.startsWith("http:") || href.startsWith("https:")){
				newUrl = href;
			}else{
				newUrl = fetchedPage.getUrlHeader() + href;
//				System.out.println("fetchedPage.getUrl(),href,newUrl = " + fetchedPage.getUrl() + ",  " + href + ",  " + newUrl);
				
			}
		}
		
		if(newUrl != null){
			newUrl = newUrl.trim();
//			if(newUrl.contains("http://pmm.people.com.cn/main/s?user=people|2016people|D_icon_left&db=people&border=0&local=yes")){
//				System.out.println("fetchedPage.getUrl() = " + fetchedPage.gettNews().getUrl());
//				System.out.println("aLink = " + aLink);
//				System.out.println("href = " + href);
//				System.out.println("newUrl = " + newUrl);
//			}
//			if(!newUrl.startsWith("http") || newUrl.substring(1).contains("http")){
//				System.out.println("aLink = " + aLink);
//				System.out.println("href = " + href);
//				System.out.println("newUrl.startsWith(\"http\") = " + newUrl.startsWith("http"));
//				System.out.println("newUrl.substring(1) = " + newUrl.substring(1));
//				System.out.println("newUrl.substring(1).contains(\"http\") = " + newUrl.substring(1).contains("http"));
//				System.out.println("fetchedPage.getUrl() = " + fetchedPage.getUrl());
//				System.out.println("newUrl = " + newUrl);
//			}
		}
		return newUrl;
	}
	
	/**
	 * 从超链接中拿出链接描述
	 * @param aLink
	 * @return
	 */
	private String getDescOfALink(String aLink){
		String desc = getSubStringFrom(aLink,">","<");
		if(desc.contains("<img") || desc.contains("<Img") || desc.contains("src=")){
			desc = "图片链接";
		}
		return desc;
	}
	
	/**
	 * 获取字符串中某两个指定子串之间的子串
	 */
	private String getSubStringFrom(String father,String child0,String child1){
		String child = null;
		String temp = null;
		int index0 = father.indexOf(child0);
		int index1 = father.lastIndexOf(child1);
		if(index0 >= 0 && index1 > index0){
			temp = father.substring(index0 + 1,index1);
			child = getSubStringFrom(temp,child0,child1);
		}else{
			child = father;
		}
		return child;
	}
	
	/** 
	  *  
	  * @param s 
	  * @return 获得网页标题 
	  */  
	 public String getTitle(final String s){  
		 String regex;  
		 String title = null;  
		 regex = "<title>.*?</title>";  
		 final Pattern pa = Pattern.compile(regex, Pattern.CANON_EQ);  
		 final Matcher ma = pa.matcher(s);  
		 if (ma.find()){  
			 title = ma.group();
		 } 
		 return title;  
	 }  
	 
	 /** 
	  * @param s 
	  * @return 去掉标签标记 <>
	  */  
	 public String outTag(final String s){  
		 return s.replaceAll("<.*?>", "");  
	 }  
}
