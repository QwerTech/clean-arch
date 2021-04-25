package org.example.usecases.impl;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dataproviders.repositories.CustomerRepository;
import org.example.entities.Customer;
import org.example.usecases.CustomerCreateDto;
import org.example.usecases.CustomerGetDto;
import org.example.usecases.CustomerService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


  private final CustomerRepository customerRepository;

  @Override
  public CustomerGetDto getById(int id) {
    Optional<Customer> customer = customerRepository.findById(id);
    if (!customer.isPresent()) {
      throw new EntityNotFoundException(String.format("Customer with id=%d not found", id));
    }
    return mapToCustomerGetDto(customer.get());
  }

  private CustomerGetDto mapToCustomerGetDto(Customer customer) {
    return new CustomerGetDto().setId(customer.getId()).setName(customer.getName());
  }

  @Override
  public CustomerGetDto update(CustomerCreateDto customer) {
    Customer savedCustomer = customerRepository.save(new Customer().setName(customer.getName()));
    return mapToCustomerGetDto(savedCustomer);
  }
}
