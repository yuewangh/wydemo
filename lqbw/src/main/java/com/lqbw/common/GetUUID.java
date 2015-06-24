package com.lqbw.common;

import java.util.UUID;

public class GetUUID {
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString());
	}
	/**
	 * 去掉UUID的-
	 * @return
	 */
	public static String getMyUUID(String uid){
		StringBuffer sb = new StringBuffer();  
		for(int i=0;i<uid.length();i++){
			char chr=uid.charAt(i);
			if(Character.isLowerCase(chr)){  
	           sb.append(Character.toUpperCase(chr));   
	        }  else{
	        	sb.append(chr);
	        }

		}
		return sb.toString();
	}
}
