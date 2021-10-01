package tacos;

import lombok.Data;

@Data
public class Ingredient {
	private final String id;
	private final String name;
	private final Type type;
	
	public static enum Type {
		WRAP,	// лаваш ???
		PROTEIN,// мясо и т.д.
		VEGGIES,// овощи
		CHEESE, // сыры
		SAUCE,	// соус
	}
}
