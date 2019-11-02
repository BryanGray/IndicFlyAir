package com.gui;

import javax.swing.ImageIcon;

public interface Screen {
	String AppName="IndicFlyAir";
	String forms[]= {"Passenger Details", "Confirm Details", "Available Options", "Ticket Details"};
	String cities[]={"Delhi","Mumbai","Pune"};
	int width=500;
	int height=500;
	ImageIcon icon = new ImageIcon("flight-icon.png");
}
