package se.kth.csc.iprog.dinnerplanner.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import se.kth.csc.iprog.dinnerplanner.DinnerPlanner;

public class DinnerPlannerStateController implements ActionListener {
	DinnerPlanner dinnerPlanner;
	MenuView menuView;
	PreparationView preparationView;
	IngredientsView ingredientsView;
	
	JFrame preparationWindow;
	JFrame ingredientsWindow;
	
	public DinnerPlannerStateController(
			DinnerPlanner dinnerPlanner, 
			MenuView menuView, 
			PreparationView preparationView,
			IngredientsView ingredientsView
	) {
		this.dinnerPlanner = dinnerPlanner;
		this.menuView = menuView;
		this.preparationView = preparationView;
		this.ingredientsView = ingredientsView;
		
		//	Windows
		preparationWindow = new JFrame();
		preparationWindow.setTitle("Dinner Planner - Preparation");
		preparationWindow.setSize(500, 500);
		preparationWindow.getContentPane().add(preparationView);
		
		ingredientsWindow = new JFrame();
		ingredientsWindow.setTitle("Dinner Planner - Ingredients");
		ingredientsWindow.setSize(500, 500);
		ingredientsWindow.getContentPane().add(ingredientsView);
		
		//	Listeners
		menuView.btnPreparation.addActionListener(this);
		menuView.btnIngredients.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// PreparationView.
		if (e.getSource() == menuView.btnPreparation) {
			if (preparationWindow.isVisible()) {
				preparationWindow.setVisible(false);
				
			} else {
				//preparationView.update(dinnerPlanner.getModel(), true);
				preparationWindow.setVisible(true);
				
			}
		}
		// IngredientsView.
		if (e.getSource() == menuView.btnIngredients) {
			if (ingredientsWindow.isVisible()) {
				ingredientsWindow.setVisible(false);
				
			} else {
				ingredientsView.update(dinnerPlanner.getModel(), true);
				ingredientsWindow.setVisible(true);
				
			}
		}
	}
}
