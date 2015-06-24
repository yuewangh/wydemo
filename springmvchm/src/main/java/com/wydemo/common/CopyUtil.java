package com.lqbw.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.lqbw.model.student.Student;


public class CopyUtil {
	/*
	 * public static void Copy(Object source, Object dest)throws Exception {
	 * //获取属性 BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(),
	 * java.lang.Object.class); PropertyDescriptor[] sourceProperty =
	 * sourceBean.getPropertyDescriptors();
	 * 
	 * BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(),
	 * java.lang.Object.class); PropertyDescriptor[] destProperty =
	 * destBean.getPropertyDescriptors();
	 * 
	 * try{ for(int i=0;i<sourceProperty.length;i++){
	 * 
	 * for(int j=0;j<destProperty.length;j++){
	 * 
	 * if(sourceProperty[i].getName().equals(destProperty[j].getName())){
	 * //调用source的getter方法和dest的setter方法
	 * if(!sourceProperty[i].getName().equals("dwzcsqsdate") &&
	 * !sourceProperty[i].getName().equals("jbrcodedate") &&
	 * !sourceProperty[i].getName().equals("zzjgdate") &&
	 * !sourceProperty[i].getName().equals("yyzzdate")){
	 * destProperty[j].getWriteMethod().invoke(dest,
	 * sourceProperty[i].getReadMethod().invoke(source)); break; } Object value
	 * = sourceProperty[i].getReadMethod().invoke(source); if (value != null) {
	 * destProperty[j].getWriteMethod().invoke(dest,value); } } } }
	 * }catch(Exception e){ throw new Exception("属性复制失败:"+e.getMessage()); } }
	 */
	/**
	 * 将source对象的属性值复制到target对象
	 * 
	 * @param source
	 * @param target
	 * @param ignoreNullValue
	 *            是否忽略值为null的属性,即为null属性,是否将null赋值给目标对象的属性
	 */
	@SuppressWarnings("rawtypes")
	public static void Copy(Object source, Object target) {
		if (source == null || target == null) {
			return;
		}
		try {
			BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(),
					java.lang.Object.class);
			PropertyDescriptor[] sourceProperty = sourceBean
					.getPropertyDescriptors();
			BeanInfo targetBean = Introspector.getBeanInfo(target.getClass(),
					java.lang.Object.class);
			PropertyDescriptor[] targetProperty = targetBean
					.getPropertyDescriptors();
			for (int i = 0; i < targetProperty.length; i++) {
				// 获取目标_属性名称
				String t_name = targetProperty[i].getName();
				// 获取目标_属性类型
				Class t_propertyType = targetProperty[i].getPropertyType();
				// 获取目标_属性set方法
				Method writeMethod = targetProperty[i].getWriteMethod();
				// 这里判断是不是public的set方法
				if (!Modifier.isPublic(writeMethod.getModifiers())) {
					writeMethod.setAccessible(true);
				}
				for (int j = 0; j < sourceProperty.length; j++) {
					// 获取原_属性名称
					String s_name = sourceProperty[j].getName();
					// 获取原_属性名称
					Class s_propertyType = sourceProperty[j].getPropertyType();
					// 获取原_属性get方法
					Method readMethod = sourceProperty[j].getReadMethod();
					// 这里判断是不是public的get方法
					if (!Modifier.isPublic(readMethod.getModifiers())) {
						readMethod.setAccessible(true);
					}
					if (t_name.equals(s_name)
							&& (t_propertyType == s_propertyType)) {
						// 根据get方法获取原_属性值
						Object value = readMethod.invoke(source);
						if (value == null) {
							continue;
						}
						// 根据set方法把属性值赋值到目标里
						writeMethod.invoke(target, value);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("属性复制失败:" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 将source对象的属性值复制到target对象
	 * 
	 * @param source
	 * @param target
	 * @param ignoreNullValue
	 *            是否忽略值为null的属性,即为null属性,是否将null赋值给目标对象的属性
	 */
	@SuppressWarnings("rawtypes")
	public static void copyProperties(Object source, Object target,
			boolean ignoreNullValue) {
		if (source == null || target == null) {
			return;
		}
		try {
			BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(),
					java.lang.Object.class);
			PropertyDescriptor[] sourceProperty = sourceBean
					.getPropertyDescriptors();
			BeanInfo targetBean = Introspector.getBeanInfo(target.getClass(),
					java.lang.Object.class);
			PropertyDescriptor[] targetProperty = targetBean
					.getPropertyDescriptors();
			for (int i = 0; i < targetProperty.length; i++) {
				// 获取目标_属性名称
				String t_name = targetProperty[i].getName();
				// 获取目标_属性类型
				Class t_propertyType = targetProperty[i].getPropertyType();
				// 获取目标_属性set方法
				Method writeMethod = targetProperty[i].getWriteMethod();
				// 这里判断是不是public的set方法
				if (!Modifier.isPublic(writeMethod.getModifiers())) {
					writeMethod.setAccessible(true);
				}
				for (int j = 0; j < sourceProperty.length; j++) {
					// 获取原_属性名称
					String s_name = sourceProperty[j].getName();
					// 获取原_属性名称
					Class s_propertyType = sourceProperty[j].getPropertyType();
					// 获取原_属性get方法
					Method readMethod = sourceProperty[j].getReadMethod();
					// 这里判断是不是public的get方法
					if (!Modifier.isPublic(readMethod.getModifiers())) {
						readMethod.setAccessible(true);
					}
					if (t_name.equals(s_name)
							&& (t_propertyType == s_propertyType)) {
						// 根据get方法获取原_属性值
						Object value = readMethod.invoke(source);
						if (ignoreNullValue && (value == null)) {
							continue;
						}
						// 根据set方法把属性值赋值到目标里
						writeMethod.invoke(target, value);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("属性复制失败:" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 将source对象的属性值复制到target对象
	 * 
	 * @param source
	 * @param target
	 * @param ignoreNullValue 是否忽略值为null的属性,即为null属性,是否将null赋值给目标对象的属性
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public static void Copy2(Object source, Object target, boolean ignoreNullValue) {
		if (source == null || target == null) {
			return;
		}
		try {
			BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(),
					java.lang.Object.class);
			// PropertyDescriptor[] sourceProperty =
			// sourceBean.getPropertyDescriptors();
			BeanInfo targetBean = Introspector.getBeanInfo(target.getClass(),
					java.lang.Object.class);
			PropertyDescriptor[] targetProperty = targetBean
					.getPropertyDescriptors();
			for (int i = 0; i < targetProperty.length; i++) {
				// 获取目标_属性名称
				String t_name = targetProperty[i].getName();
				// 获取目标_属性类型
				Class t_propertyType = targetProperty[i].getPropertyType();
				// 获取目标_属性set方法
				Method writeMethod = targetProperty[i].getWriteMethod();
				// 这里判断是不是public的set方法
				if (!Modifier.isPublic(writeMethod.getModifiers())) {
					writeMethod.setAccessible(true);
				}
				Method readMethod = source.getClass().getMethod(
						"get" + upperStr(t_name), new Class[0]);// 根据方法名和参数获取方法
				System.out.println(readMethod.getName());
				Method readMethod1 = source.getClass().getMethod(
						"set" + upperStr(t_name), t_propertyType);
				System.out.println(readMethod1.getName());
				if (readMethod != null) {
					// 这里判断是不是public的get方法
					if (!Modifier.isPublic(readMethod.getModifiers())) {
						readMethod.setAccessible(true);
					}
					Object value = readMethod.invoke(source);
					if (ignoreNullValue && (value == null)) {
						continue;
					}
					// 根据set方法把属性值赋值到目标里
					writeMethod.invoke(target, value);
				}
			}
		} catch (Exception e) {
			System.out.println("属性复制失败:" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 把输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private static String upperStr(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	public static void main(String[] args) {
		Student m1 = new Student();
		m1.setId("123123123");
		Student m2 = new Student();
		System.out.println(m2.getId());
		Copy2(m1, m2, true);
		System.out.println(m2.getId());
	}
}
