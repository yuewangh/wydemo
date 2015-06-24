package com.lqbw.common;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * User: 姚丰利（lidey）
 * Date: 13-5-23
 * Time: 下午2:21
 * To change this template use File | Settings | File Templates.
 */
public class UploadUnit {

    private static FreeMarkerConfigurer freeMarkerConfigurer;
    
    /**
     * 初始化
     * @return
     */
    private static FreeMarkerConfigurer initService() {
        if (freeMarkerConfigurer == null)
            freeMarkerConfigurer = ApplicationContextUtil.getSpringBean("freeMarkerConfigurer", new FreeMarkerConfigurer().getClass());
        return freeMarkerConfigurer;
    }

    /**
     * 获取上传模板
     * @param id
     * @param name
     * @param extensions
     * @param js
     * @return
     */
    public static String getUploadTemplate(String id, String name, String extensions, String js, String base) {
        try {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("id", id);
            data.put("name", name);
            data.put("extensions", extensions);
            data.put("js", js);
            data.put("base", base);
            //声明Freemarker配置信息
            Configuration freemarkerCfg = initService().createConfiguration();
            //模板缓冲时间，0为永远不缓冲
            freemarkerCfg.setTemplateUpdateDelay(0);
            Template template = freemarkerCfg.getTemplate("/upload/upload.ftl", "UTF-8");
            StringWriter writer = new StringWriter();
            template.process(data, writer);
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return "";
    }
}
