package com.lqbw.common;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.lqbw.common.security.CustomPasswordEncoder;


/**
 * Group: 姚丰利（lidey）
 * Date: 2015-04-21
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
public final class MD5PasswordEncoder {

    /**
     *
     * @param pwd
     * @param salt
     * @return
     */
    public final static String encode(String pwd, String salt) {

        /*Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        md5.setEncodeHashAsBase64(false);

        return md5.encodePassword(pwd, salt);*/
    	CustomPasswordEncoder md5 = new CustomPasswordEncoder();
    	md5.setEncodeHashAsBase64(false);
    	return md5.encodePassword(pwd, salt);
    }

    /**
     *
     * @param encPwd
     * @param rawPwd
     * @param salt
     * @return
     */
    public final static boolean valid(String encPwd, String rawPwd, String salt) {

        /*Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        md5.setEncodeHashAsBase64(false);

        return md5.isPasswordValid(encPwd, rawPwd, salt);*/

    	CustomPasswordEncoder md5 = new CustomPasswordEncoder();
    	md5.setEncodeHashAsBase64(false);
    	return md5.isPasswordValid(encPwd, rawPwd, salt);
    }
}
