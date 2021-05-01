package org.example.quartz.job;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.usecases.OrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@RequiredArgsConstructor
public class ExportOrdersJob implements Job {

  private final OrderService orderService;

  @Value("app.export.emailRecipient")
  private String emailRecipient;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    log.info("Execution export orders to email job");
    orderService.emailOrders(Collections.singletonList(emailRecipient));
  }
}
