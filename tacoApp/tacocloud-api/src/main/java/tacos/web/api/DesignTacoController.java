package tacos.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import tacos.Taco;
import tacos.data.TacoRepository;


@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
  private TacoRepository tacoRepo;

  @Autowired
  EntityLinks entityLinks;

  public DesignTacoController(TacoRepository tacoRepo) {
    this.tacoRepo = tacoRepo;
  }

//  @GetMapping("/recent")
//  public Resources<TacoResource> recentTacos() {
//    PageRequest page = PageRequest.of(
//            0, 12, Sort.by("createdAt").descending());
//    List<Taco> tacos = tacoRepo.findAll(page).getContent();
//
//    List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
//    Resources<TacoResource> recentResources = new Resources<TacoResource>(tacoResources);
//
//    recentResources.add(
//            linkTo(methodOn(DesignTacoController.class).recentTacos())
//                    .withRel("recents"));
//    return recentResources;
//  }

  @GetMapping("/recent")
  public Flux<Taco> recentTacos() {
    return tacoRepo.findAll().take(12);
  }

//  @PostMapping(consumes = "application/json")
//  @ResponseStatus(HttpStatus.CREATED)
//  public Taco postTaco(@RequestBody Taco taco) {
//    return tacoRepo.save(taco);
//  }
}
