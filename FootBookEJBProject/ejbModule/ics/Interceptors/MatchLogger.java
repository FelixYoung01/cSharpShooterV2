package ics.Interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
public class MatchLogger {

	@AroundInvoke
	public Object logMatchMethods(InvocationContext iCtx) throws Exception {
        System.out.println("*********************************");
		System.out.println("Entering method: " + iCtx.getMethod().getName());
		System.out.println("Target Klass: " + iCtx.getTarget().getClass());
		System.out.println("Parameters: " + iCtx.getParameters().length);
		for (int i = 0; i < iCtx.getParameters().length; i++) {
			System.out.println("Parameter: " + iCtx.getParameters()[i].toString());
		}
		System.out.println("*********************************");

		long startTime = System.currentTimeMillis();
		Object result = iCtx.proceed();
		long executionTime = System.currentTimeMillis() - startTime;

		System.out.println("Exiting method: " + iCtx.getMethod().getName() + " in class "
				+ iCtx.getTarget().getClass().getName() + " - Execution time: " + executionTime + " ms");
		System.out.println("*********************************");

		return result;
	}
}
