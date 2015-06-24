package com.lqbw.model.upload;


import java.io.Serializable;

/**
 * Group: 姚丰利（lidey）
 * Date: 2014-08-23
 * Time: 18:15
 * To change this template use File | Settings | File Templates.
 */
public class UploadFileBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3572502110777928768L;
	private String name;
    private String type;
    private String size;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 单位kb
     * @return
     */
    public String getSize() {
        return size;
    }

    /**
     * 单位kb
     * @param size
     */
    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
