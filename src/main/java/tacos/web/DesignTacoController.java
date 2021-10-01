package tacos.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

	private static final List<Ingredient> INGREDIENTS = Arrays.asList(
			new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
			new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
			new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
			new Ingredient("CARN", "Carnitas", Type.PROTEIN),
			new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
			new Ingredient("LETC", "Lettuce", Type.VEGGIES),
			new Ingredient("CHED", "Cheddar", Type.CHEESE),
			new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
			new Ingredient("SLSA", "Salsa", Type.SAUCE),
			new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);

	private static final Type[] types = Type.values();

	private static List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients
				.stream()
				.filter(i -> i.getType() == type)
				.collect(Collectors.toList())
		;
	}

	private static final Map<String, Object> INGREDIENTS_BY_TYPE = new HashMap<>();
	static {
		for (Type type: types) {
			INGREDIENTS_BY_TYPE.put(type.toString().toLowerCase(), filterByType(INGREDIENTS, type));
		}
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		model.addAttribute("design", new Taco());
		
		model.addAllAttributes(INGREDIENTS_BY_TYPE);
		
		return "design";
	}

	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAllAttributes(INGREDIENTS_BY_TYPE);
			return "design";
		}
		
		log.info("Processing design: {}", design);
		
		return "redirect:/orders/current";
	}
	
}
