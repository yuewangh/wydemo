package com.lqbw.service.student.impl;

import java.util.List;
import com.lqbw.model.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lqbw.dao.student.StudentDao;
import com.lqbw.service.student.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	//新增
	@Override
	public int insert(Student student){
		return studentDao.insert(student);
	}

	//修改
	@Override
	public int update(Student student){
		return studentDao.update(student);
	}

	//删除
	@Override
	public int delete(String id){
		return studentDao.delete(id);
	}

	//根据Id查询单条记录
	@Override
	public Student getById(String id){
		return studentDao.getById(id);
	}

	//分页查询列表
	@Override
	public List<Student> getAllList(Student student){
		return studentDao.getAllList(student);
	}

	//分页查询数量
	@Override
	public int getCount(Student student){
		return studentDao.getCount(student);
	}

}