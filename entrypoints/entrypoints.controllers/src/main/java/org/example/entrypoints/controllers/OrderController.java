package org.example.entrypoints.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.example.usecases.OrderService;
import org.example.usecases.dto.OrderCreateDto;
import org.example.usecases.dto.OrderGetDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping("/orders/{id}")
  public ResponseEntity<OrderGetDto> getById(@PathVariable("id") int id) {
    OrderGetDto orderDto = orderService.getById(id);
    return ResponseEntity.ok(orderDto);
  }

  @PostMapping("/orders")
  public ResponseEntity<OrderGetDto> save(@RequestBody OrderCreateDto orderDto) {
    OrderGetDto createdOrder = orderService.update(orderDto);
    return ResponseEntity.ok(createdOrder);
  }

  @GetMapping(value = "/orders/csv", produces = "text/csv")
  public ResponseEntity<byte[]> exportCsv() {
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"orders.csv\"")
        .body(orderService.exportCsv());
  }

  @PostMapping(value = "/orders/email")
  public CompletableFuture<ResponseEntity<?>> sendEmail(@RequestParam("to") @NotBlank @Valid List<String> to) {
    return orderService.emailOrders(to).thenApply(v -> ResponseEntity.noContent().build());
  }

  @GetMapping(value = "/customers/{customerId}/orders/last5")
  public ResponseEntity<List<OrderGetDto>> last5OrdersForCustomer(@PathVariable("customerId") int customerId) {
    return ResponseEntity.ok(orderService.last5Orders(customerId));
  }
}
