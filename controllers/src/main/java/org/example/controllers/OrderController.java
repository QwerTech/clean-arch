package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.usecases.OrderDto;
import org.example.usecases.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping("/orders/{id}")
  public ResponseEntity<OrderDto> getById(@PathVariable("id") int id) {
    OrderDto orderDto = orderService.getById(id);
    return ResponseEntity.ok(orderDto);
  }

  @PostMapping("/orders/{id}")
  public ResponseEntity<?> save(@PathVariable("id") int id, @RequestBody OrderDto orderDto) {
    orderService.update(orderDto);
    return ResponseEntity.noContent().build();
  }
}
