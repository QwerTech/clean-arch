package org.example.usecases;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.example.usecases.dto.OrderCreateDto;
import org.example.usecases.dto.OrderGetDto;

public interface OrderService {

  OrderGetDto getById(int id);

  OrderGetDto update(OrderCreateDto order);

  byte[] exportCsv();

  CompletableFuture<Void> emailOrders(List<String> to);

  List<OrderGetDto> last5Orders(int customerId);
}
