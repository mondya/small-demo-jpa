package com.xhh.smalldemojpa.config;

import com.xhh.smalldemojpa.service.quartz.MyJobFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class QuartzConfiguration {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    @Autowired
    private MyJobFactory myJobFactory;

    //@Bean
    public SchedulerFactoryBean schedulerFactoryBean() {

        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJobFactory(myJobFactory);
        // quartz参数
        Properties prop = new Properties();
        //调度标识名 集群中每一个实例都必须使用相同的名称
        prop.put("org.quartz.scheduler.instanceName", "hiiadmin");
        //调度器实例编号自动生成，每个实例不能不能相同
        prop.put("org.quartz.scheduler.instanceId", "AUTO");

        // 线程池配置
        //实例化ThreadPool时，使用的线程类为SimpleThreadPool（一般使用SimpleThreadPool即可满足几乎所有用户的需求）
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        //并发个数,指定线程数，至少为1（无默认值）(一般设置为1-100之间的的整数合适)
        prop.put("org.quartz.threadPool.threadCount", "10");
        //设置线程的优先级（最大为java.lang.Thread.MAX_PRIORITY 10，最小为Thread.MIN_PRIORITY 1，默认为5）
        prop.put("org.quartz.threadPool.threadPriority", "5");

        // 数据库方式 JobStore配置
        prop.put("org.quartz.jobStore.class", "org.springframework.scheduling.quartz.LocalDataSourceJobStore");
        //持久化方式配置数据驱动，ORACLE数据
        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");

        //开启分布式部署，集群
        prop.put("org.quartz.jobStore.isClustered", "true");

        //分布式节点有效性检查时间间隔，单位：毫秒,默认值是15000
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "false");

        //容许的最大作业延长时间,最大能忍受的触发超时时间，如果超过则认为"失误",不敢再内存中还是数据中都要配置
        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
        //quartz相关数据表前缀名
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        factoryBean.setQuartzProperties(prop);

        //调度标识名 集群中每一个实例都必须使用相同的名称
        factoryBean.setSchedulerName("hiiadmin");

        factoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        // 可选，QuartzScheduler
        // 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factoryBean.setOverwriteExistingJobs(true);
        // 设置自动启动，默认为true
        factoryBean.setAutoStartup(true);

        return factoryBean;
    }

    //@Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }
}
