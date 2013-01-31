package se.kth.csc.iprog.dinnerplanner.view;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainView extends JPanel {
	
	public MainView(){
		JLabel label = new JLabel();
		label.setText("Hello World");
		this.add(label);
		
		//here you set other layout elements
	}
}
