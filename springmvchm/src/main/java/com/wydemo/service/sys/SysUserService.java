package com.lqbw.service.sys;

import java.util.List;

import com.lqbw.model.sys.SysUser;

public interface SysUserService {

	//新增
	public int insert(SysUser sysUser);

	//修改
	public int update(SysUser sysUser);

	//删除
	public int delete(String id);

	//根据Id查询单条记录
	public SysUser getById(String id);
	
	//根据Id查询单条记录
	public SysUser getByName(String user_name);

	//分页查询列表
	public List<SysUser> getAllList(SysUser sysUser);

	//分页查询数量
	public int getCount(SysUser sysUser);

}