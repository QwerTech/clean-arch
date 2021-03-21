package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.usecases.OrderCreateDto;
import org.example.usecases.OrderGetDto;
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
  public ResponseEntity<OrderGetDto> getById(@PathVariable("id") int id) {
    OrderGetDto orderDto = orderService.getById(id);
    return ResponseEntity.ok(orderDto);
  }

  @PostMapping("/orders")
  public ResponseEntity<OrderGetDto> save(@RequestBody OrderCreateDto orderDto) {
    OrderGetDto createdOrder = orderService.update(orderDto);
    return ResponseEntity.ok(createdOrder);
  }
}
