//	Package
package se.kth.csc.iprog.dinnerplanner.view;

//	Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class MenuController_removeDish implements ActionListener {
	//	Defining variables
	DinnerModel model;
	MenuView view;
	Dish d;
	
	public MenuController_removeDish(DinnerModel model, MenuView view, Dish d) {
		this.model = model;
		this.view = view;
		this.d = d;
		
	}
	
	//	Action Method
	@Override
	public void actionPerformed(ActionEvent e) {
		model.removeDish(d);
		
	}
}
