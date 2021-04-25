package org.example.usecases.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dataproviders.email.EmailMessage;
import org.example.dataproviders.email.EmailSender;
import org.example.entities.Order;
import org.example.repositories.OrderRepository;
import org.example.repositories.csv.OrdersCsvBuilder;
import org.example.usecases.OrderCreateDto;
import org.example.usecases.OrderGetDto;
import org.example.usecases.OrderService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final OrdersCsvBuilder ordersCsvBuilder;
  private final EmailSender emailSender;

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

  @Override
  public byte[] exportCsv() {
    Stream<Order> recordsStream = orderRepository.findAll().stream();
    return ordersCsvBuilder.buildOrdersCsv(recordsStream);
  }

  @Override
  public CompletableFuture<Void> emailOrders(List<String> to) {
    HashMap<String, byte[]> attachments = new HashMap<String, byte[]>() {{
      put("orders.csv", exportCsv());
    }};
    return emailSender.send(new EmailMessage("Orders", "Orders are in the attachment", to, attachments));
  }
}
