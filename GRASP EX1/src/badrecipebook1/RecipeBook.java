package badrecipebook1;

import java.util.ArrayList;

// Controller / Factory
// High cohesion
public class RecipeBook {
	private ArrayList<RecipeCategory> recipecategories = new ArrayList<RecipeCategory>();
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	public RecipeBook() {
		CreateTempCategories();
		CreateTempRecipes();
	}

	private void CreateTempCategories() {
		// Creator
		recipecategories.add(new RecipeCategory("dessert"));
		recipecategories.add(new RecipeCategory("appetizer"));
	}

	// Information Set by this method
	private void CreateTempRecipes() {
		String rname1 = "Puff Puff";
		String cat1 = "dessert";
		createRecipe(rname1, cat1);
		addIngredient(rname1, 3, UnitOfMeasure.cup, "white flour");
		addIngredient(rname1, 1, UnitOfMeasure.tsp, "salt");
		addIngredient(rname1, 1, UnitOfMeasure.cup, "water");
		addIngredient(rname1, 8, UnitOfMeasure.grms, "yeast");
		addInstruction(rname1, "Combine warm water with salt in bowl");
		addInstruction(rname1, "Add yeast and mix well");
		String rname2 = "Plantain chips";
		String cat2 = "appetizer";
		createRecipe(rname2, cat2);
		addIngredient(rname2, 1.5, UnitOfMeasure.cup, "yogurt");
		addIngredient(rname2, 3, null, "plantains");
		addIngredient(rname2, 1, UnitOfMeasure.cup, "vegetable oil");
		addIngredient(rname2, 0.25, UnitOfMeasure.tsp, "salt");
		addInstruction(rname2, "Heat oil in frying pan");
		addInstruction(rname2, "Cut plantain into thin slices");
		addInstruction(rname2, "Mix in salt");
		addInstruction(rname2, "Fry plantain until crispy brown");
	}

	// categories
	// Creator
	public void addRecipeCategory(String categoryname) {
		recipecategories.add(new RecipeCategory(categoryname));
	}

	private RecipeCategory getRecipeCategory(String name) {
		for (RecipeCategory rcat : recipecategories) {
			if (rcat.getCategoryName().equals(name))
				return rcat;
		}
		return null;
	}

	// recipes
	public String[] getAllRecipes() {
		String[] recipenames = new String[recipes.size()];
		int idx = 0;
		for (Recipe r : recipes) {
			recipenames[idx] = r.getRecipeName();
			idx++;
		}
		return recipenames;
	}

	public ArrayList<Recipe> getAllRecipeObjects() {
		return recipes;
	}

	public Recipe getRecipe(String name) {
		for (Recipe r : recipes) {
			if (r.getRecipeName().equals(name))
				return r;
		}
		return null;
	}

	// creator
	public void createRecipe(String recipename, String categoryname) {
		RecipeCategory category = getRecipeCategory(categoryname);
		recipes.add(new Recipe(recipename, category));
	}

	// creator
	public void addIngredient(String recipename, double unit, UnitOfMeasure uom,
			String ingredient) {
		Recipe recipe = getRecipe(recipename);
		if (recipe != null) {
			Ingredient ingObj = new Ingredient(unit, uom, ingredient);
			recipe.addIngredientObj(ingObj);
		}
	}

	// creator
	public void addInstruction(String recipename, String instruction) {
		Recipe recipe = getRecipe(recipename);
		if (recipe != null) {
			Instruction instrObj = new Instruction(instruction);
			recipe.addInstructionObj(instrObj);
		}
	}

	// Low Coupling
	public String printRecipe(Recipe r) {
		String s = "";
		if (r != null) {
			s += s.format("%s\n", r.getRecipeName());
			s += s.format("Category: %s\n", r.getRecipeCategory().getCategoryName());
			s += s.format("\nINGREDIENTS\n");
			for (int i = 0; i < r.numIngredients(); i++) {
				Ingredient ing = r.getIngredientObj(i);
				if (ing.getUOM() == null)
					s += s.format("%.2f %s\n", ing.getUnit(), ing.getIngredient());
				else
					s += s.format("%.2f %s %s\n", ing.getUnit(), ing.getUOM(), ing.getIngredient());
			}
			s += s.format("\nINSTRUCTIONS\n");
			for (int i = 0; i < r.numInstructions(); i++) {
				Instruction inst = r.getInstructionObj(i);
				s += s.format("%s\n", inst.getInstruction());
			}
		}
		return s;
	}

	public String toString() {
		String s = "";
		for (Recipe r : recipes) {
			s += printRecipe(r);
		}
		return s;
	}
}