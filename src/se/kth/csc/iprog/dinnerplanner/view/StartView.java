package se.kth.csc.iprog.dinnerplanner.view;

import javax.swing.*;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

import java.awt.*;
import java.util.List;


public class StartView extends JPanel {
	
	public StartView(DinnerModel model){	
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
		
		JPanel dish = new JPanel();
		ImageIcon icon = new ImageIcon("images/icecream.jpg");
		JLabel dishName = new JLabel("Dish name", null, JLabel.CENTER);
		JLabel dishIcon = new JLabel("", icon, JLabel.CENTER);		
		
		dish.setLayout(new BorderLayout());
		dish.add(dishIcon, BorderLayout.NORTH);
		dish.add(dishName, BorderLayout.SOUTH);
		DishList.add(dish);
		
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
		JLabel labelCost = new JLabel("$0.00");
		
		costContainer.add(labelCostLabel);
		costContainer.add(labelCost);
		
		rightNorthContainer.add(costContainer, BorderLayout.NORTH);
		
		// Right Side Dinner Menu Header
		JLabel dinnerMenuHeader = new JLabel("Dinner Menu");
		dinnerMenuHeader.setAlignmentX(CENTER_ALIGNMENT);
		rightNorthContainer.add(dinnerMenuHeader, BorderLayout.NORTH);
		
		// Right side Dinner Menu
		JLabel labelDinnerMenu = new JLabel("Drag your dish to the menu");
		labelDinnerMenu.setAlignmentX(CENTER_ALIGNMENT);
		
		// Append to right side
		rightSide.add(rightNorthContainer, BorderLayout.NORTH);
		rightSide.add(labelDinnerMenu, BorderLayout.CENTER);
		rightSide.add(btnContainer, BorderLayout.SOUTH);
		
		//The panels properties
		this.setLayout(new BorderLayout());
		this.add(leftTab, BorderLayout.CENTER);		
		this.add(rightSide, BorderLayout.EAST);
		

		
		
		
	}
}
