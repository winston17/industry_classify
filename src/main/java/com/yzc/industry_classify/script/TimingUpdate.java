package com.yzc.industry_classify.script;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Classname TimingUpdate
 * @Author lizonghuan
 * @Description 定期更新es和neo4j中的内容
 * @Date-Time 2019/5/29-19:27
 * @Version 1.0
 */

public class TimingUpdate extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException{
        System.out.println(123);
    }
}
