package com.lqbw.service.upload.impl;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.lqbw.common.StringUtil;
import com.lqbw.model.upload.UploadFileBean;
import com.lqbw.service.upload.UploadFileService;


/**
 * Group: 姚丰利（lidey）
 * Date: 2014-08-23
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
@Service("loadFileService")
public class UploadFileServiceImpl implements UploadFileService {

    @Value("/Users/lidey/Tools/uploadFile1")
    private String rootPath;

    private static String DEFAULT_PATH = "/temp";
    private static String UPLOAD_PATH = "/UPLOAD";
    private static String URL_PREFIX = "/file";

    @Override
    public List<UploadFileBean> uploadMultiFile(MultipartHttpServletRequest request, String modulePath){
        List<UploadFileBean> uploadFileBeanList = new ArrayList<UploadFileBean>();
        DecimalFormat df = new DecimalFormat("########.00 ");
        UploadFileBean uploadFileBean = null;

        if (StringUtils.isEmpty(modulePath)) {
            modulePath = DEFAULT_PATH;
        }

        if (StringUtils.isEmpty(rootPath)) {
            //throw new CustomException(CustomExceptionEnum.NO_CONFIGURATION);
        }

        File rootDirectory = new File(rootPath, UPLOAD_PATH);
        if (!rootDirectory.exists()) {
            rootDirectory.mkdirs();
        }

        File moduleDirectory = new File(rootDirectory, modulePath);
        if (!moduleDirectory.exists()) {
            moduleDirectory.mkdirs();
        }

        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        File dateDirectory = new File(moduleDirectory, "/" + formater.format(new Date()));
        if (!dateDirectory.exists()) {
            dateDirectory.mkdirs();
        }

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        if (multipartResolver.isMultipart(request)) {
            Iterator<String> fileNames = request.getFileNames();
            MultipartFile multipartFile = null;
            Magic parser = new Magic();
            StringBuilder url = null;
            while ((fileNames.hasNext())) {
                multipartFile = request.getFile(fileNames.next());
                if (multipartFile != null) {
                    try {
                        File uploadFile = new File(dateDirectory, this.getRandomName(multipartFile.getOriginalFilename()));
                        multipartFile.transferTo(uploadFile);
                        if (uploadFile.exists()) {
                            uploadFileBean = new UploadFileBean();
                            url = new StringBuilder();
                            uploadFileBean.setName(multipartFile.getOriginalFilename());
                            try {
                                MagicMatch match = parser.getMagicMatch(uploadFile, false);
                                uploadFileBean.setType(match.getMimeType());
                            } catch (MagicParseException e) {
                                e.printStackTrace();
                            } catch (MagicMatchNotFoundException e) {
                                e.printStackTrace();
                            } catch (MagicException e) {
                                e.printStackTrace();
                            }
                            uploadFileBean.setSize(df.format(multipartFile.getSize() / 1024));

                            url.append(request.getContextPath());
                            url.append(this.URL_PREFIX);
                            url.append(uploadFile.getPath().replace(rootDirectory.getPath(), "").replace("\\","/"));
                            uploadFileBean.setUrl(url.toString());
                            uploadFileBeanList.add(uploadFileBean);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
        return uploadFileBeanList;
    }

    @Override
    public UploadFileBean uploadOneFile(MultipartHttpServletRequest request, String modulePath){
        List<UploadFileBean> uploadFileBeanList = this.uploadMultiFile(request, modulePath);
        if (uploadFileBeanList.isEmpty())
            return null;
        else
            return uploadFileBeanList.get(0);
    }

    /**
     * 获取文件扩展名
     *
     * @return string
     */
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 依据原始文件名生成新文件名
     *
     * @param fileName
     * @return
     */
    private String getRandomName(String fileName) {
        Random random = new Random();
        return StringUtil.getRandomString(6) + this.getFileExt(fileName);
    }

	public String geRootPath() {
		return rootPath;
	}
}
