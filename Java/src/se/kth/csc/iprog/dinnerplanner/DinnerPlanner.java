package se.kth.csc.iprog.dinnerplanner;

import javax.swing.JFrame;
import se.kth.csc.iprog.dinnerplanner.model.*;
import se.kth.csc.iprog.dinnerplanner.view.*;


public class DinnerPlanner extends JFrame {

	private DinnerModel model = new DinnerModel();

	public DinnerModel getModel() {
		return model;
	}

	public void setModel(DinnerModel model) {
		this.model = model;
	}

	public static void main(String[] args) {
		//Initiating the main JFrame
		DinnerPlanner dinnerPlanner = new DinnerPlanner();
		dinnerPlanner.setTitle("Dinner Planner");
		dinnerPlanner.setSize(500, 500);
		
		//Creating views
		MenuView menuView = new MenuView(dinnerPlanner.getModel());
		PreparationView preparationView = new PreparationView(dinnerPlanner.getModel());
		IngredientsView ingredientsView = new IngredientsView(dinnerPlanner.getModel());
		
		//	The Program State Controller
		DinnerPlannerStateController DPSC = new DinnerPlannerStateController(dinnerPlanner, 
				menuView, 
				preparationView,
				ingredientsView);
		
		//Adding the view to the main JFrame
		dinnerPlanner.getContentPane().add(menuView);
		
		//and starting the JFrame
		dinnerPlanner.setVisible(true);
	}	
}


