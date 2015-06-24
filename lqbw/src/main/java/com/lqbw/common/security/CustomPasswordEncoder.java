package com.lqbw.common.security;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
* 项目名称：zc_yxk   
* 类名称：CustomPasswordEncoder   
* 类描述：   为对应前台jquery md5加密言后与后台加密方式不一样，如下实现 密码+登录名称组合后通过md5不加密言方式生成密码
* 创建人：xlzhangf   
* 创建时间：2015年4月29日 下午1:39:16   
* 修改人：xlzhangf   
* 修改时间：2015年4月29日 下午1:39:16   
* 修改备注：   
* @version    
*
 */
public class CustomPasswordEncoder extends Md5PasswordEncoder {

	@Override
	public String encodePassword(String rawPass, Object salt) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        md5.setEncodeHashAsBase64(false);

        return md5.encodePassword(rawPass+salt,"");
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        md5.setEncodeHashAsBase64(false);

        return md5.isPasswordValid(encPass, rawPass+salt, "");
	}

}
