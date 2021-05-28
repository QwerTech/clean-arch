package org.example.usecases.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerCreateDto {

  private String name;
}
