package org.example.entities;


import javax.persistence.Entity;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity(name = "products")
@Accessors(chain = true)
public class Product extends BaseEntity {

  private String code;
  private String name;
}
