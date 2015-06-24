package com.lqbw.operation_log.enumeration;
/**
 * 业务类型
 * UNITREGISTER 单位注册  （提交、审核退回、审核通过、修改邮箱、停用账号、启用账号）
 * ISSUEREPORT 课题申报（创建申报课题、提交管理员、管理员退回，提交水办、水办退回、通过形式审查）
 * UNITLOGIN 单位登录
 * USERLOGIN 填报用户登录
 * SHUIBANLOGIN 水办用户登录
 * NEXTUSER 二级用户（增加、修改、删除）
 * @author issuser
 *
 */
public enum LogType {
	SAVE,UPDATE,DELETE,SYSUSERSAVE,STUDENTSAVE,STUDENTUPDATE;
}
