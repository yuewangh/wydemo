package com.lqbw.controller.student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lqbw.model.student.Student;
import com.lqbw.operation_log.annotation.Log;
import com.lqbw.operation_log.enumeration.LogType;
import com.lqbw.service.student.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Resource(name = "studentController")
	private StudentController studentController;

	//新增
	@RequestMapping("/saveStudent")
	@ResponseBody
	public Map<String, Object> saveStudent(HttpServletRequest request,@Valid Student student,BindingResult result){
		Map<String, Object> map = new HashMap<String, Object>();
		if(result.hasErrors()){//这个是spring validation校验后返回的结果对象  
			//如果校验失败，你要做什么事情  
			/*String[] codes = result.getFieldError().getCodes();//验证出错的那个类型名称 比如NotEmpty  
			String code = result.getFieldError().getCode()//验证出错的那个类型名称 比如NotEmpty  
	        String errmsg = result.getFieldError().getDefaultMessage();//出错的信息  
	        System.out.println(code);
	        System.out.println(errmsg);*/
			String errmsg ="";
	        List<FieldError> errlist = result.getFieldErrors();
	        for(int i=0;i<errlist.size();i++){
	        	errmsg+=errlist.get(i).getDefaultMessage()+"；\n";
	        }
	        map.put("success", false);
	        map.put("errmsg", errmsg);
	        return map;  
		}else{  
			String id = student.getId();
			if(id == null || id.equals("")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				id=UUID.randomUUID().toString();
				student.setId(id);
				student.setCreate_date(sdf.format(new Date()));
				request.setAttribute("bId", id);
				studentController.insertStudent(request,student);
			} else{
				request.setAttribute("bId", id);
				studentController.updateStudent(request,student);
			}
			map.put("id", id);
			map.put("success", true);
			return map;
		}  
	}
	
	@Log(type = LogType.STUDENTUPDATE, content = "进行了修改学生操作")
	public void updateStudent(HttpServletRequest request,Student student) {
		studentService.update(student);
	}
	@Log(type = LogType.STUDENTSAVE, content = "进行了新增学生操作")
	public void insertStudent(HttpServletRequest request,Student student) {
		studentService.insert(student);
	}

	//删除
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,String id){
		Map<String, Object> map = new HashMap<String, Object>();
		studentService.delete(id);
		map.put("success", true);
		return map;
	}

	//进入编辑页面
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request,String id){
		Student student = studentService.getById(id);
		request.setAttribute("student", student);
		return new ModelAndView("student/edit");
	}

	//查询分页列表
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest request,Student student){
		int count = studentService.getCount(student);
		student.setTotalCount(count);
		student.calculatePage();
		List<Student> studentlist = studentService.getAllList(student);
		request.setAttribute("studentlist", studentlist);
		return new ModelAndView("student/list");
	}
}