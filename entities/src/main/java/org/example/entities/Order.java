package org.example.entities;


import java.time.OffsetDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.example.entities.enums.OrderStatus;

@Data
@Entity(name = "orders")
@Accessors(chain = true)
public class Order extends BaseEntity {

  private String name;
  @OneToMany
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private List<OrderProduct> orderProducts;
  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  private Customer customer;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  private OffsetDateTime creationDateTime;
}
