package com.yzc.industry_classify.config;

import com.yzc.industry_classify.script.TimingUpdate;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname QuartzConfig
 * @Author lizonghuan
 * @Description quartz定时配置类
 * @Date-Time 2019/5/29-19:32
 * @Version 1.0
 */
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail teatQuartzDetail(){
        return JobBuilder.newJob(TimingUpdate.class).withIdentity("timingUpdate")
                .storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.
                simpleSchedule().withIntervalInSeconds(86400).repeatForever();

        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail())
                .withIdentity("timing")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
