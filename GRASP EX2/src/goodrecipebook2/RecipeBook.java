package goodrecipebook2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RecipeBook {
	
	// Protected variations
	private ArrayList<RecipeCategory> recipecategories = new ArrayList<RecipeCategory>();
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	public RecipeBook() {
		if (!RecipeBookExists()) {
			CreateTempCategories();
			CreateTempRecipes();
			SaveRecipes();
		} else
			ReadRecipes();
		// INDIRECTION (decouple sorting from Recipe)
		Collections.sort(recipes);
	}

	private void CreateTempCategories() {
		addRecipeCategory("dessert");
		addRecipeCategory("appetizer");
	}

	private void CreateTempRecipes() {
		String rname1 = "Puff Puff";
		String cat1 = "dessert";
		createRecipeG(rname1, cat1, "Sarah Miles");
		addIngredient(rname1, 3, UnitOfMeasure.cup, "white flour");
		addIngredient(rname1, 1, UnitOfMeasure.tsp, "salt");
		addIngredient(rname1, 1, UnitOfMeasure.cup, "water");
		addIngredient(rname1, 8, UnitOfMeasure.grms, "yeast");
		addInstruction(rname1, "Combine warm water with salt in bowl");
		addInstruction(rname1, "Add yeast and mix well");
		String rname2 = "Plantain chips";
		String cat2 = "appetizer";
		createRecipeO(rname2, cat2, "http://www.myrecipes.ca");
		addIngredient(rname2, 1.5, UnitOfMeasure.cup, "yogurt");
		addIngredient(rname2, 3, null, "plantains");
		addIngredient(rname2, 1, UnitOfMeasure.cup, "vegetable oil");
		addIngredient(rname2, 0.25, UnitOfMeasure.tsp, "salt");
		addInstruction(rname2, "Heat oil in frying pan");
		addInstruction(rname2, "Cut plantain into thin slices");
		addInstruction(rname2, "Mix in salt");
		addInstruction(rname2, "Fry plantain until crispy brown");
	}

	private boolean RecipeBookExists() {
		// SUPPORT PURE FABRICATION
		return StorageAgent.objectsExist();
	}

	private void SaveRecipes() {
		// SUPPORT PURE FABRICATION
		ArrayList<Object> list = new ArrayList<Object>(Arrays.asList(recipes));
		StorageAgent.SaveObjects(list);
	}

	private void ReadRecipes() {
		// SUPPORT PURE FABRICATION
		ArrayList<Object> list = StorageAgent.ReadObjects();
		for (Object obj : list) {
			recipes.add((Recipe) obj);
		}
	}

	// categories
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
	
	public ArrayList<Recipe> getAllRecipeObjects(){
		return this.recipes;
	}

	private Recipe getRecipe(String name) {
		for (Recipe r : recipes) {
			if (r.getRecipeName().equals(name))
				return r;
		}
		return null;
	}

	public void createRecipeG(String recipename, String categoryname, String g_name) {
		RecipeCategory category = getRecipeCategory(categoryname);
		recipes.add(new RecipeGrandma(recipename, category, g_name));
	}

	public void createRecipeO(String recipename, String categoryname, String url) {
		RecipeCategory category = getRecipeCategory(categoryname);
		recipes.add(new RecipeOnline(recipename, category, url));
	}

	public void addIngredient(String recipename, double unit, UnitOfMeasure uom,
			String ingredient) {
		Recipe recipe = getRecipe(recipename);
		if (recipe != null)
			recipe.addIngredient(unit, uom, ingredient);
	}

	public void addInstruction(String recipename, String instruction) {
		Recipe recipe = getRecipe(recipename);
		if (recipe != null)
			recipe.addInstruction(instruction);
	}

	public String displayRecipe(String recipename) {
		Recipe recipe = getRecipe(recipename);
		return (recipe != null) ? recipe.toString() : "";
	}

	// controller
	public String cmbselectedRecipe(String recipename) {
		return displayRecipe(recipename);
	}
}