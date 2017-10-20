package com.zrgj.utils;

/**
 * 
 * @author caiyikai yuanhao
 *
 */


public class StringUtils {
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */

	public static boolean isEmpty(String str){
		return str == null  || "".equals(str.trim());
	}
	
	
	/**
	 * 把字符串整数部分加1,并返回
	 * @param id
	 * @return
	 */
	public static String dealId(String id){
		char []strArr=id.toCharArray();
		char sign=strArr[0];
		String str="";
		for(int i=1;i<strArr.length;i++){
			str=str+strArr[i];
		}
		int num=Integer.parseInt(str);
		num++;
		String newId=""+sign+num;
		return newId;
	}
	


}
