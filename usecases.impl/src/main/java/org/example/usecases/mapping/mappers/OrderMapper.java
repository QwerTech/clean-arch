package org.example.usecases.mapping.mappers;

import lombok.NonNull;
import org.example.entities.Customer;
import org.example.entities.Order;
import org.example.usecases.dto.OrderCreateDto;
import org.example.usecases.dto.OrderGetDto;
import org.example.usecases.mapping.CentralConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralConfig.class)
public interface OrderMapper {

  @Mapping(source = "customer.id", target = "customerId")
  OrderGetDto orderToOrderGetDto(Order order);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "name", source = "order.name")
  @Mapping(target = "status", constant = "CREATED")
  @Mapping(target = "creationDateTime", expression = "java(OffsetDateTime.now())")
  Order orderCreateDtoAndCustomerToOrder(@NonNull OrderCreateDto order, Customer customer);
}
