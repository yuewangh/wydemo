package com.lqbw.common;

import java.io.File;
import java.io.UnsupportedEncodingException;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

/**
 * Group: 姚丰利（lidey）
 * Date: 2015-04-22
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
public class MineTypeUtil {

    /**
     * 获取文件mine类型
     *
     * @param file
     * @return
     * @throws UnsupportedEncodingException
     * @throws MagicParseException
     * @throws MagicException
     * @throws MagicMatchNotFoundException
     */
    public static String getMineType(File file) throws Exception {
        MagicMatch match = Magic.getMagicMatch(file, false, true);
        String mineType = match.getMimeType();
        String fileName = new String(file.getName().getBytes("utf-8"), "ISO8859-1");
        if ("text/plain".equals(mineType)) {
            if (fileName.toLowerCase().endsWith(".css"))
                mineType = "text/css";
            if (fileName.toLowerCase().endsWith(".js"))
                mineType = "text/javascript";
        }
        return mineType;
    }
}
