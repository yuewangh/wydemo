package com.lqbw.operation_log.annotation.mapping;

import java.io.Serializable;
import java.util.Date;

import com.lqbw.operation_log.annotation.Log;

public class OperationLog implements Serializable {

	private static final long serialVersionUID = 2600355276769038803L;

	private String userId;
	private Date operationTime;
	private String content;
	private String type;
	private Successed successed;
	private String details;
	private String bId;
	//用于查询
	private String time;
	private String username;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public OperationLog() {
		super();
	}

	public OperationLog(Log log, Successed successed, String details,
			String userId, String bId) {
		super();
		this.successed = successed;
		this.details = details;
		this.content = log.content();
		this.type = log.type().toString();
		this.operationTime = new Date();
		this.userId = userId;
		this.bId = bId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Successed getSuccessed() {
		return successed;
	}

	public void setSuccessed(Successed successed) {
		this.successed = successed;
	}

	public String getDetails() {
		return details;
	}

	public void setDesc(String details) {
		this.details = details;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public enum Successed {
		SUCCESSED, FAILED, ;
	}
}
