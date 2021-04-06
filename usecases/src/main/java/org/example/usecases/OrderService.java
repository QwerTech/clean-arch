package org.example.usecases;


public interface OrderService {

  OrderGetDto getById(int id);

  OrderGetDto update(OrderCreateDto order);

  byte[] exportCsv();
}
