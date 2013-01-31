package se.kth.csc.iprog.dinnerplanner.view;

import javax.swing.*;
import java.awt.*;


public class StartView extends JPanel {
	
	public StartView(){
		//Left side border
		JPanel leftBorder = new JPanel();
		leftBorder.setLayout(new BorderLayout());
		// leftBorder.add(new JButton("search"), BorderLayout.NORTH);
		leftBorder.add(new JButton("dishes"), BorderLayout.SOUTH);
		
		//Build search bar
		JPanel searchBar = new JPanel();
		searchBar.setLayout(new FlowLayout());
		searchBar.add(new JTextField("Search", 20));
		leftBorder.add(searchBar,BorderLayout.NORTH);
		
		//Build dish list
		JPanel DishList = new JPanel();
		DishList.setLayout(new FlowLayout());
		
		JButton button1 = new JButton("Dish 1");
		JButton button2 = new JButton("Dish 2");
		JButton button3 = new JButton("Dish 3");
		JButton button4 = new JButton("Dish 4");
		JButton button5 = new JButton("Dish 5");
		
		DishList.add(button1);
		DishList.add(button2);
		DishList.add(button3);
		DishList.add(button4);
		DishList.add(button5);
		
		leftBorder.add(DishList,BorderLayout.CENTER);
		
		//Building left side
		//Left side tabs
		JTabbedPane leftTab = new JTabbedPane();
		leftTab.addTab("Starter", leftBorder);
		
		//The panels properties
		this.setLayout(new BorderLayout());
		this.add(leftTab, BorderLayout.CENTER);
		this.add(new JButton("Dinner Menu"), BorderLayout.EAST);
		

	}
}
