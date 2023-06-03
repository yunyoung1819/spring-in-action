package tacos.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import tacos.Order;
import tacos.User;

public interface OrderRepository 
         extends ReactiveCassandraRepository<Order, UUID> {

  List<Order> findByUserOrderByPlacedAtDesc(
          User user, Pageable pageable);

}
