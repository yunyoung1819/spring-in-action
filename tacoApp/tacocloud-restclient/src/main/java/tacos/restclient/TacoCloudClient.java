package tacos.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tacos.Ingredient;

@Service
@Slf4j
public class TacoCloudClient {

	private RestTemplate rest;
	private Traverson traverson;

	public TacoCloudClient(RestTemplate rest, Traverson traverson) {
		this.rest = rest;
		this.traverson = traverson;
	}

	// GET
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

	// PUT
	public void updateIngredient(Ingredient ingredient) {
		rest.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.getId());
	}

	// DELETE
	public void deleteIngredient(Ingredient ingredient) {
		rest.delete("http://localhost:8080/ingredients/{id}", ingredient.getId());
	}
}
