package goodrecipebook2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class RecipeBookFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	JLabel rlabel;
	JComboBox<String> recipenames;
	JTextArea displayArea;
	private RecipeBook recipebook;

	public RecipeBookFrame() {
		super("RecipeBook Frame");
		this.setLayout(new FlowLayout());
		// contents of main frame
		recipebook = new RecipeBook();
		rlabel = new JLabel("Recipes:");
		add(rlabel);
		recipenames = new JComboBox<String>(recipebook.getAllRecipes());
		recipenames.setSelectedIndex(-1);
		recipenames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox) e.getSource();
				String recipename = (String) cb.getSelectedItem();
				// display the recipe
				ArrayList<Recipe> recipes = recipebook.getAllRecipeObjects();
				for (Recipe r : recipes) {
					if (r.getRecipeName().equals(recipename)) {
						displayArea.setText(r.toString());

					}
				}
			}
		});
		add(recipenames);
		displayArea = new JTextArea(20, 30);
		displayArea.setEditable(false);
		add(displayArea);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}