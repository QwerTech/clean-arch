package org.example.usecases;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderDto {

  private int id;
  private String name;

}
