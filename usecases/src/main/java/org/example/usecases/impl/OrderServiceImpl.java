package org.example.usecases.impl;

import static java.util.Optional.ofNullable;

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
import org.example.usecases.OrderCreateDto;
import org.example.usecases.OrderGetDto;
import org.example.usecases.OrderService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;
  private final OrdersCsvBuilder ordersCsvBuilder;
  private final EmailSender emailSender;

  @Override
  public OrderGetDto getById(int id) {
    Optional<Order> order = orderRepository.findById(id);
    if (!order.isPresent()) {
      throw new EntityNotFoundException(String.format("Order with id=%d not found", id));
    }
    return mapToOrderGetDto(order.get());
  }

  @Override
  public OrderGetDto update(OrderCreateDto order) {
    Customer customer = null;
    if (order.getCustomerId() != null) {
      customer = customerRepository.getOne(order.getCustomerId());
    }
    Order savedOrder = orderRepository.save(new Order().setName(order.getName()).setCustomer(customer));
    return mapToOrderGetDto(savedOrder);
  }

  private OrderGetDto mapToOrderGetDto(Order order) {
    return new OrderGetDto()
        .setId(order.getId())
        .setName(order.getName())
        .setCustomerId(ofNullable(order.getCustomer()).map(Customer::getId).orElse(null));
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
        .map(this::mapToOrderGetDto)
        .collect(Collectors.toList());
  }
}
