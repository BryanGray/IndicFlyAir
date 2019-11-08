package com.gui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public interface Screen {
	String AppName="IndicFlyAir";
	String forms[]= {"Passenger Details", "Confirm Details", "Available Options", "Ticket Details"};
	String cities[]={"Delhi","Mumbai","Pune"};
	int width=500;
	int height=500;
	ImageIcon icon = new ImageIcon();
	ImageIcon infoIcon = new ImageIcon();
	public default void loadIcons() {
		try {
			icon.setImage(ImageIO.read(new File("icons/flight-icon.png")));
			Image ic = ImageIO.read(new File("icons/info_icon.png")).getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			infoIcon.setImage(ic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
