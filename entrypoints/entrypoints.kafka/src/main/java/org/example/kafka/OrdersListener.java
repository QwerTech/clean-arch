package org.example.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.usecases.OrderCreateDto;
import org.example.usecases.OrderGetDto;
import org.example.usecases.OrderService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.kafka.annotation.KafkaBootstrapConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnBean(KafkaBootstrapConfiguration.class)
public class OrdersListener {

  private final OrderService orderService;

  @KafkaListener(topics = "orders-creation")
  public void kafkaOrderCreation(OrderCreateDto order) {
    OrderGetDto createdOrder = orderService.update(order);
    log.info("Order created via Kafka {}", createdOrder);
  }
}
