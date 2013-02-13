package se.kth.csc.iprog.dinnerplanner.view;

import javax.swing.*;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class DishNameView extends JPanel implements Observer {
	JLabel labelCostOfDish;
	Dish d;
	
	public DishNameView(DinnerModel model, Dish d){
		model.addObserver(this);
		this.setLayout(new BorderLayout());
		this.d = d;
		
		// Header
		JPanel headerContainer = new JPanel();
		headerContainer.setLayout(new BorderLayout());
		
		// ... img
		ImageIcon tempIcon = new ImageIcon("images/" + d.getImage());
		Image tempImage = tempIcon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon smallIcon = new ImageIcon(tempImage);
		JLabel smallDishIcon = new JLabel("", smallIcon, JLabel.CENTER);
		
		// ... Dish Labels
		JPanel dishLabelContainer = new JPanel();
		dishLabelContainer.setLayout(new BoxLayout(dishLabelContainer, BoxLayout.Y_AXIS));
		JLabel labelTypeOfDish = new JLabel(d.getName());
		labelCostOfDish = new JLabel("Cost of Dish : " + d.getDishPrice(model.getNumberOfGuests()) + "SEK for " + model.getNumberOfGuests() + " guests.");
		labelTypeOfDish.setAlignmentX(CENTER_ALIGNMENT);
		labelCostOfDish.setAlignmentX(CENTER_ALIGNMENT);
		dishLabelContainer.add(Box.createVerticalGlue());
		dishLabelContainer.add(labelTypeOfDish);
		dishLabelContainer.add(labelCostOfDish);
		dishLabelContainer.add(Box.createVerticalGlue());
		
		// ... appending to Header
		headerContainer.add(smallDishIcon, BorderLayout.WEST);
		headerContainer.add(dishLabelContainer, BorderLayout.CENTER);
		
		
		// Split
		// ... Left
		JPanel dishRecipeContainer = new JPanel();
		JLabel dishRecipe = new JLabel();
		dishRecipe.setText(model.getSelectedDish(Dish.STARTER).getDescription());
		dishRecipe.setAlignmentY(TOP_ALIGNMENT);	
		dishRecipeContainer.add(dishRecipe);
		
		// ... Right	
		JPanel tableContainer = new JPanel();
		String[] columnNames = {"Ingredient","Quantity","Cost"};
		
		Object[][] data = new Object[100][3];
		
		int k = 0;
		for (Ingredient i : model.getSelectedDish(Dish.STARTER).getIngredients()){
				data[k][0] = i.getName();
				data[k][1] = i.getQuantity() + " " + i.getUnit();
				data[k][2] = i.getPrice();
			k++;
		}
		
		JTable table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
		
		tableContainer.setLayout(new BorderLayout());
		tableContainer.add(table.getTableHeader(), BorderLayout.PAGE_START);
		tableContainer.add(table, BorderLayout.CENTER);
		
		// Append to split
		JSplitPane contentContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dishRecipeContainer, tableContainer);
		
		// Appending to this
		this.add(headerContainer, BorderLayout.NORTH);
		this.add(contentContainer, BorderLayout.CENTER);
		
	}

	@Override
	public void update(Observable model, Object o) {
		labelCostOfDish.setText(("Cost of Dish : " + d.getDishPrice(((DinnerModel) model).getNumberOfGuests()) + "SEK for " + ((DinnerModel) model).getNumberOfGuests() + " guests."));
		
	}
}
