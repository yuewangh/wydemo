package com.lqbw.service.sys.impl;

import java.util.List;

import com.lqbw.model.sys.SysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lqbw.dao.sys.SysUserDao;
import com.lqbw.service.sys.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	//新增
	@Override
	public int insert(SysUser sysUser){
		return sysUserDao.insert(sysUser);
	}

	//修改
	@Override
	public int update(SysUser sysUser){
		return sysUserDao.update(sysUser);
	}

	//删除
	@Override
	public int delete(String id){
		return sysUserDao.delete(id);
	}

	//根据Id查询单条记录
	@Override
	public SysUser getById(String id){
		return sysUserDao.getById(id);
	}

	//分页查询列表
	@Override
	public List<SysUser> getAllList(SysUser sysUser){
		return sysUserDao.getAllList(sysUser);
	}

	//分页查询数量
	@Override
	public int getCount(SysUser sysUser){
		return sysUserDao.getCount(sysUser);
	}

	@Override
	public SysUser getByName(String user_name) {
		return sysUserDao.getByName(user_name);
	}

}