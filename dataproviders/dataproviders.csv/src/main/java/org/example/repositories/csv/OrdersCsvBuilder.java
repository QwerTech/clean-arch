package org.example.repositories.csv;

import java.util.stream.Stream;
import org.example.entities.Order;

public interface OrdersCsvBuilder {

  byte[] buildOrdersCsv(Stream<Order> records);
}
