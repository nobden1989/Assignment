package badrecipebook1;

// Low Coupling
// Information Expert
public class Ingredient {
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