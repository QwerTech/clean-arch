package org.example.usecases;

import org.example.usecases.dto.CustomerCreateDto;
import org.example.usecases.dto.CustomerGetDto;

public interface CustomerService {

  CustomerGetDto getById(int id);

  CustomerGetDto update(CustomerCreateDto order);
}
