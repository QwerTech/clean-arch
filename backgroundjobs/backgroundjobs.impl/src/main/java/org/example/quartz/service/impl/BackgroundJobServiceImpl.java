package org.example.quartz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.quartz.service.BackgroundJobService;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BackgroundJobServiceImpl implements BackgroundJobService {

  private final Scheduler scheduler;
  private final JobDetail exportOrdersJob;

  @Override
  @SneakyThrows
  public void fireExportOrdersJob() {
    scheduler.addJob(exportOrdersJob, true);
    scheduler.triggerJob(exportOrdersJob.getKey());
  }
}
