package org.example.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity(name = "orders")
@Accessors(chain = true)
public class Order {

  @Id
  private Integer id;
  private String name;
}
