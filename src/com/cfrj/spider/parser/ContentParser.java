package com.cfrj.spider.parser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.muke.ssh.domain.T_Keyword;
import cn.muke.ssh.domain.T_News;

import com.cfrj.spider.KeywordCtrl;
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
		String ids = containsTargetData(fetchedPage.getUrl(), doc);
		if(ids != null){
			// 解析并获取目标数据并持久化
			// TODO
			fetchedPage.gettNews().setKeyword(ids);
			Date date = getDate(doc.toString());
			if(date != null){
				fetchedPage.gettNews().setPubDate(date);
			}
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
	private List<T_Keyword> keywords;
	private String containsTargetData(String url, Document contentDoc){
		// 通过URL判断
		// TODO
		if(keywords == null){
			keywords = KeywordCtrl.getKeywordsList();
		}
		
		String contentString = contentDoc.toString();
		String word = null;
		T_Keyword keyword = null;
		StringBuffer ids = null;
		
		
		if(url.equals("http://lf.hebei.com.cn/yqx")){
			//System.out.println("查找word = " + word + "," + contentString);
		}
		if(contentString != null){
			int num = 0;
			for (int i = 0; i < keywords.size(); i++) {
				keyword = keywords.get(i);
				word = keyword.getKeyword();
				if(contentString.indexOf(word) >= 0){
					num ++;				//关键字出现次数计数
					if(ids == null){
						ids = new StringBuffer();
					}
					ids.append(keyword.getKeyword() + ",");
					System.out.println("找到关键字: " + keyword.getKeyword());
				}
			}
		}
		if(ids != null){
			String temp = ids.toString();
			System.out.println("关键字ids = " + temp);
			return temp;
		}else{
			return null;
		}
		
//		System.out.println(contentDoc.toString());
		// 通过content判断，比如需要抓取class为grid_view中的内容
//		if(contentDoc.getElementsByClass("grid_view") != null){
//			System.out.println(contentDoc.getElementsByClass("grid_view").toString());
//			return true;
//		}
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
	 * @return 获得网页发布时间
	 */  
	SimpleDateFormat sdf =   new SimpleDateFormat( "yy-MM-dd HH:mm" );
	public Date getDate(final String s){  
		
//			String eL = "[0-9]{2}-[0-9]{2}-[0-9]{2}";
//			Pattern p = Pattern.compile(eL);
//			Matcher m = p.matcher(content);
//			boolean dateFlag = m.matches();
//			if (!dateFlag) {
//				System.out.println("格式错误");
//			}
//			System.out.println("格式正确");
//			return null;
		String[] regexs = {
			"\\d{4}年(0?[1-9]|[1][012])月(0?[1-9]|[12][0-9]|[3][01])日\\s([0-1]?[0-9]|2[0-3]):([0-5][0-9])",
			"[0-9]{2}-[0-9]{2}-[0-9]{2}\\s([0-1]?[0-9]|2[0-3]):([0-5][0-9])",
			"\\d{4}年(0?[1-9]|[1][012])月(0?[1-9]|[12][0-9]|[3][01])日",
			"[0-9]{2}-[0-9]{2}-[0-9]{2}",
		};
		
//		String regex = "[0-9]{2}-[0-9]{2}-[0-9]{2}";  
		String dateStr = null;  
		Date date = null;
		Pattern pa = null;
		Matcher ma = null;
		for(int i = 0;i < regexs.length;i ++){
			pa = Pattern.compile(regexs[i], Pattern.CANON_EQ);  
			ma = pa.matcher(s);  
			if (ma.find()){  
				dateStr = ma.group();
				System.out.println("找到发布日期: date = " + dateStr);
				try {
					date = sdf.parse(dateStr);
					System.out.println("date.toString() = " + date.toString());
					break;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} 
		}
		return date;  
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
