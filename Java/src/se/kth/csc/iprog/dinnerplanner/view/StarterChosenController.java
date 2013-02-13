package se.kth.csc.iprog.dinnerplanner.view;

import javax.swing.*;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class StarterChosenController implements ActionListener {
	
	DinnerModel model;
	StarterChosenView view;
	
	public StarterChosenController(DinnerModel model, StarterChosenView view){
		this.model = model;
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == view.btnAdd)
		{
			//Add dish
		}
		
		
	}
	
}