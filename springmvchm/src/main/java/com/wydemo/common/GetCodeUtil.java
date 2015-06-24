package com.lqbw.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GetCodeUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	public static String getCode(){
		Random random = new Random();
		return sdf.format(new Date())+random.nextInt(99);
	}
}
