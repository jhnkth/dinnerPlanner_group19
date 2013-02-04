package se.kth.csc.iprog.dinnerplanner.view;

import javax.swing.*;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

import java.awt.*;
import java.util.List;


public class PreparationView extends JPanel {
	
	public PreparationView(DinnerModel model){
		JScrollPane mainScrollContainer = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel mainContainer = new JPanel();
		mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
		
		mainScrollContainer.setViewportView(mainContainer);
		
		JLabel label1 = new JLabel("Dinner menu preparation");
		label1.setAlignmentX(CENTER_ALIGNMENT);
		label1.setFont(new Font("Serif", Font.PLAIN, 26));
		mainContainer.add(label1);
		mainContainer.add(Box.createVerticalGlue());
		
		JLabel label2 = new JLabel("Starter : " + model.getSelectedDish(Dish.STARTER).getName());
		label2.setAlignmentX(CENTER_ALIGNMENT);
		label2.setFont(new Font("Serif", Font.PLAIN, 18));
		mainContainer.add(label2);
		mainContainer.add(Box.createVerticalGlue());
		
		JLabel label3 = new JLabel(model.getSelectedDish(Dish.STARTER).getDescription());
		label3.setAlignmentX(CENTER_ALIGNMENT);
		mainContainer.add(label3);
		mainContainer.add(Box.createVerticalGlue());
		mainContainer.add(Box.createVerticalGlue());
		
		JLabel label4 = new JLabel("Main : " + model.getSelectedDish(Dish.MAIN).getName());
		label4.setAlignmentX(CENTER_ALIGNMENT);
		label4.setFont(new Font("Serif", Font.PLAIN, 18));
		mainContainer.add(label4);
		mainContainer.add(Box.createVerticalGlue());
		
		JLabel label5 = new JLabel(model.getSelectedDish(Dish.MAIN).getDescription());
		label5.setAlignmentX(CENTER_ALIGNMENT);
		mainContainer.add(label5);
		mainContainer.add(Box.createVerticalGlue());
		mainContainer.add(Box.createVerticalGlue());
		
		JLabel label6 = new JLabel("Dessert : Dish name");
		label6.setAlignmentX(CENTER_ALIGNMENT);
		label6.setFont(new Font("Serif", Font.PLAIN, 18));
		mainContainer.add(label6);
		mainContainer.add(Box.createVerticalGlue());
		
		JLabel label7 = new JLabel("Dessert description");
		label7.setAlignmentX(CENTER_ALIGNMENT);
		mainContainer.add(label7);
		mainContainer.add(Box.createVerticalGlue());
		mainContainer.add(Box.createVerticalGlue());
		
		
		
		this.setLayout(new BorderLayout());
		this.add(mainScrollContainer, BorderLayout.CENTER);
	}
}
