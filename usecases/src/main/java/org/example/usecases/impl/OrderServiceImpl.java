package org.example.usecases.impl;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.entities.Order;
import org.example.repositories.OrderRepository;
import org.example.usecases.OrderCreateDto;
import org.example.usecases.OrderGetDto;
import org.example.usecases.OrderService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public OrderGetDto getById(int id) {
    Optional<Order> order = orderRepository.findById(id);
    if (!order.isPresent()) {
      throw new EntityNotFoundException(String.format("Order with id=%d not found", id));
    }
    return new OrderGetDto().setId(order.get().getId()).setName(order.get().getName());
  }

  @Override
  public OrderGetDto update(OrderCreateDto order) {
    Order savedOrder = orderRepository.save(new Order().setName(order.getName()));
    return new OrderGetDto().setId(savedOrder.getId()).setName(savedOrder.getName());
  }
}
