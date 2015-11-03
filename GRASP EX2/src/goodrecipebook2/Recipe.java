package goodrecipebook2;

import java.util.ArrayList;
import java.io.Serializable;

public class Recipe implements Serializable, Comparable<Recipe> {
	// POLYMORPHISM
	// Protected variations
	private String recipename;
	private RecipeCategory category = null;
	private ArrayList<Ingredient> ingredients = null;
	private ArrayList<Instruction> instructions = null;

	public Recipe(String name, RecipeCategory cat) {
		recipename = name;
		category = cat;
	}

	public String getRecipeName() {
		return recipename;
	}

	public RecipeCategory getRecipeCategory() {
		return category;
	}

	// ingredients
	public void addIngredient(double u, UnitOfMeasure uom, String ingrd) {
		Ingredient ing = new Ingredient(u, uom, ingrd);
		if (ingredients == null)
			ingredients = new ArrayList<Ingredient>();
		ingredients.add(ing);
	}

	public int numIngredients() {
		return ingredients.size();
	}

	// instructions
	public void addInstruction(String instr) {
		Instruction inst = new Instruction(instr);
		if (instructions == null)
			instructions = new ArrayList<Instruction>();
		instructions.add(inst);
	}

	public int numInstructions() {
		return instructions.size();
	}

	// SUPPORT POLYMORPHISM / INDIRECTION (decouple sorting from Recipe)
	public int compareTo(Recipe otherrecipe) {
		// comparing two recipes means comparing their names
		return (recipename.compareToIgnoreCase(otherrecipe.getRecipeName()));
	}

	public String getSource() {
		return "Unknown";
	}

	public String toString() {
		String s = "";
		s += s.format("%s\n", recipename);
		s += s.format("Category: %s\n", category.getCategoryName());
		// POLYMORPHISM
		s += s.format("Recipe source: %s\n", getSource());
		s += s.format("\nINGREDIENTS\n");
		for (int i = 0; i < numIngredients(); i++) {
			Ingredient ing = ingredients.get(i);
			if (ing.getUOM() == null)
				s += s.format("%.2f %s\n", ing.getUnit(), ing.getIngredient());
			else
				s += s.format("%.2f %s %s\n", ing.getUnit(), ing.getUOM(), ing.getIngredient());
		}
		s += s.format("\nINSTRUCTIONS\n");
		for (int i = 0; i < numInstructions(); i++) {
			Instruction inst = instructions.get(i);
			s += s.format("%s\n", inst.getInstruction());
		}
		return s;
	}
}