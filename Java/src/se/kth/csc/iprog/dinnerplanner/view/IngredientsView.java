//	Package
package se.kth.csc.iprog.dinnerplanner.view;

//	Import
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

//	--------
public class IngredientsView extends JPanel implements Observer {
	JTable table;
	JScrollPane scrollPane;
	DefaultTableModel tableModel;
	Object[][] data;
	Object[] columnNames = {"Column One", "Column Two", "Column Three"};
	
	public IngredientsView(DinnerModel model){
		model.addObserver(this);
		
		data = this.getData(model);
		tableModel = new DefaultTableModel(data, columnNames);
		table = new JTable(tableModel);
		scrollPane = new JScrollPane(table);
		this.add(scrollPane);
	}
	
	public Object[][] getData(DinnerModel model) {
		Object[][] tempData = new Object[100][3];
		Set<Ingredient> tempIngredients;
		Set<Ingredient> ingredients = new HashSet<Ingredient>();
		
		for (Dish d : model.getFullMenu()) {
			tempIngredients = d.getIngredients();
			//	Fetching all ingredients
			for (Ingredient i : tempIngredients) {
				ingredients.add(i);
				
			}
		}
		
		int k = 0;
		for (Ingredient ing : ingredients) {
			tempData[k][0] = ing.getName();
			tempData[k][1] = ing.getQuantity() + " " + ing.getUnit();
			tempData[k][2] = ing.getPrice();
			k++;

		}
		
		//	Return
		return tempData;
	}

	@Override
	public void update(Observable model, Object o) {
		data = this.getData((DinnerModel) model);
		tableModel.setDataVector(data, columnNames);

	}
}