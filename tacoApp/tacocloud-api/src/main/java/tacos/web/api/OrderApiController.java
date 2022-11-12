package tacos.web.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Order;
import tacos.data.OrderRepository;

@RestController
@RequestMapping(path="/orders",
                produces="application/json")
@CrossOrigin(origins="*")
public class OrderApiController {

  private OrderRepository repo;

  public OrderApiController(OrderRepository repo) {
    this.repo = repo;
  }
  
  @GetMapping(produces="application/json")
  public Iterable<Order> allOrders() {
    return repo.findAll();
  }
  
  @PostMapping(consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Order postOrder(@RequestBody Order order) {
    return repo.save(order);
  }


}
