package org.example.quartz.config;

import org.example.quartz.job.ExportOrdersJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

@Configuration
public class QuartzConfig {

  @Bean
  public JobDetailFactoryBean exportOrdersJob() {
    JobDetailFactoryBean job = new JobDetailFactoryBean();
    job.setJobClass(ExportOrdersJob.class);
    job.setDescription("Export orders to email quartz job");
    job.setDurability(true);
    return job;
  }
}
