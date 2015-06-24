package com.lqbw.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;



/**
 * 验证注解处理类
 * @author zhoufeng
 */
@Component
@Aspect
public class ValidateAspectHandel {
	/**
	 * 使用AOP对使用了ValidateGroup的方法进行代理校验
	 * @throws Throwable 
	 */
	@SuppressWarnings({ "finally", "rawtypes" })
	@Around("@annotation(com.lqbw.validate.ValidateGroup)")
	public Object validateAround(ProceedingJoinPoint joinPoint) throws Throwable  {
		boolean flag = false ;
		ValidateGroup an = null;
		Object[] args =  null ;
		Method method = null;
		Object target = null ;
		String methodName = null;
		String errormsg = "";
		try{
			methodName = joinPoint.getSignature().getName();
			target = joinPoint.getTarget();
			method = getMethodByClassAndName(target.getClass(), methodName);	//得到拦截的方法
			args = joinPoint.getArgs();		//方法的参数	
			an = (ValidateGroup)getAnnotationByMethod(method ,ValidateGroup.class );//注解参数
			errormsg =validateFiled(an.fileds(),args);
			if(errormsg.equals("")){
				flag = true;
			}else{
				flag = false;
			}
		}catch(Exception e){
			flag = false;
		}finally{
			if(flag){
				System.out.println("验证通过");
				return joinPoint.proceed();
			}else{	//这里使用了Spring MVC ，所有返回值应该为Strng或ModelAndView ，如果是用Struts2，直接返回一个String的resutl就行了
				System.out.println("验证未通过");
				Class returnType = method.getReturnType();	//得到方法返回值类型
				if(returnType == String.class){	//如果返回值为Stirng
					return "/error.jsp";		//返回错误页面
				}else if(returnType == ModelAndView.class){
					return new ModelAndView("/error.jsp");//返回错误页面
				}else if(returnType == Map.class){	//当使用Ajax的时候 可能会出现这种情况
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("success", false);
					map.put("errormsg", errormsg);
					return map;
				} else{
					return null ;
				}
			}
		}
	}

	/**
	 * 验证参数是否合法
	 */
	public String validateFiled( ValidateFiled[] valiedatefiles , Object[] args) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		String errormsg="";
		for (ValidateFiled validateFiled : valiedatefiles) {
			Object arg = null;
			String fileName=validateFiled.filedName();
			String chname = validateFiled.chname();
			if("".equals(fileName) ){
				arg = args[validateFiled.index()];
			}else{
				arg = getFieldByObjectAndFileName(args[validateFiled.index()] ,validateFiled.filedName() );
			}

			if(validateFiled.notNull()){		//判断参数是否为空
				if(arg == null ){
					errormsg+=chname+"：不能为空;\n";
				}
			}
			int maxLen = validateFiled.maxLen();
			if(maxLen > 0){		//判断字符串最大长度
				if(((String)arg).length() > maxLen){
					errormsg+=chname+"：最大长度不能超过"+maxLen+";\n";
					continue;
				}
			}
			int minLen = validateFiled.minLen();
			if(minLen > 0){		//判断字符串最小长度
				if(((String)arg).length() < minLen){
					errormsg+=chname+"：最小长度不能小于"+minLen+";\n";
					continue;
				}
			}
			int maxVal = validateFiled.maxVal();
			if(maxVal != -1){	//判断数值最大值
				if( (Integer)arg > maxVal) {
					errormsg+=chname+"：值不能大于"+maxVal+";\n";
					continue;
				}
					
			}
			int minVal = validateFiled.minVal();
			if(minVal != -1){	//判断数值最小值
				if((Integer)arg < minVal){
					errormsg+=chname+"：值不能小于"+minVal+";\n";
					continue;
				}
					
			}
			String regStr = validateFiled.regStr();
			if(!"".equals(regStr)){	//判断正则
				if(arg instanceof String){
					if(!((String)arg).matches(regStr)){
						errormsg+=chname+"："+validateFiled.errormsg()+";\n";
						continue;
					}
				}else{
					errormsg+= "配置错误：非字符串类型无法用正则表达式匹配;\n";
					continue;
				}
			}
		}
		return errormsg;
	}

	/**
	 * 根据对象和属性名得到 属性
	 */
	public Object getFieldByObjectAndFileName(Object targetObj , String fileName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		String tmp[] = fileName.split("\\.");
		Object arg = targetObj ;
		for (int i = 0; i < tmp.length; i++) {
			Method methdo = arg.getClass().
					getMethod(getGetterNameByFiledName(tmp[i]));
			arg = methdo.invoke(arg);
		}
		return arg ;
	}

	/**
	 * 根据属性名 得到该属性的getter方法名
	 */
	public String getGetterNameByFiledName(String fieldName){
		return "get" + fieldName.substring(0 ,1).toUpperCase() + fieldName.substring(1) ;
	}

	/**
	 * 根据目标方法和注解类型  得到该目标方法的指定注解
	 */
	@SuppressWarnings("rawtypes")
	public Annotation getAnnotationByMethod(Method method , Class annoClass){
		Annotation all[] = method.getAnnotations();
		for (Annotation annotation : all) {
			if (annotation.annotationType() == annoClass) {
				return annotation;
			}
		}
		return null;
	}

	/**
	 * 根据类和方法名得到方法
	 */
	@SuppressWarnings("rawtypes")
	public Method getMethodByClassAndName(Class c , String methodName){
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			if(method.getName().equals(methodName)){
				return method ;
			}
		}
		return null;
	}

}