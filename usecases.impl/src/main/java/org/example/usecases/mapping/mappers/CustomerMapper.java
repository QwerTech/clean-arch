package org.example.usecases.mapping.mappers;

import org.example.entities.Customer;
import org.example.usecases.dto.CustomerCreateDto;
import org.example.usecases.dto.CustomerGetDto;
import org.example.usecases.mapping.CentralConfig;
import org.mapstruct.Mapper;

@Mapper(config = CentralConfig.class)
public interface CustomerMapper {

  Customer customerCreateToToCustomer(CustomerCreateDto customer);

  CustomerGetDto customerToCustomerGetDto(Customer customer);
}
