package tacos.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tacos.Ingredient;

class IngredientResourceAssembler extends ResourceAssemblerSupport<Ingredient, IngredientResource> {

	public IngredientResourceAssembler() {
		super(IngredientController.class, IngredientResource.class);
	}

	@Override
	public IngredientResource toResource(Ingredient ingredient) {
		return null;
	}
}
