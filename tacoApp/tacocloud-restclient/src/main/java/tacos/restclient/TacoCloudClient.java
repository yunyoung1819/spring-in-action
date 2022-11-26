package tacos.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tacos.Ingredient;

import java.net.URI;
import java.util.Collection;

@Service
@Slf4j
public class TacoCloudClient {

	private RestTemplate rest;
	private Traverson traverson;

	public TacoCloudClient(RestTemplate rest, Traverson traverson) {
		this.rest = rest;
		this.traverson = traverson;
	}

	public Ingredient getIngredientById(String ingredientId) {
		return rest.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
	}

//	public Ingredient getIngredientById(String ingredientId) {
//		Map<String, String> urlVariables = new HashMap<>();
//		urlVariables.put("id", ingredientId);
//		return rest.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, urlVariables);
//	}

//	public Ingredient getIngredientById(String ingredientId) {
//		Map<String, String> urlVariables = new HashMap<>();
//		urlVariables.put("id", ingredientId);
//		URI url = UriComponentsBuilder
//				.fromHttpUrl("http://localhost:8080/ingredients/{id}")
//				.build(urlVariables);
//		return rest.getForObject(url, Ingredient.class);
//	}

	public void updateIngredient(Ingredient ingredient) {
		rest.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
	}

	public void deleteIngredient(Ingredient ingredient) {
		rest.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
	}

	public Ingredient createIngredient(Ingredient ingredient) {
		return rest.postForObject("http://localhost:8080/ingredients", ingredient, Ingredient.class);
	}

//	public URI createIngredient(Ingredient ingredient) {
//		return rest.postForLocation("http://localhost:8080/ingredients", ingredient);
//	}

//	public Ingredient createIngredient(Ingredient ingredient) {
//		ResponseEntity<Ingredient> responseEntity =
//				rest.postForEntity("http://localhost:8080/ingredients",
//						ingredient,
//						Ingredient.class);
//
//		log.info("New resource created at " + responseEntity.getHeaders().getLocation());
//
//		return responseEntity.getBody();
//	}

	ParameterizedTypeReference<Resources<Ingredient>> ingredientType =
			new ParameterizedTypeReference<Resources<Ingredient>>() {};

	Resources<Ingredient> ingredientRes =
			traverson
					.follow("ingredients")
					.toObject(ingredientType);

	Collection<Ingredient> ingredients = ingredientRes.getContent();
}
