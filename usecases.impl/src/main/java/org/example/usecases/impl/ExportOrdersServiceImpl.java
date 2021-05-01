package org.example.usecases.impl;

import lombok.RequiredArgsConstructor;
import org.example.quartz.service.BackgroundJobService;
import org.example.usecases.ExportOrdersService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportOrdersServiceImpl implements ExportOrdersService {

  private final BackgroundJobService backgroundJobService;

  @Override
  public void exportOrdersToEmail() {
    backgroundJobService.fireExportOrdersJob();
  }
}
