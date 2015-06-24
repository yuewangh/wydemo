package com.lqbw.controller.sys;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lqbw.model.sys.SysUser;
import com.lqbw.operation_log.annotation.Log;
import com.lqbw.operation_log.enumeration.LogType;
import com.lqbw.service.sys.SysUserService;
import com.lqbw.validate.ValidateFiled;
import com.lqbw.validate.ValidateGroup;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;
	@Resource(name = "sysUserController")
	private SysUserController sysUserController;
	//新增
		@RequestMapping("/login")
		@ResponseBody
		public Map<String, Object> login(String user_name,String password){
			Map<String, Object> map = new HashMap<String, Object>();
			SysUser sysUser = sysUserService.getByName(user_name);
			if(sysUser != null){
				if(sysUser.getPassword().equals(password)){
					map.put("success", true);
				} else{
					map.put("success", false);
					map.put("msg", "用户名或密码错误");
				}
			} else{
				map.put("success", false);
				map.put("errormsg", "用户不存在");
			}
			return map;
		}
	//新增
		
	/*@RequestMapping("savePerson")
		public ModelAndView savePerson(Person person , String name , int age , String email){
			ModelAndView mav = new ModelAndView("/index.jsp");
			System.out.println("addPerson()方法调用成功！");
			return mav ;		//返回index.jsp视图
	}*/
		/*@ValidateGroup(fileds = {
				//index=0 表示下面方法的第一个参数，也就是person  nutNull=true 表示不能为空
				@ValidateFiled(index=0 , notNull=true ) ,
				//index=0 表示第一个参数  filedName表示该参数的一个属性 ，也就是person.id 最小值为3 也就是 person.id 最小值为3
				@ValidateFiled(index=0 , notNull=true , filedName="id" , minVal = 3) ,
				//表示第一个参数的name 也就是person.name属性最大长度为10，最小长度为3
				@ValidateFiled(index=0 , notNull=true , filedName="name" , maxLen = 10 , minLen = 3 ) ,
				//index=1 表示第二个参数最大长度为5，最小长度为2
				@ValidateFiled(index=1 , notNull=true , maxLen = 5 , minLen = 2 ) ,
				@ValidateFiled(index=2 , notNull=true , maxVal = 100 , minVal = 18),
				@ValidateFiled(index=3 , notNull=false , regStr= "^\\w+@\\w+\\.com$" )
		})*/
	@ValidateGroup(fileds = { 
			@ValidateFiled(index=1 , notNull=true , filedName="user_name" , maxLen = 10 , minLen = 3,regStr="^[a-zA-Z0-9_]+$",errormsg="只能有字母数字下划线组成",chname="用户名"),
			@ValidateFiled(index=1 , notNull=true , filedName="password" , maxLen = 12 , minLen = 6,regStr="^[a-zA-Z0-9_]+$",errormsg="只能有字母数字下划线组成",chname="密码"),
	})
	
	@RequestMapping(value="/insert",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String, Object> insert(HttpServletRequest request,SysUser sysUser){
		Map<String, Object> map = new HashMap<String, Object>();
		SysUser sus = sysUserService.getByName(sysUser.getUser_name());
		if(sus == null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String id=UUID.randomUUID().toString();
			sysUser.setId(id);
			sysUser.setCreate_time(sdf.format(new Date()));
			sysUser.setType("2");
			request.setAttribute("bId", id);
			sysUserController.insertSysUser(request,sysUser);
			map.put("success", true);
		} else{
			map.put("success", false);
			map.put("errormsg", "此用户名已被注册，请更换");
		}
		return map;
	}
	@Log(type = LogType.SYSUSERSAVE, content = "进行了注册用户操作")
	public void insertSysUser(HttpServletRequest request,SysUser sysUser) {
		sysUserService.insert(sysUser);
	}

	//修改
	@RequestMapping("/update")
	public Map<String, Object> update(HttpServletRequest request,SysUser sysUser){
		Map<String, Object> map = new HashMap<String, Object>();
		sysUserService.update(sysUser);
		map.put("success", true);
		return map;
	}

	//删除
	@RequestMapping("/delete")
	public Map<String, Object> delete(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		sysUserService.delete(id);
		map.put("success", true);
		return map;
	}

	//进入编辑页面
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request,String id){
		SysUser sysUser = sysUserService.getById(id);
		request.setAttribute("sysUser", sysUser);
		return new ModelAndView("sysUser/edit");
	}

	//查询分页列表
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request,SysUser sysUser){
		int count = sysUserService.getCount(sysUser);
		sysUser.setTotalCount(count);
		sysUser.calculatePage();
		List<SysUser> sysUserlist = sysUserService.getAllList(sysUser);
		request.setAttribute("sysUserlist", sysUserlist);
		return new ModelAndView("sysUser/list");
	}

}