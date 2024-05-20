package ics.Interceptors;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
public class DefaultInterceptor {
    private static final Logger logger = Logger.getLogger(DefaultInterceptor.class.getName());

    @AroundInvoke
    public Object defaultLogg(InvocationContext iCtx) throws Exception {
        System.out.println("*** DefaultInterceptor: " + iCtx.getMethod().getName());

        // Log method entry
       // logger.info("Entering method: " + iCtx.getMethod().getName() + " in class " + iCtx.getTarget().getClass().getName());
        
        long startTime = System.currentTimeMillis();
        
        try {
            // Proceed with the method invocation
            return iCtx.proceed();
        } catch (Exception e) {
            // Log any exception thrown
            logger.severe("Exception in method: " + iCtx.getMethod().getName() + " in class " + iCtx.getTarget().getClass().getName() + " - " + e.getMessage());
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            // Log method exit and execution time
            logger.info("Exiting method: " + iCtx.getMethod().getName() + " in class " + iCtx.getTarget().getClass().getName() + " - Execution time: " + (endTime - startTime) + " ms");
        }
    }
}
