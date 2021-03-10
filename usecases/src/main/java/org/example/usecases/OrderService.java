package org.example.usecases;


public interface OrderService {

  OrderDto getById(int id);
  void update(OrderDto order);
}
