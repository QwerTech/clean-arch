package org.example.entities;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity(name = "order_products")
@Accessors(chain = true)
public class OrderProduct extends BaseEntity {

  @ManyToOne(optional = false)
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private Order order;
  @ManyToOne(optional = false)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;
}
