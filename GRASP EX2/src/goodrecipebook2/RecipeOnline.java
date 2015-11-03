package goodrecipebook2;

//Polymorphism
public class RecipeOnline extends Recipe {
	private String url;

	public RecipeOnline(String name, RecipeCategory cat, String link) {
		super(name, cat);
		url = link;
	}

	// SUPPORT POLYMORPHISM
	public String getSource() {
		return url;
	}
}