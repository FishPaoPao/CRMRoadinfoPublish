package com.bytd.crm.roadinfo.core;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bytd
 */
public class RoadinfoPublishJobScheduler {

    private static Logger logger = Logger.getLogger(RoadinfoPublishJobScheduler.class);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    static{
        PropertiesLoader.load();
    }

    static class RoadInfoPublishJobListener implements JobListener{

        private static final String LISTENER_NAME = "RoadinfoPublish";
        @Override
        public String getName() {
            return LISTENER_NAME;
        }

        @Override
        public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
            String jobName = jobExecutionContext.getJobDetail().getKey().getName();
            logger.info("Job: " + jobName + " Is Going... At " + simpleDateFormat.format(new Date()));
        }

        @Override
        public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        }

        @Override
        public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
            String jobName = jobExecutionContext.getJobDetail().getKey().getName();
            logger.info("Job: " + jobName + " Has Completed! At" + simpleDateFormat.format(new Date()));
        }
    }

    public static void main(String[] args) {
        logger.error("RoadinfoPublishJobScheduler start...");
        //创建一个JobDetail实例，将该实例与RoadInfoPublishJob类绑定
        JobDetail jobDetail = JobBuilder.newJob(RoadInfoPublishJob.class)
                .withIdentity("roadInfoPublishJob")
                .build();

        //创建一个Trigger实例，定义该Job执行计划
        //该Job执行计划由配置文件RoadInfoPublish.xml配置
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() + PropertiesLoader.INITDELAY * 1000);
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("roadInfoPublishJobTrigger")
                .startAt(startDate)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(PropertiesLoader.PERIOD)
                        .withRepeatCount(PropertiesLoader.REPEATCOUNT))
                .build();

        //创建Scheduler实例
        SchedulerFactory sf = new StdSchedulerFactory();
        try {
            Scheduler scheduler = sf.getScheduler();
            scheduler.getListenerManager().addJobListener(new RoadInfoPublishJobListener());
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        logger.error("RoadinfoPublishJobScheduler started");
    }
}
