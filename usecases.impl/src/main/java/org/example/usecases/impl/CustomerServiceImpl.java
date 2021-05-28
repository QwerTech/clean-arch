package org.example.usecases.impl;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dataproviders.repositories.CustomerRepository;
import org.example.entities.Customer;
import org.example.usecases.CustomerService;
import org.example.usecases.dto.CustomerCreateDto;
import org.example.usecases.dto.CustomerGetDto;
import org.example.usecases.mapping.mappers.CustomerMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
class CustomerServiceImpl implements CustomerService {


  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  @Override
  public CustomerGetDto getById(int id) {
    Optional<Customer> customer = customerRepository.findById(id);
    if (!customer.isPresent()) {
      throw new EntityNotFoundException(String.format("Customer with id=%d not found", id));
    }
    return customerMapper.customerToCustomerGetDto(customer.get());
  }

  @Override
  public CustomerGetDto update(CustomerCreateDto customer) {
    Customer savedCustomer = customerRepository.save(customerMapper.customerCreateToToCustomer(customer));
    return customerMapper.customerToCustomerGetDto(savedCustomer);
  }

}
