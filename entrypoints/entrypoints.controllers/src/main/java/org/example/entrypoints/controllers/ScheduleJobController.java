package org.example.entrypoints.controllers;

import lombok.RequiredArgsConstructor;
import org.example.usecases.ExportOrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleJobController {

  private final ExportOrdersService exportOrdersService;

  @PutMapping("/orders/export/email")
  public ResponseEntity<Void> scheduleExportOrdersAndSendEmail() {
    exportOrdersService.exportOrdersToEmail();
    return ResponseEntity.noContent().build();
  }
}
