package org.example.usecases.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderCreateDto {

  private String name;
  private Integer customerId;

}
