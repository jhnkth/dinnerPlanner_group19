package se.kth.csc.iprog.dinnerplanner.view;

import javax.swing.*;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.awt.*;
import java.util.List;


public class StarterChosenView extends JPanel {
	
	public StarterChosenView(DinnerModel model){	
		//Left side border
		JPanel leftBorder = new JPanel();
		leftBorder.setLayout(new BorderLayout());
		
		//Build search bar
		JPanel searchBar = new JPanel();
		searchBar.setLayout(new FlowLayout());
		searchBar.add(new JTextField("Search", 20));
		leftBorder.add(searchBar,BorderLayout.NORTH);
		
		//Build dish list
		JScrollPane DishListContainer = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		DishListContainer.setPreferredSize(new Dimension(450, 110));
		
		JPanel DishList = new JPanel();
		DishList.setLayout(new FlowLayout());
		
		for(Dish d : model.getDishes()){
			JPanel dish = new JPanel();
			ImageIcon icon = new ImageIcon("images/" + d.getImage());
			JLabel dishName = new JLabel(d.getName(), null, JLabel.CENTER);
			JLabel dishIcon = new JLabel("", icon, JLabel.CENTER);		
			
			dish.setLayout(new BorderLayout());
			dish.add(dishIcon, BorderLayout.NORTH);
			dish.add(dishName, BorderLayout.SOUTH);
			DishList.add(dish);
		}
		

		
		DishListContainer.setViewportView(DishList);
		leftBorder.add(DishListContainer,BorderLayout.CENTER);
		
		//Building left side
		//Left side tabs
		JTabbedPane leftTab = new JTabbedPane();
		leftTab.addTab("Starter", leftBorder);
		leftTab.addTab("Main", null);
		leftTab.addTab("Dessert", null);
		
		// Right Side
		JPanel rightSide = new JPanel();
		rightSide.setLayout(new BorderLayout());
		
		// Right Side buttons
		JPanel btnContainer = new JPanel();
		JButton btnPreparation = new JButton("Preparation");
		JButton btnIngredients= new JButton("Ingredients");
		
		btnContainer.add(btnPreparation);
		btnContainer.add(btnIngredients);
		
		// Right North Container
		JPanel rightNorthContainer = new JPanel();
		rightNorthContainer.setLayout(new BoxLayout(rightNorthContainer, BoxLayout.Y_AXIS));
		
		// Right Side Spinner
		JPanel spinnerContainer = new JPanel();
		JLabel labelNrOfGuests = new JLabel("number of guests");
		SpinnerModel spinnerModel = new SpinnerNumberModel(1, 0, 100, 1);
		JSpinner spinner = new JSpinner(spinnerModel);
		
		spinnerContainer.add(labelNrOfGuests);
		spinnerContainer.add(spinner);
		
		rightNorthContainer.add(spinnerContainer, BorderLayout.NORTH);
		
		// Right Side Cost
		JPanel costContainer = new JPanel();
		JLabel labelCostLabel = new JLabel("Total cost: ");
		JLabel labelCost = new JLabel(Float.toString(model.getTotalMenuPrice()));
		
		costContainer.add(labelCostLabel);
		costContainer.add(labelCost);
		
		rightNorthContainer.add(costContainer, BorderLayout.NORTH);
		
		// Right Side Dinner Menu Header
		JLabel dinnerMenuHeader = new JLabel("Dinner Menu");
		dinnerMenuHeader.setAlignmentX(CENTER_ALIGNMENT);
		rightNorthContainer.add(dinnerMenuHeader, BorderLayout.NORTH);
		
		// Right side Dinner Menu
		// Chosen Dish Container
		JPanel chosenDishesContainer = new JPanel();
		chosenDishesContainer.setLayout(new BoxLayout(chosenDishesContainer, BoxLayout.Y_AXIS));
		
		for (Dish d : model.getFullMenu()){
			
			// Chosen Dish
			JPanel chosenDishContainer = new JPanel();
			chosenDishContainer.setLayout(new BoxLayout(chosenDishContainer, BoxLayout.X_AXIS));
			
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
			JButton btnRemove = new JButton("X");
			
			chosenDishContainer.add(smallDishIcon);
			chosenDishContainer.add(Box.createHorizontalGlue());
			chosenDishContainer.add(dishLabelContainer);
			chosenDishContainer.add(Box.createHorizontalGlue());
			chosenDishContainer.add(btnRemove);
			chosenDishesContainer.add(chosenDishContainer);
		
		}
		
		
		// Append to right side
		rightSide.add(rightNorthContainer, BorderLayout.NORTH);
		rightSide.add(chosenDishesContainer, BorderLayout.CENTER);
		rightSide.add(btnContainer, BorderLayout.SOUTH);
		
		//The panels properties
		this.setLayout(new BorderLayout());
		this.add(leftTab, BorderLayout.CENTER);		
		this.add(rightSide, BorderLayout.EAST);
		

		
		
		
	}
}
