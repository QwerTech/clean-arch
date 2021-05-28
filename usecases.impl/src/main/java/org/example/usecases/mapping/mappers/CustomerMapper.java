package org.example.usecases.mapping.mappers;

import org.example.entities.Customer;
import org.example.usecases.dto.CustomerCreateDto;
import org.example.usecases.dto.CustomerGetDto;
import org.example.usecases.mapping.CentralConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralConfig.class)
public interface CustomerMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "orders", ignore = true)
  Customer customerCreateToToCustomer(CustomerCreateDto customer);

  CustomerGetDto customerToCustomerGetDto(Customer customer);
}
