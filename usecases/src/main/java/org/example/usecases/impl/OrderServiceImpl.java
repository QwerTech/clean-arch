package org.example.usecases.impl;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.entities.Order;
import org.example.repositories.OrderRepository;
import org.example.usecases.OrderDto;
import org.example.usecases.OrderService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public OrderDto getById(int id) {
    Optional<Order> order = orderRepository.findById(id);
    if (!order.isPresent()) {
      throw new EntityNotFoundException();
    }
    return new OrderDto().setId(order.get().getId()).setName(order.get().getName());
  }

  @Override
  public void update(OrderDto order) {
    orderRepository.save(new Order().setId(order.getId()).setName(order.getName()));
  }
}
