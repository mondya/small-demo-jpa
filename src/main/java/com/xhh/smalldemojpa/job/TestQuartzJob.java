package com.xhh.smalldemojpa.job;

import com.xhh.smalldemojpa.dao.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
@Slf4j
@RequiredArgsConstructor
public class TestQuartzJob extends QuartzJobBean {
    
    private final StudentRepository studentRepository;
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("开始执行---");
        log.info(studentRepository.findAll().toString());
    }
}
