package tacos.web.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import reactor.core.publisher.Flux;
import tacos.Taco;
import tacos.data.TacoRepository;

@RepositoryRestController
public class RecentTacosController {

  private TacoRepository tacoRepo;

  public RecentTacosController(TacoRepository tacoRepo) {
    this.tacoRepo = tacoRepo;
  }


  @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
  public Flux<Taco> recentTacos() {
    return tacoRepo.findAll().take(12);
  }


}
