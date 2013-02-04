package se.kth.csc.iprog.dinnerplanner.view;

import javax.swing.*;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

import java.awt.*;
import java.util.List;


public class IngredientsView extends JPanel {
	
	public IngredientsView(DinnerModel model){
		
		String[] columnNames = {"Ingredient","Quantity","Cost"};
		
		Object[][] data = new Object[100][3];
		
		int k = 0;
		for (Ingredient i : model.getAllIngredients()){
				data[k][0] = i.getName();
				data[k][1] = i.getQuantity() + " " + i.getUnit();
				data[k][2] = i.getPrice();
			k++;
		}
		
		JTable table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
		
		this.setLayout(new BorderLayout());
		this.add(table.getTableHeader(), BorderLayout.PAGE_START);
		this.add(table, BorderLayout.CENTER);
	}
}