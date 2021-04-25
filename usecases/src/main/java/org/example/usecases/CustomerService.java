package org.example.usecases;

public interface CustomerService {

  CustomerGetDto getById(int id);

  CustomerGetDto update(CustomerCreateDto order);
}
