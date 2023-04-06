package com.odth.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@Aspect
@Slf4j
public class AccessLogAOP {

	@Pointcut("within(com.odth..*Controller) && execution(public * *(..))")
	public void accessLogPointCut() {};	

	@Pointcut("accessLogPointCut() && @annotation(requestMapping)")
	public void accessLogRequestPointCut(RequestMapping requestMapping) {};		

	@Pointcut("accessLogPointCut() && @annotation(requestMapping)")
	public void accessLogPostPointCut(PostMapping requestMapping) {};	
	
	@AfterReturning(value="accessLogRequestPointCut(requestMapping)")
	public void saveRequestMappingLog(JoinPoint jpoint, RequestMapping requestMapping) {
		String method = getMethod(requestMapping);
		
		saveNormalLog(jpoint, method);
	}
	
	@AfterReturning(value="accessLogPostPointCut(requestMapping)")
	public void savePostMappingLog(JoinPoint jpoint, PostMapping requestMapping) {
		
		saveNormalLog(jpoint, RequestMethod.POST.name());
	}
	
	@AfterThrowing(value="accessLogRequestPointCut(requestMapping)", throwing = "ex")
	public void saveRequestMappingExLog(JoinPoint jpoint, RequestMapping requestMapping, Exception ex) {
		String url = getUrl(requestMapping);
		String method = getMethod(requestMapping);
		
		saveErrorLog(jpoint, url, method, ex);		
	}
	
	@AfterThrowing(value="accessLogPostPointCut(requestMapping)", throwing = "ex")
	public void savePostMappingExLog(JoinPoint jpoint, PostMapping requestMapping, Exception ex) {
		String url = getUrl(requestMapping);
		
		saveErrorLog(jpoint, url, RequestMethod.POST.name(), ex);
	}
	
	
	private void saveNormalLog(JoinPoint jpoint, String method) {
		String serviceName = jpoint.getSignature().getDeclaringTypeName();
		String methodName = jpoint.getSignature().getName();

		
		log.debug("Save an access log for the call: "+serviceName+"."+methodName+" with method - "+method);

		//systHistService.insertPrgmUseHist(histVO);
	}
	
	private void saveErrorLog(JoinPoint jpoint, String url, String method, Exception ex) {
		String serviceName = jpoint.getSignature().getDeclaringTypeName();
		String methodName = jpoint.getSignature().getName();

		log.debug("Save an error log for the call: "+serviceName+"."+methodName+" with method - "+method);

		//systHistService.insertPrgmErrHist(histVO);
	}
	
	private String getMethod(RequestMapping requestMapping) {
		RequestMethod[] methodList = requestMapping.method();
		String method = null;
		
		if(methodList.length > 0) {
			method = methodList[0].name();
		}
		
		return method;
	}
	
	private String getUrl(RequestMapping requestMapping) {
		return getUrl(requestMapping.value());			
	}
	
	private String getUrl(PostMapping requestMapping) {
		return getUrl(requestMapping.value());		
	}	
	
	private String getUrl(String[] urlList) {
		return urlList.length > 0 ? urlList[0] : null;	
	}


}
