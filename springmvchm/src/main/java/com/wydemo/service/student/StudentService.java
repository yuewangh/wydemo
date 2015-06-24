package com.lqbw.service.student;

import java.util.List;
import com.lqbw.model.student.Student;

public interface StudentService {

	//新增
	public int insert(Student student);

	//修改
	public int update(Student student);

	//删除
	public int delete(String id);

	//根据Id查询单条记录
	public Student getById(String id);

	//分页查询列表
	public List<Student> getAllList(Student student);

	//分页查询数量
	public int getCount(Student student);

}