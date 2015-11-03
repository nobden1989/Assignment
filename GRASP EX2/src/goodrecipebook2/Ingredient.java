package goodrecipebook2;

public class Ingredient {
	
	// Protected variations
	private double unit;
	private UnitOfMeasure unitofmeasure = null;
	private String ingredient;

	public Ingredient(double u, UnitOfMeasure uom, String ingrd) {
		unit = u;
		unitofmeasure = uom;
		ingredient = ingrd;
	}

	public double getUnit() {
		return unit;
	}

	public UnitOfMeasure getUOM() {
		return unitofmeasure;
	}

	public String getIngredient() {
		return ingredient;
	}
}