package com.lqbw.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

//import org.apache.commons.lang.StringUtils;

public class DateConvertUtils {
	
	public static java.util.Date parse(String dateString,String dateFormat) {
		return parse(dateString, dateFormat,java.util.Date.class);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends java.util.Date> T parse(String dateString,String dateFormat,Class<T> targetResultType) {
		if(dateString == null || "".equals(dateString))
			return null;
		DateFormat df = new SimpleDateFormat(dateFormat);
		try {
			java.util.Date t = targetResultType.getConstructor(long.class).newInstance(df.parse(dateString).getTime());
			return (T)t;
		} catch (ParseException e) {
			String errorInfo = "cannot use dateformat:"+dateFormat+" parse datestring:"+dateString;
			throw new IllegalArgumentException(errorInfo,e);
		} catch (Exception e) {
			throw new IllegalArgumentException("error targetResultType:"+targetResultType.getName(),e);
		}
	}
	
	/**
	 * 格式化时间
	 * @param date 未格式化的时间
	 * @param format 格式化的规则
	 * @return 格式化后的时间
	 */
	public static String format(java.util.Date date,String format) {
		 if(date == null)
			 return null;
		 DateFormat df = new SimpleDateFormat(format);
		 return df.format(date);
	}
	
	/**
	 * 格式化时间
	 * @param date 未格式化的时间
	 * @return 格式化后的时间(yyyy-MM-dd HH:mm:ss)
	 */
	public static String format(java.util.Date date) {
		 if(date == null)
			 return null;
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		 return df.format(date);
	}
	
	/**
	 * 获取当前年
	 * @return 当前年
	 */
	public static Integer getNowYearFormat(){
		java.util.Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy");
		Integer year=Integer.parseInt(format.format(date));
		return year;
	}
	
	/**
	 * 获取当前月份
	 * @return 当前月
	 */
	public static Integer getNowMonthFormat(){
		java.util.Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("MM");
		Integer month=Integer.parseInt(format.format(date));
		return month;
	}
	
	/**
	 * 获取当前系统时间
	 * @return 当前系统时间
	 */
	public static Date getCurTime(){
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		return date;
	}
	/** 
	* @Title: getDaydOfMonth 
	* @Description: 获取指定年指定月的天数，格式：yyyy-MM
	* @param source
	* @return
	* @return int
	* @throws 
	*/
	public static int getDaydOfMonth(String source,String fm){
		SimpleDateFormat format = new SimpleDateFormat(fm);
	    try {
	      Date date = format.parse(source);
	      Calendar calendar = new GregorianCalendar();
	      calendar.setTime(date);
	      return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
		return 0;
	}
	/** 
	* @Title: stringToDate 
	* @Description: 将字符串转换为date类型
	* @param date 时间字符串
	* @param formate 格式（默认为：yyyy-MM-dd HH:mm:ss）,
	* @return Date
	* @throws 
	*/
	public static Date stringToDate(String date,String formate){
		if("".equals(formate) || formate == null){
			formate = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf =   new SimpleDateFormat(formate);
		Date res = null;  
		try {  
			res = sdf.parse(date);  
		} catch (ParseException e) {  
			e.printStackTrace();  
		}  
		return res;
	}
	/** 
	* @Title: dateRise 
	* @Description: 在原有时间基础上，增长riseMillis个毫秒数
	* @param curDate 原时间
	* @param riseMillis 偏移量（毫秒）
	* @return String
	* @throws 
	*/
	public static String dateRise(String curDate,long riseMillis){
		if(curDate == null || "".equals(curDate)){
			return "";
		}else{
			if(riseMillis == 0){
				return curDate;
			}
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newDate = "";
		try {
			Date s = sf.parse(curDate);
			long new_date = s.getTime()+riseMillis;
			s.setTime(new_date);
			newDate = sf.format(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDate;
	}
	
	/** 
	* @Title: get95598Time 
	* @Description: 快捷生成95598时间
	* @return
	* @return Map<String,String>
	* @throws 
	*/
	public static Map<String,String> get95598Time(String dispachTime){
		Map<String,String> timeMap = new HashMap<String,String>();
		timeMap.put("firstContactTime", dateRise(dispachTime,31000*2*2));//首次联系时间
		timeMap.put("arrivalTime", dateRise(dispachTime,32000*2*6));//现场时间
		timeMap.put("faultAnalyseTime", dateRise(dispachTime,31000*2*7));//初步分析时间
		timeMap.put("writeTime", dateRise(dispachTime,31000*2*9));//首填写预计送电时间
		timeMap.put("predictPowerTime", dateRise(dispachTime,31000*2*25));//预计送电时间
		timeMap.put("recoDate", dateRise(dispachTime,32000*2*10));//排除时间
		return timeMap;
	}
	
}
