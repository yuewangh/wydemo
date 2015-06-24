package com.lqbw.dao.operationLog;

import java.util.List;

import com.lqbw.operation_log.annotation.mapping.OperationLog;

public interface OperationLogDAO {
	
	public void saveLog(OperationLog log);
	
	public void updateLog(OperationLog log);
	
	public List<OperationLog> findBybId(String bId);
}
