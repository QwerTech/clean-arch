package org.example.entrypoints.controllers;

import lombok.RequiredArgsConstructor;
import org.example.usecases.CustomerService;
import org.example.usecases.dto.CustomerCreateDto;
import org.example.usecases.dto.CustomerGetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping("/customers/{id}")
  public ResponseEntity<CustomerGetDto> getById(@PathVariable("id") int id) {
    CustomerGetDto orderDto = customerService.getById(id);
    return ResponseEntity.ok(orderDto);
  }

  @PostMapping("/customers")
  public ResponseEntity<CustomerGetDto> save(@RequestBody CustomerCreateDto orderDto) {
    CustomerGetDto createdOrder = customerService.update(orderDto);
    return ResponseEntity.ok(createdOrder);
  }
}
