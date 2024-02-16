package org.acme;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.jboss.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import jakarta.inject.Inject;

public class MyJob implements Job {

    private static final Logger LOG = Logger.getLogger(MyJob.class);

    @Inject
    MyBean bean;

    @Override
    @Fallback(fallbackMethod = "fallback")
    public void execute(JobExecutionContext context) throws JobExecutionException {
        bean.doSomething();
        LOG.info("JobRunning");

    }

    public void fallback(JobExecutionContext context) {
        LOG.info("falling back");
    }

}
