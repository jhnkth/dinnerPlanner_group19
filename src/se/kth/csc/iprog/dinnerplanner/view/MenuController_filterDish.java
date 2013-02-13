//	Package
package se.kth.csc.iprog.dinnerplanner.view;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

public class MenuController_filterDish implements DocumentListener {
	//	Defining variables
	DinnerModel model;
	MenuView view;
	JTextField t;
	int type;
	
	public MenuController_filterDish(DinnerModel model, MenuView view, JTextField t, int type) {
		this.model = model;
		this.view = view;
		this.t = t;
		this.type = type;
		
	}

	//	Action Method
	@Override
	public void changedUpdate(DocumentEvent e) {
		System.out.println("update");
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		view.update(model, true);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		view.update(model, true);
		
	}
}
