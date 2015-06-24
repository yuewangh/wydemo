package com.lqbw.service.upload;

import java.io.File;

/**
 * Group: 姚丰利（lidey）
 * Date: 2014-08-24
 * Time: 02:27
 * To change this template use File | Settings | File Templates.
 */
public interface LoadFileService {

    /**
     * 根据文件的虚拟路径获得文件
     * @param fileUrl
     * @return
     */
    public File loadFileByFileUrl(String fileUrl);
}
