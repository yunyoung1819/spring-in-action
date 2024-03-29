package tacos.web.api;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Ingredient;
import tacos.IngredientUDT;
import tacos.Taco;
import tacos.data.TacoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * 리액티브
 */
public class DesignTacoControllerTest {

	/**
	 * GET 요청 테스트
	 */
	@Test
	public void shoudReturnRecentTacos() {
		// 테스트 데이터를 생성한다
		Taco[] tacos = {
				testTaco(1L), testTaco(2L),
				testTaco(3L), testTaco(4L),
				testTaco(5L), testTaco(6L),
				testTaco(7L), testTaco(8L),
				testTaco(9L), testTaco(10L),
				testTaco(11L), testTaco(12L),
				testTaco(13L), testTaco(14L),
				testTaco(15L), testTaco(16L)};
		Flux<Taco> tacoFlux = Flux.just(tacos);
		TacoRepository tacoRepo = Mockito.mock(TacoRepository.class);
		when(tacoRepo.findAll()).thenReturn(tacoFlux);

		WebTestClient testClient = WebTestClient.bindToController(new DesignTacoController(tacoRepo)).build();
		testClient.get().uri("/design/recent")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$").isNotEmpty()
				.jsonPath("$[0].id").isEqualTo(tacos[0].getId().toString())
				.jsonPath("$[0].name").isEqualTo("Taco 1")
				.jsonPath("$[1].id").isEqualTo(tacos[1].getId().toString())
				.jsonPath("$[1].name").isEqualTo("Taco 2")
				.jsonPath("$[11].id").isEqualTo(tacos[11].getId().toString())
				.jsonPath("$[11].name").isEqualTo("Taco 12")
				.jsonPath("$[12]").doesNotExist();
	}

	private Taco testTaco(Long number) {
		Taco taco = new Taco();
		taco.setId(number);
		taco.setName("Taco " + number);
		List<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(new Ingredient("1", "Ingredient A", Ingredient.Type.WRAP));
		ingredients.add(new Ingredient("2", "Ingredient B", Ingredient.Type.PROTEIN));
		taco.setIngredients(ingredients);
		return taco;
	}

	/**
	 * POST 요청 테스트
	 */
	public void shouldSavedATaco() {
		TacoRepository tacoRepo = Mockito.mock(TacoRepository.class);
		Mono<Taco> unsavedTacoMono = Mono.just(testTaco(null)); // 테스트 데이터를 생성한다

		Taco savedTaco = testTaco(null);
		savedTaco.setId(1L);
		Mono<Taco> savedTacoMono = Mono.just(savedTaco);

		when(tacoRepo.save(any())).thenReturn(savedTacoMono);   // 모의 TacoRepository

		WebTestClient testClient = WebTestClient.bindToController(new DesignTacoController(tacoRepo)).build(); // WebTestClient를 생성한다

		testClient.post()
				.uri("/design")
				.contentType(MediaType.APPLICATION_JSON)
				.body(unsavedTacoMono, Taco.class)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Taco.class)
				.isEqualTo(savedTaco);
	}
}
