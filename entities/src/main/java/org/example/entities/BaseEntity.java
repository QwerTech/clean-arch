package org.example.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.domain.Persistable;

@Data
@MappedSuperclass
public class BaseEntity implements Persistable<Integer> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Override
  public boolean isNew() {
    return id == null;
  }
}
