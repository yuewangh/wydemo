package com.lqbw.model.sys;

import com.lqbw.model.BaseModel;
public class SysUser extends BaseModel{

	/**
	 * 系统用户
	 */
	private static final long serialVersionUID = 3043460721121337476L;
	private  String id;//主键ID
	private  String user_name;//用户名
	private  String password;//密码
	private  String name;//真实姓名
	private  String phone;//电话
	private  String create_time;//创建时间
	private  String type;//1管理员，2用户

	public void setId(String id){
		this.id=id;
	}

	public String getId(){
		return id;
	}

	public void setUser_name(String user_name){
		this.user_name=user_name;
	}

	public String getUser_name(){
		return user_name;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public String getPassword(){
		return password;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setCreate_time(String create_time){
		this.create_time=create_time;
	}

	public String getCreate_time(){
		return create_time;
	}

	public void setType(String type){
		this.type=type;
	}

	public String getType(){
		return type;
	}

}
