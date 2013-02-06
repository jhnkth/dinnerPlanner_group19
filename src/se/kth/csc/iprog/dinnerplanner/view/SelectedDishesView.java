package se.kth.csc.iprog.dinnerplanner.view;

import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class SelectedDishesView extends JPanel {

	JButton btnRemove;
	Dish dish;
	DinnerModel model;
	
	public SelectedDishesView(DinnerModel model, Dish d) {
		dish =d;
		this.model = model;
		
		// TODO Auto-generated constructor stub
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		//	... labels
		JPanel dishLabelContainer = new JPanel();
		dishLabelContainer.setLayout(new BoxLayout(dishLabelContainer, BoxLayout.Y_AXIS));
		JLabel labelTypeOfDish = new JLabel(d.getName());
		JLabel labelCostOfDish = new JLabel(Double.toString(d.getDishPrice(model.getNumberOfGuests())));
		dishLabelContainer.add(labelTypeOfDish);
		dishLabelContainer.add(labelCostOfDish);
		
		//	... small image
		ImageIcon tempIcon = new ImageIcon("images/" + d.getImage());
		Image tempImage = tempIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon smallIcon = new ImageIcon(tempImage);
		JLabel smallDishIcon = new JLabel("", smallIcon, JLabel.CENTER);
		
		//	... remove button
		btnRemove = new JButton("X");
		
		this.add(smallDishIcon);
		this.add(Box.createHorizontalGlue());
		this.add(dishLabelContainer);
		this.add(Box.createHorizontalGlue());
		this.add(btnRemove);
		
	}
}
