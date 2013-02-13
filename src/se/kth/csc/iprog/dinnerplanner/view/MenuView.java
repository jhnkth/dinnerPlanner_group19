//	Package
package se.kth.csc.iprog.dinnerplanner.view;

//	Imports
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class MenuView extends JPanel implements Observer {
	//	Defining public(?) variables.
	JButton btnAdd;
	JButton btnRemove;
	JSpinner spinner;
	JLabel labelCost;
	JPanel chosenDishesContainer;
	JTabbedPane leftSide;
	JScrollPane dishListContainerStarter;
	JScrollPane dishListContainerMain;
	JScrollPane dishListContainerDessert;
	JPanel leftSideStarter;
	JPanel leftSideMain;
	JPanel leftSideDessert;
	JTextField searchStarter;
	JTextField searchMain;
	JTextField searchDessert;
	JButton btnPreparation;
	JButton btnIngredients;
	
	//	Constructor
	public MenuView(DinnerModel model) {
		model.addObserver(this);
		
		//	Defining variables
		JPanel rightSide;
		
		//	Building left part of the view
		leftSideStarter = this.buildLeftSide(1, model);
		leftSideMain = this.buildLeftSide(2, model);
		leftSideDessert = this.buildLeftSide(3, model);
		
		leftSide = new JTabbedPane();
		leftSide.addTab("Starter", leftSideStarter);
		leftSide.addTab("Main", leftSideMain);
		leftSide.addTab("Dessert", leftSideDessert);
		
		
		//	Build right part of the view
		rightSide = this.buildRightSide(model);
		
		
		//	Appending left and right part to the MenuView.
		this.setLayout(new BorderLayout());
		this.add(leftSide, BorderLayout.CENTER);
		this.add(rightSide, BorderLayout.EAST);
		
	}
	//	Methods
	//	----------
	//	buildLargeDish, is used to build a dish for the dish list on the left side.
	public JPanel buildLargeDish(DinnerModel model, Dish d) {
		//	Defining variables
		JPanel dish;
		JLabel dishIcon;
		JLabel dishName;
		ImageIcon icon;
		
		//	Creating components
		dish = new JPanel();
		icon = new ImageIcon("images/" + d.getImage());
		dishName = new JLabel(d.getName(), null, JLabel.CENTER);
		dishIcon = new JLabel("", icon, JLabel.CENTER);
		btnAdd = new JButton("+");
		
		//	Attaching listener
		btnAdd.addActionListener(new MenuController_addDish(model, this, d));
		dishName.addMouseListener(new MenuController_openDishView(model, this, d));
		
		//	Appending
		dish.setLayout(new BorderLayout());
		dish.add(dishIcon, BorderLayout.NORTH);
		dish.add(dishName, BorderLayout.CENTER);
		dish.add(btnAdd, BorderLayout.EAST);
		
		//	Returning
		return dish;
	}
	//	buildSmallDish, is used to build the small dishes on the right side view.
	public JPanel buildSmallDish(DinnerModel model, Dish d) {
		//	Defining variables
		JPanel dish = new JPanel();
		JPanel dishLabelContainer;
		JLabel labelTypeOfDish;
		JLabel labelCostOfDish;
		ImageIcon tempIcon;
		Image tempImage;
		ImageIcon smallIcon;
		JLabel smallDishIcon;	
		
		//	Creating components
		//	-----------------------
		//	... Labels
		dishLabelContainer = new JPanel();
		dishLabelContainer.setLayout(new BoxLayout(dishLabelContainer, BoxLayout.Y_AXIS));
		
		labelTypeOfDish = new JLabel(d.getName());
		labelCostOfDish = new JLabel(Double.toString(d.getDishPrice(model.getNumberOfGuests())));
		
		//	... Small Icon
		tempIcon = new ImageIcon("images/" + d.getImage());
		tempImage = tempIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		smallIcon = new ImageIcon(tempImage);
		smallDishIcon = new JLabel("", smallIcon, JLabel.CENTER);
		
		//	... remove button
		btnRemove = new JButton("X");
		btnRemove.addActionListener(new MenuController_removeDish(model, this, d));
		
		//	Appending
		dishLabelContainer.add(labelTypeOfDish);
		dishLabelContainer.add(labelCostOfDish);
		
		dish.add(smallDishIcon);
		dish.add(Box.createHorizontalGlue());
		dish.add(dishLabelContainer);
		dish.add(Box.createHorizontalGlue());
		dish.add(btnRemove);
		
		//	Returning
		return dish;

	}
	//	buildEmptyChosenDishContainer, ...
	public JPanel buildEmptyChosenDishContainer () {
		JPanel empty = new JPanel();
		
		//	returning
		return empty;
		
	}
	//	buildDishlist, ...
	public JPanel buildDishlist(int type, DinnerModel model, JTextField searchBarText) {
		JPanel dishList;
		dishList = new JPanel();
		dishList.setLayout(new FlowLayout());
		
		for (Dish d : model.getDishesOfType(type)) {
			//	Filtering out dishes with wrong names
			if (searchBarText.getText() == "") {
				dishList.add(this.buildLargeDish(model, d));
				
			} else {
				String s = searchBarText.getText();
				String dName = d.getName();
				boolean dAdd = true;
				
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) != dName.charAt(i)) {
						dAdd = false;
						
					}
				}
				
				if (dAdd) {
					dishList.add(this.buildLargeDish(model, d));
					
				}
			}
		}
		//	Returning
		return dishList;
		
	}
	//	buildLeftSide, is used to build the left side of the view.
	public JPanel buildLeftSide(int type, DinnerModel model) {
		//	Defining variables	
		JPanel leftSide = new JPanel();
		JPanel searchBar;
		JTextField searchBarText;
		
		//	Components
		//	-------------
		//	Search bar
			searchBarText = new JTextField("", 20);
			searchBarText.getDocument().addDocumentListener(new MenuController_filterDish(model, this, searchBarText, type));
			
			searchBar = new JPanel();
			searchBar.setLayout(new FlowLayout());
			searchBar.add(searchBarText);
			
			
		//	Building dish lists depending on type.
		if (type == 1) {
			dishListContainerStarter = new JScrollPane();
			dishListContainerStarter.setPreferredSize(new Dimension(450, 110));
			dishListContainerStarter.setViewportView(this.buildDishlist(type, model, searchBarText));
			
			//	Appending
			leftSide.setLayout(new BorderLayout());
			leftSide.add(searchBar, BorderLayout.NORTH);
			leftSide.add(dishListContainerStarter, BorderLayout.CENTER);
			
			searchStarter = searchBarText; 
			
		} else if (type == 2) {		
			dishListContainerMain = new JScrollPane();
			dishListContainerMain.setPreferredSize(new Dimension(450, 110));
			dishListContainerMain.setViewportView(this.buildDishlist(type, model, searchBarText));
			
			//	Appending
			leftSide.setLayout(new BorderLayout());
			leftSide.add(searchBar, BorderLayout.NORTH);
			leftSide.add(dishListContainerMain, BorderLayout.CENTER);
			
			searchMain = searchBarText; 
			
		} else {			
			dishListContainerDessert = new JScrollPane();
			dishListContainerDessert.setPreferredSize(new Dimension(450, 110));
			dishListContainerDessert.setViewportView(this.buildDishlist(type, model, searchBarText));
			
			//	Appending
			leftSide.setLayout(new BorderLayout());
			leftSide.add(searchBar, BorderLayout.NORTH);
			leftSide.add(dishListContainerDessert, BorderLayout.CENTER);
			
			searchDessert = searchBarText;
			
		}
		
		//	Returning
		return leftSide;
	}
	//	buldRightSide, is used to build the right side of the view.
	public JPanel buildRightSide(DinnerModel model) {
		//	Defining variables
		JPanel rightSide = new JPanel();		
		JPanel rightSideButtonContainer;
		JPanel rightNorthContainer;
		JPanel spinnerContainer;
		JLabel labelNrOfGuests;
		JPanel costContainer;
		JLabel labelCostLabel;
		JLabel dinnerMenuHeader;
		
		//	Components
		//	-------------
		//	Right Side Buttons
		btnPreparation = new JButton("Preparation");
		btnIngredients= new JButton("Ingredients");
		
		rightSideButtonContainer = new JPanel();
		rightSideButtonContainer.add(btnPreparation);
		rightSideButtonContainer.add(btnIngredients);
		
		//	Right Side Spinner
		labelNrOfGuests = new JLabel("number of guests");
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(model.getNumberOfGuests(), 0, 100, 1);
		spinner = new JSpinner(spinnerModel);
		spinner.addChangeListener(new MenuController_setNumberOfGuests(model, this));
		
		spinnerContainer = new JPanel();
		spinnerContainer.add(labelNrOfGuests);
		spinnerContainer.add(spinner);
		
		// Right Side Cost
		costContainer = new JPanel();
		labelCostLabel = new JLabel("Total cost: ");
		labelCost = new JLabel(Float.toString(model.getTotalMenuPrice()));
		
		costContainer.add(labelCostLabel);
		costContainer.add(labelCost);
		
		// Right Side Dinner Menu Header
		dinnerMenuHeader = new JLabel("Dinner Menu");
		dinnerMenuHeader.setAlignmentX(CENTER_ALIGNMENT);
		
		// Chosen Dish Container
		chosenDishesContainer = new JPanel();
		chosenDishesContainer.setLayout(new BoxLayout(chosenDishesContainer, BoxLayout.Y_AXIS));
		
		if (model.getFullMenu().isEmpty()) {
			chosenDishesContainer.add(this.buildEmptyChosenDishContainer());
			
		} else {
			for (Dish d : model.getFullMenu()) {
				chosenDishesContainer.add(this.buildSmallDish(model, d));
				
			}
		}
		
		//	Right North Container
		rightNorthContainer = new JPanel();
		rightNorthContainer.setLayout(new BoxLayout(rightNorthContainer, BoxLayout.Y_AXIS));
		
		//	Appending
		rightNorthContainer.add(spinnerContainer, BorderLayout.NORTH);
		rightNorthContainer.add(costContainer, BorderLayout.NORTH);
		rightNorthContainer.add(dinnerMenuHeader, BorderLayout.NORTH);
		
		rightSide.setLayout(new BorderLayout());
		rightSide.add(rightNorthContainer, BorderLayout.NORTH);
		rightSide.add(chosenDishesContainer, BorderLayout.CENTER);
		rightSide.add(rightSideButtonContainer, BorderLayout.SOUTH);
		
		//	Returning
		return rightSide;	
	}
	@Override
	public void update(Observable model, Object o) {		
		//	Changing total cost.
		labelCost.setText(Float.toString(((DinnerModel)model).getTotalMenuPrice()));
		
		//	Re-iterating list chosen dishes.
		chosenDishesContainer.removeAll();		
		if (((DinnerModel) model).getFullMenu().isEmpty()) {
			chosenDishesContainer.add(this.buildEmptyChosenDishContainer());
			
		} else {
			for (Dish d : ((DinnerModel) model).getFullMenu()) {
				chosenDishesContainer.add(this.buildSmallDish((DinnerModel) model, d));
				
			}
		}
		
		//	Re-iterating dish list
		dishListContainerStarter.setViewportView(this.buildDishlist(1, (DinnerModel) model, searchStarter));
		dishListContainerMain.setViewportView(this.buildDishlist(2, (DinnerModel) model, searchMain));
		dishListContainerDessert.setViewportView(this.buildDishlist(3, (DinnerModel) model, searchDessert));
		
		leftSide.updateUI();
	}
}
