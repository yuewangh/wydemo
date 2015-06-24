/** *  */package com.wydemo.common;import org.springframework.web.context.ContextLoader;import org.springframework.web.context.WebApplicationContext;/** * @author 姚丰利(lidey) * 创建时间：2012-9-19 下午12:10:43  */public class ApplicationContextUtil {		private static WebApplicationContext wac;		private ApplicationContextUtil(){}		/**	 * 获取Spring的 WebApplicationContext	 * @return WebApplicationContext	 */	public static WebApplicationContext getWebApplicationContext() {		if(wac==null)			wac=ContextLoader.getCurrentWebApplicationContext();		return wac;	}		/**	 * 获取SpringBean	 * @param beanName SpringBean名称	 * @return Object 实例化对象	 */	public static Object getBean(String beanName) {  		  return getWebApplicationContext().getBean(beanName);  	 } 		/**	 * 获取SpringBean	 * @param beanName SpringBean名称	 * @param clazs 返回类型	 * @return 实例化clazs对象	 */	public static <T> T getSpringBean(String beanName, Class<T> clazs) {		return clazs.cast(getBean(beanName));	}}