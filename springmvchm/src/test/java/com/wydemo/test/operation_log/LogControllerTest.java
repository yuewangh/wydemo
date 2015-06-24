package com.lqbw.test.operation_log;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lqbw.operation_log.annotation.Log;
import com.lqbw.operation_log.enumeration.LogType;

@Controller("log")
public class LogControllerTest {
	private static org.apache.commons.logging.Log logger = LogFactory.getLog(LogControllerTest.class);
	
	@Log(type = LogType.SAVE, content = "进行了添加操作")
	@RequestMapping("save")
	public ModelAndView testSave(HttpServletRequest req){
		logger.debug("===========>进行了添加操作");
		ModelAndView mv = new ModelAndView("save");
		mv.addObject("bId", "1234567890");
		return mv;
	}
	
	@Log(type = LogType.UPDATE, content = "进行了更新操作")
	@RequestMapping("update")
	public ModelAndView testUpdate(HttpServletRequest req){
		logger.debug("===========>进行了更新操作");
		ModelAndView mv = new ModelAndView("update");
		mv.addObject("bId", "1234567890");
		return mv;
	}
	
	@Log(type = LogType.DELETE, content = "进行了删除操作")
	@RequestMapping("delete")
	public ModelAndView testDelete(HttpServletRequest req){
		logger.debug("===========>进行了删除操作");
		ModelAndView mv = new ModelAndView("delete");
		mv.addObject("bId", "1234567890");
		return mv;
	}
	
	@Log(type = LogType.SAVE, content = "进行了有异常的操作")
	@RequestMapping("throw")
	public ModelAndView testThrow(HttpServletRequest req) throws Exception {
		throw new Exception("出现了异常");
	}
	
	@Log(type = LogType.SAVE, content = "进行了没有request参数的方法")
	@RequestMapping("noReq1")
	public ModelAndView testNoReq(){
		ModelAndView mv = new ModelAndView("noRequest");
		mv.addObject("bId", "1234567890");
		return mv;
	}
	
	@Log(type = LogType.SAVE, content = "进行了没有request参数的方法")
	@RequestMapping("noReq2")
	public ModelAndView testNoReq(String id){
		ModelAndView mv = new ModelAndView("noRequest");
		mv.addObject("bId", "1234567890");
		return mv;
	}
}
