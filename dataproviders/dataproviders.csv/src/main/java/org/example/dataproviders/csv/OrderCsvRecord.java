package org.example.dataproviders.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.example.entities.Order;

@Data
@Accessors(chain = true)
public class OrderCsvRecord {

  @CsvBindByName
  private Integer id;
  @CsvBindByName
  private String name;

  public static OrderCsvRecord from(Order order) {
    return new OrderCsvRecord()
        .setId(order.getId())
        .setName(order.getName());
  }
}
