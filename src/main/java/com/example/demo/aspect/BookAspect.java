package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.AopInvocationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.demo.annotation.MyTransactional;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class BookAspect {

	private final TransactionTemplate tx;

	@Pointcut("@annotation(myTransactional)")
	public void callAt(MyTransactional myTransactional) {
	}

	@Around(value = "callAt(myTransactional)", argNames = "pjp,myTransactional")
	public Object around(ProceedingJoinPoint pjp, MyTransactional myTransactional) {
		return tx.execute(ts -> {
			try {
				return pjp.proceed();
			}
			catch (Throwable e) {
				throw new AopInvocationException(e.getMessage(), e);
			}
		});
	}

}
