package se.kth.csc.iprog.dinnerplanner.view;

import javax.swing.*;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class StarterChosenView extends JPanel implements Observer {
	
	JSpinner spinner;
	JLabel labelCost;
	
	JButton btnRemove;
	JButton btnAdd;
	
	
	public StarterChosenView(DinnerModel model){	
		
		model.addObserver(this);
		
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
			btnAdd = new JButton("+");
			
			dish.setLayout(new BorderLayout());
			dish.add(dishIcon, BorderLayout.NORTH);
			dish.add(dishName, BorderLayout.SOUTH);
			dish.add(btnAdd, BorderLayout.EAST);
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
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 0, 100, 1);
		spinner = new JSpinner(spinnerModel);
		
		spinnerContainer.add(labelNrOfGuests);
		spinnerContainer.add(spinner);
		
		rightNorthContainer.add(spinnerContainer, BorderLayout.NORTH);
		
		// Right Side Cost
		JPanel costContainer = new JPanel();
		JLabel labelCostLabel = new JLabel("Total cost: ");
		labelCost = new JLabel(Float.toString(model.getTotalMenuPrice()));
		
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
			SelectedDishesView dishView = new SelectedDishesView(model, d);
			
			chosenDishesContainer.add(dishView);
		
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
	
	public void update(Observable model, Object o){
		//model.setNumberOfGuests((Integer)spinner.getValue());
		
		labelCost.setText(Float.toString(((DinnerModel)model).getTotalMenuPrice()));
		//spinner.setValue((Object)(DinnerModel)model).getNumberOfGuests());

	}
}
