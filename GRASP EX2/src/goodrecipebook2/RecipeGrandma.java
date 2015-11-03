package goodrecipebook2;

// Polymorphism
public class RecipeGrandma extends Recipe {
	
	
	// Protected variations
	private String grandma_name;

	public RecipeGrandma(String name, RecipeCategory cat, String g_name) {
		super(name, cat);
		grandma_name = g_name;
	}

	// SUPPORT POLYMORPHISM
	public String getSource() {
		return grandma_name;
	}
}