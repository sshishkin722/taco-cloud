package tacos;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {
	
	private static final String YOU_MUST_CHOOSE_AT_LEAST_1_INGREDIENT = "You must choose at least 1 ingredient";

	@NotNull
	@Size(min = 5, message="Name must be at least 5 characters long")
	private String name;
	
	@NotNull(message = YOU_MUST_CHOOSE_AT_LEAST_1_INGREDIENT)
	@Size(min = 1, message = YOU_MUST_CHOOSE_AT_LEAST_1_INGREDIENT)
	private List<String> ingredients;
}
