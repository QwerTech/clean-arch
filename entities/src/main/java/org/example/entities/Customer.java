package org.example.entities;


import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Entity(name = "customers")
@Accessors(chain = true)
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  @OneToMany
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  private List<Order> orders;
}
