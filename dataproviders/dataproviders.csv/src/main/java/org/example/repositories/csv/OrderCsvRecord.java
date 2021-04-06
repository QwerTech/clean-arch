package org.example.repositories.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderCsvRecord {

  @CsvBindByName
  private Integer id;
  @CsvBindByName
  private String name;
}
