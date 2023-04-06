package com.odth.config.aop;

import java.util.Collections;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

import lombok.extern.slf4j.Slf4j;

//@Configuration
//@EnableTransactionManagement
@Slf4j
@Deprecated
public class TransactionAOP {
	private final String POINTCUT_OR_SEP = " || ";
	
	@Bean 
	public UserTransaction userTransaction() throws SystemException { 
		UserTransaction ut = new UserTransactionImp();
		ut.setTransactionTimeout(5000);
		return ut;
	}
	
	@Bean(initMethod = "init", destroyMethod = "close")
	public UserTransactionManager userTransactionManager() {
		UserTransactionManager utm = new UserTransactionManager();
		utm.setForceShutdown(false);
		return utm;
	}
	
	@Bean(name = "jtaTransactionManager")
	public JtaTransactionManager jtaTransactionManager(UserTransaction ut, UserTransactionManager utm) throws SystemException {
		return new JtaTransactionManager(ut, utm);
	}
	
	public TransactionAttributeSource transactionAttributeSource() {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        
        RuleBasedTransactionAttribute newTransactionAttribute = new RuleBasedTransactionAttribute();
        newTransactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        newTransactionAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);       
        
        RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
        transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        transactionAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        
        source.addTransactionalMethod("newTX*", newTransactionAttribute);
        source.addTransactionalMethod("*", transactionAttribute);
        
        return source;
	}	

	@Bean
    public TransactionInterceptor transactionAdvice(@Qualifier("jtaTransactionManager") TransactionManager transactionManager){
		log.info("Initializing a transactionAdvice...★★★★★");
        return new TransactionInterceptor(transactionManager, transactionAttributeSource());
    }
	
	@Bean
	public Advisor transactionAdvisor(@Qualifier("jtaTransactionManager") TransactionManager transactionManager, TransactionInterceptor ti) {
		log.info("Initializing a transactionAdvisor...★★★★★");
		
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(getPointCut());
        return new DefaultPointcutAdvisor(pointcut, ti);
	}
	
	private String getPointCut() {
		StringBuilder sb = new StringBuilder();
		sb.append("execution(* com.oy..service.*Service.*insert*(..))").append(POINTCUT_OR_SEP)
		  .append("execution(* com.oy..service.*Service.*update*(..))").append(POINTCUT_OR_SEP)
		  .append("execution(* com.oy..service.*Service.*delete*(..))").append(POINTCUT_OR_SEP)
		  .append("execution(* com.oy..service.*Service.*save*(..))").append(POINTCUT_OR_SEP)
		  .append("execution(* com.oy..service.*Service.*merge*(..))");
		return sb.toString();
	}
}
