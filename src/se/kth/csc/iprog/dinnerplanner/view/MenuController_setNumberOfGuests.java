//	Package
package se.kth.csc.iprog.dinnerplanner.view;

//	Imports
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

public class MenuController_setNumberOfGuests implements ChangeListener {
	//	Defining variables
	DinnerModel model;
	MenuView view;
	
	public MenuController_setNumberOfGuests(DinnerModel model, MenuView view) {
		this.model = model;
		this.view = view;
		
	}
	
	//	Action Method
	@Override
	public void stateChanged(ChangeEvent e) {
		model.setNumberOfGuests((int)view.spinner.getValue());
		
	}
}
