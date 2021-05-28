package org.example.dataproviders.repositories;

import java.util.List;
import org.example.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

  List<Order> getTop5ByCustomerIdOrderByCreationDateTimeDesc(Integer customerId);
}
