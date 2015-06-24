package com.lqbw.operation_log.Aspectj;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.lqbw.dao.operationLog.OperationLogDAO;
import com.lqbw.operation_log.annotation.Log;
import com.lqbw.operation_log.annotation.mapping.OperationLog;
import com.lqbw.operation_log.annotation.mapping.OperationLog.Successed;

/**
 * 记录被{@link com.lqbw.operation_log.annotation.Log}注解标记的方法，所产生的操作日志<br/>
 * 其中在执行方法前获取相应的注解值和方法参数；<br/>
 * 被标记方法执行完成后，保存所产生的日志；<br/>
 * 如果标记方法在执行过程中失败或异常，则记录失败的日志。
 * @author xiajiang
 *
 */
@Aspect
@Component
public class OperationLogAspectj {
	
	private static org.apache.commons.logging.Log logger = LogFactory.getLog(OperationLogAspectj.class);
	private Log logAnn;
	@Autowired
	private OperationLogDAO operationLogDAO;
	private String userId;
	private String bId;
	
	@Pointcut("@annotation(com.lqbw.operation_log.annotation.Log)")
	public void executeLog(){};
	
	@SuppressWarnings("rawtypes")
	@Before(value = "executeLog()")
	public void initOperationLog(JoinPoint jp){
		logger.trace("切面 @Before OperationLogAspectj initOperationLog(@annotation(com.lqbw.operation_log.annotation.Log))");
		String _signatureName = jp.getSignature().getName();
		Class targetClass = jp.getTarget().getClass();
		logger.trace(String.format("Join Point kind : %s", jp.getKind()));
		logger.trace(String.format("Signature declaring type : %s", jp.getSignature().getDeclaringTypeName()));
		logger.trace(String.format("Signature name : %s", _signatureName));
		logger.trace(String.format("Target class : %s", targetClass.getName()));
		logger.trace(String.format("This class : %s", jp.getThis().getClass().getName()));
		
		Object[] args = jp.getArgs();
		getRequestArgs(args);
		
		Method[] targetMethods = targetClass.getDeclaredMethods();
		for (Method _method : targetMethods) {
			if (_method.getName().equalsIgnoreCase(_signatureName)) {
				logAnn = _method.getAnnotation(Log.class);
			}
		}
	}
	
	@AfterReturning(value = "executeLog()", returning = "result")
	public void executeResultMethod(Object result){
		logger.debug("=====>返回结果内容：" + ReflectionToStringBuilder.toString(result, ToStringStyle.MULTI_LINE_STYLE));
		if (result != null) {
			Map<String, Object> tageResult = null;
			if (result instanceof ModelAndView){
				tageResult =( (ModelAndView) result).getModel();
			} else if (result instanceof Map){
				tageResult = (Map<String, Object>) result;
			}
			if (tageResult == null)
				throw new IllegalArgumentException("您执行的方法的返回值类型错误，请返回 java.util.Map 或 org.springframework.web.servlet.ModelAndView类型");
			if(this.bId == null) {
				bId = (String) tageResult.get("bId");
			}
			if (this.userId == null || this.userId.equals(""))
				this.userId = (String) tageResult.get("user_id");
		}
		this.createLog(Successed.SUCCESSED, "");
	}
	
	@AfterThrowing(value = "executeLog()", argNames = "exception", throwing = "exception")
	public void throwProcess(Exception exception){
		logger.trace("执行带有返回值的方法");
		this.bId = "-1";
		this.createLog(Successed.FAILED, exception.getMessage());
	}
	
	private void createLog(Successed successed, String desc){
		if (bId == null)
			throw new IllegalArgumentException("您执行的方法的参数和返回值中都没有bId(business id)参数，导致无法保存日志");
		OperationLog oLog = new OperationLog(logAnn, successed, desc, userId, bId);
		logger.debug("=====>日志内容：" + ReflectionToStringBuilder.toString(oLog, ToStringStyle.MULTI_LINE_STYLE));
		operationLogDAO.saveLog(oLog);
	}

	/**
	 * 从{@link javax.servlet.http.HttpSession}中获取登录用户的id
	 * @param args controller方法的参数
	 */
	private void getRequestArgs(Object[] args) {
		HttpServletRequest req = null;
		if (args.length < 1)
			throw new IllegalArgumentException("您执行的方法没有任何参数，导致无法获取操作用户信息");
		for (Object arg : args) {
			if(arg instanceof HttpServletRequest)
				req = (HttpServletRequest) arg;
		}
		if (req == null)
			throw new IllegalArgumentException("调用@Log的方法参数中，没有HttpServletRequest参数，导致无法获取操作用户信息");
		userId = (String) req.getSession().getAttribute("user_id");
		bId = req.getAttribute("bId") == null ? req.getParameter("bId") : (String) req.getAttribute("bId");
//		if (bId == null || bId.equals(""))
//			throw new IllegalArgumentException("调用@Log的方法，HttpServletRequest参数中没有bId(business id)参数，导致无法获取正在操作的业务信息");
	}
}
