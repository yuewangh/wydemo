/**
 * 
 */
package com.wydemo.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.wydemo.model.sys.SysUser;


/**
 * @author 姚丰利(lidey)
 * 创建时间：2012-8-7 上午09:59:57 
 */
public class SecurityUtils {
	
	/**
	 * 
	 * @return
	 */
	public static SysUser getUserInfoDetails() {
		SysUser sysUser=null;
		try{
			SecurityContext sc = SecurityContextHolder.getContext();   
		    Authentication auth = sc.getAuthentication();   
		    Object principal = auth.getPrincipal();   
		    if (principal instanceof SysUser) {
		    	sysUser = (SysUser) principal;
		    } 
		}catch(Exception e){
			e.printStackTrace();
		}
	    return sysUser;
	}
	
}
