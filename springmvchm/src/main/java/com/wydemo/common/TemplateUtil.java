package com.lqbw.common;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 项目名称：portal
 * 开发人员：姚丰利（lidey）
 * 创建时间：14-4-3
 * <p/>
 * 请编写描述信息 .
 */
public class TemplateUtil {

    /**
     * @param root
     * @param templateLoader
     * @param templateName
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String formatString(Map<String, Object> root, TemplateLoader templateLoader, String templateName) throws Exception {
        @SuppressWarnings("deprecation")
		Configuration cfg = new Configuration();
        StringWriter writer = new StringWriter();
        cfg.setTemplateLoader(templateLoader);
        cfg.setNumberFormat("0.##########");
        //模板缓冲时间，0为永远不缓冲
        cfg.setTemplateUpdateDelay(0);
        cfg.setEncoding(Locale.getDefault(), "UTF-8");
        Template template = cfg.getTemplate(templateName, "UTF-8");
        template.process(root, writer);
        return writer.toString();
    }
}
