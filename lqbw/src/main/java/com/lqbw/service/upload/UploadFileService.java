package com.lqbw.service.upload;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lqbw.model.upload.UploadFileBean;


/**
 * Group: 姚丰利（lidey）
 * Date: 2014-08-23
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
public interface UploadFileService {

    /**
     * 多文件上传服务
     * @param request
     * @param modulePath
     * @return
     */
    public List<UploadFileBean> uploadMultiFile(MultipartHttpServletRequest request, String modulePath);

    /**
     * 单文件上传服务
     * @param request
     * @param modulePath
     * @return
     */
    public UploadFileBean uploadOneFile(MultipartHttpServletRequest request, String modulePath);
}
