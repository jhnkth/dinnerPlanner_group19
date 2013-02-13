//	Package
package se.kth.csc.iprog.dinnerplanner.view;

//	Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class MenuController_openDishView implements MouseListener {
	//	Defining variables
	DinnerModel model;
	MenuView view;
	Dish d;
	
	public MenuController_openDishView(DinnerModel model, MenuView view, Dish d) {
		this.model = model;
		this.view = view;
		this.d = d;
		
	}
	
	//	Action Method
	@Override
	public void mouseClicked(MouseEvent e) {
		DishNameView dishNameView = new DishNameView(model, d);
		JFrame dishNameWindow = new JFrame();
		
		dishNameWindow.setTitle("Dinner Planner - Dish");
		dishNameWindow.setSize(500, 500);
		dishNameWindow.getContentPane().add(dishNameView);
		dishNameWindow.setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
