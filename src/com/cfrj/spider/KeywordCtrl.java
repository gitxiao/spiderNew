package com.cfrj.spider;

import java.util.List;

import cn.muke.ssh.domain.T_Keyword;

public class KeywordCtrl {

	private static List<T_Keyword> keywordsList;

	public static List<T_Keyword> getKeywordsList() {
		return keywordsList;
	}

	public static void setKeywordsList(List<T_Keyword> keywordsList) {
		KeywordCtrl.keywordsList = keywordsList;
	}
	
}
