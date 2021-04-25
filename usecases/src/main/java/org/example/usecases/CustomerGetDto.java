package org.example.usecases;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerGetDto {

  private Integer id;
  private String name;
}
