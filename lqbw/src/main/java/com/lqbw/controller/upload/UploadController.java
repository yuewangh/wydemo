package com.lqbw.controller.upload;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.lqbw.model.upload.UploadFileBean;
import com.lqbw.service.sys.SysUserService;
import com.lqbw.service.upload.UploadFileService;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
    @Autowired
    private UploadFileService uploadFileService;

	@Autowired
	private SysUserService sysUserService;

	// 进入编辑页面
	@RequestMapping("/toupload")
	public ModelAndView edit(HttpServletRequest request) {
		return new ModelAndView("upload/upload");
	}
	

    @RequestMapping(value = "/multiFile", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public List<UploadFileBean> multiFile(@RequestParam("path") String path, MultipartHttpServletRequest request, Model model) {

        List<UploadFileBean> uploadFileBeanList = new ArrayList<UploadFileBean>();
        try {
            uploadFileBeanList = uploadFileService.uploadMultiFile(request, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadFileBeanList;
    }


    @RequestMapping(value = "/singleFile", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public UploadFileBean singleFile(MultipartHttpServletRequest request, Model model) {
        String path = request.getParameter("path");

        List<UploadFileBean> uploadFileBeanList = new ArrayList<UploadFileBean>();
        try {
            uploadFileBeanList = uploadFileService.uploadMultiFile(request, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!uploadFileBeanList.isEmpty())
            return uploadFileBeanList.get(0);
        else
            return null;
    }
}