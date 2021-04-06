package org.example.repositories.csv;

import java.util.stream.Stream;

public interface OrdersCsvBuilder {

  byte[] buildOrdersCsv(Stream<OrderCsvRecord> records);
}
