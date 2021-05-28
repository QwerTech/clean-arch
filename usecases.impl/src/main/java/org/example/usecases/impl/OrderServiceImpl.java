package org.example.usecases.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dataproviders.csv.OrdersCsvBuilder;
import org.example.dataproviders.email.EmailMessage;
import org.example.dataproviders.email.EmailSender;
import org.example.dataproviders.repositories.CustomerRepository;
import org.example.dataproviders.repositories.OrderRepository;
import org.example.entities.Customer;
import org.example.entities.Order;
import org.example.usecases.OrderService;
import org.example.usecases.dto.OrderCreateDto;
import org.example.usecases.dto.OrderGetDto;
import org.example.usecases.mapping.mappers.OrderMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;
  private final OrdersCsvBuilder ordersCsvBuilder;
  private final EmailSender emailSender;
  private final OrderMapper orderMapper;

  @Override
  public OrderGetDto getById(int id) {
    Optional<Order> order = orderRepository.findById(id);
    if (!order.isPresent()) {
      throw new EntityNotFoundException(String.format("Order with id=%d not found", id));
    }
    return orderMapper.orderToOrderGetDto(order.get());
  }

  @Override
  public OrderGetDto update(OrderCreateDto order) {
    Customer customer = null;
    if (order.getCustomerId() != null) {
      customer = customerRepository.getOne(order.getCustomerId());
    }
    Order orderEntity = orderMapper.orderCreateDtoAndCustomerToOrder(order, customer);
    orderRepository.save(orderEntity);
    return orderMapper.orderToOrderGetDto(orderEntity);
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

  @Override
  public List<OrderGetDto> last5Orders(int customerId) {
    Objects.requireNonNull(customerRepository.getOne(customerId));
    return orderRepository.getTop5ByCustomerIdOrderByCreationDateTimeDesc(customerId).stream()
        .map(orderMapper::orderToOrderGetDto)
        .collect(Collectors.toList());
  }
}
