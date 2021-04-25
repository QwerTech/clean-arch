package org.example.entities;


import java.time.OffsetDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

@Data
@Entity(name = "orders")
@Accessors(chain = true)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  @OneToMany
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private List<OrderProduct> orderProducts;
  @ManyToOne
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  private Customer customer;

  @CreatedDate
  private OffsetDateTime creationDateTime;
}
