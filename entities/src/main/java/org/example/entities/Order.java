package org.example.entities;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Order {

  private Integer id;
  private String name;
}
