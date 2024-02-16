package org.acme;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class App {

    @Inject
    org.quartz.Scheduler quartz;

    void onStart(@Observes StartupEvent event) throws SchedulerException {
        for (int i = 0; i < 1000; i++) {
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger" + i, "myGroup")
                    .startNow()
                    .build();
            JobDetail job = JobBuilder.newJob(MyJob.class)
                    .withIdentity("myJob" + i, "myGroup")
                    .build();
            quartz.scheduleJob(job, trigger);
        }
    }

}
