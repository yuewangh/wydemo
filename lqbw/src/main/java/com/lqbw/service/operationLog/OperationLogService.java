package com.lqbw.service.operationLog;

import java.util.List;

import com.lqbw.operation_log.annotation.mapping.OperationLog;

public interface OperationLogService {
	
	public List<OperationLog> findBybId(String bId);
	
}
