package com.lqbw.model.student;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.lqbw.model.BaseModel;
public class Student extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1971367981280526397L;
	private  String id;//主键ID
	@Size(max=6,min=1,message="姓名必须为1-6位！")
	private  String name;//姓名
	private  String sex;//性别
	@NotEmpty(message="姓名不能为空！")
	private  String xueli;//学历
	@Size(max=11, min=11,message="电话必须为11位整数") 
	private  String phone;//电话
	@NotEmpty(message="身份证号不能为空") 
	private  String id_code;//身份证号
	@Length(min=5, max=20, message="家长姓名长度必须在5-20之间")
	private  String father_name;//家长姓名
	private  String father_phone;//家长电话
	private  String adress;//地址
	private  String state;//状态（未报名，已报名，已入学）
	private  String create_date;//创建时间
	private  String update_date;//最后跟新时间
	private  String create_user;//创建人
	private  String update_user;//最后修改人
	@NotEmpty(message="出生日期不能为空！")
	private  String born_time;//出生日期
	@Pattern(regexp="^\\w{3,8}$")
	private  String qq;//qq号
	private  String gaozhong;//高中
	private  String daxue;//大学
	private  String yixzy;//意向专业
	private  String zxls;//咨询老师
	@Email
	private  String beizhu;//备注

	public void setId(String id){
		this.id=id;
	}

	public String getId(){
		return id;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setSex(String sex){
		this.sex=sex;
	}

	public String getSex(){
		return sex;
	}

	public void setXueli(String xueli){
		this.xueli=xueli;
	}

	public String getXueli(){
		return xueli;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setId_code(String id_code){
		this.id_code=id_code;
	}

	public String getId_code(){
		return id_code;
	}

	public void setFather_name(String father_name){
		this.father_name=father_name;
	}

	public String getFather_name(){
		return father_name;
	}

	public void setFather_phone(String father_phone){
		this.father_phone=father_phone;
	}

	public String getFather_phone(){
		return father_phone;
	}

	public void setAdress(String adress){
		this.adress=adress;
	}

	public String getAdress(){
		return adress;
	}

	public void setState(String state){
		this.state=state;
	}

	public String getState(){
		return state;
	}

	public void setCreate_date(String create_date){
		this.create_date=create_date;
	}

	public String getCreate_date(){
		return create_date;
	}

	public void setUpdate_date(String update_date){
		this.update_date=update_date;
	}

	public String getUpdate_date(){
		return update_date;
	}

	public void setCreate_user(String create_user){
		this.create_user=create_user;
	}

	public String getCreate_user(){
		return create_user;
	}

	public void setUpdate_user(String update_user){
		this.update_user=update_user;
	}

	public String getUpdate_user(){
		return update_user;
	}

	public void setBorn_time(String born_time){
		this.born_time=born_time;
	}

	public String getBorn_time(){
		return born_time;
	}

	public void setQq(String qq){
		this.qq=qq;
	}

	public String getQq(){
		return qq;
	}

	public void setGaozhong(String gaozhong){
		this.gaozhong=gaozhong;
	}

	public String getGaozhong(){
		return gaozhong;
	}

	public void setDaxue(String daxue){
		this.daxue=daxue;
	}

	public String getDaxue(){
		return daxue;
	}

	public void setYixzy(String yixzy){
		this.yixzy=yixzy;
	}

	public String getYixzy(){
		return yixzy;
	}

	public void setZxls(String zxls){
		this.zxls=zxls;
	}

	public String getZxls(){
		return zxls;
	}

	public void setBeizhu(String beizhu){
		this.beizhu=beizhu;
	}

	public String getBeizhu(){
		return beizhu;
	}

}
