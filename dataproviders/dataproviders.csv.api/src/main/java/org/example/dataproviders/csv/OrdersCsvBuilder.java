package org.example.dataproviders.csv;

import java.util.stream.Stream;
import org.example.entities.Order;

public interface OrdersCsvBuilder {

  byte[] buildOrdersCsv(Stream<Order> records);
}
