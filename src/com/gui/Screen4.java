package com.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.main.DisplayManager;
import com.main.Flight;
import com.main.Passenger;

public class Screen4 extends JFrame implements Screen{
	private DisplayManager dmgr;

	public Screen4(DisplayManager dmgr) {        
        this.setDMgr(dmgr);
        initComponents();
	}
	
	public Screen4() {
		initComponents();
	}
	
	private JLabel nameLabel,flightsLabel,routeLabel,dateLabel,seatLabel;
	private JLabel name,flights,route,date,seat;
	private JButton proceedButton, backButton;
	
	public void initComponents() {
		setTitle(AppName+" - "+forms[1]);
		setIconImage(icon.getImage());
		setBounds(300, 300, width, height);
		Passenger p = this.getDMgr().getMgr().getPassenger();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Labels
		nameLabel=new JLabel("Name:");
		flightsLabel=new JLabel("Flights");
		routeLabel=new JLabel("Route");
		dateLabel=new JLabel("Date");
		seatLabel=new JLabel("Seats");
		
		if(!p.getName().equalsIgnoreCase("")){
			//Data
			name=new JLabel(p.getName());
			Flight flight[]=p.getCombinedFlight().getComFlight();
			flights=new JLabel(flight[0].getFlightNum()+"->"+flight[1].getFlightNum());
			route=new JLabel(flight[0].getSrc()+"->"+flight[0].getDest()+"->"+flight[1].getDest());
			date=new JLabel(p.getDate());
			seat=new JLabel(Integer.toString(p.getSeats()));
			
			
			// Button and Event     
	        proceedButton=new JButton("Confirm");
	        proceedButton.addActionListener(new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String msg="Ticket Generation Successful";
					JOptionPane.showInternalMessageDialog(getContentPane(), msg, "Booking Status!!", JOptionPane.INFORMATION_MESSAGE);
					getDMgr().exitFRS();
				}
			});
	        
	        backButton=new JButton("Back");
	        backButton.addActionListener(new ActionListener(){
	        	@Override
	            public void actionPerformed(ActionEvent e){
	                getDMgr().showScreen3(dmgr);
	            }
	        });
	        
			// Button Panel
	        JPanel btnPanel=new JPanel();
	        
	        btnPanel.add(backButton);
	        btnPanel.add(proceedButton);
	        
	        JPanel panel=new JPanel();
	        panel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80)));
	        panel.setLayout(new GridBagLayout());
	        GridBagConstraints gc=new GridBagConstraints();
	        gc.weightx=1;
	        gc.weighty=1;
	        gc.anchor=GridBagConstraints.WEST;
	        
	        gc.gridx=0;
	        gc.gridy=0;
	        panel.add(nameLabel,gc);
	        
	        gc.gridx=0;
	        gc.gridy=1;
	        panel.add(flightsLabel,gc);
	        
	        gc.gridx=0;
	        gc.gridy=2;
	        panel.add(routeLabel,gc);
	        
	        gc.gridx=0;
	        gc.gridy=3;
	        panel.add(dateLabel,gc);
	        
	        gc.gridx=0;
	        gc.gridy=4;
	        panel.add(seatLabel,gc);
	        
	        
	        gc.anchor=GridBagConstraints.LINE_START;
	        
	        gc.gridx=1;
	        gc.gridy=0;
	        panel.add(name,gc);
	        
	        gc.gridx=1;
	        gc.gridy=1;
	        panel.add(flights,gc);
	        
	        gc.gridx=1;
	        gc.gridy=2;
	        panel.add(route,gc);
	        
	        gc.gridx=1;
	        gc.gridy=3;
	        panel.add(date,gc);
	        
	        gc.gridx=1;
	        gc.gridy=4;
	        panel.add(seat,gc);
	        
	        gc.anchor=GridBagConstraints.SOUTH;
	        gc.gridx=0;
	        gc.gridy=5;
	        gc.gridwidth=2; 
	        panel.add(btnPanel,gc);
	        
	        add(panel);
		}
	}
	
	public DisplayManager getDMgr() {
		return dmgr;
	}

	public void setDMgr(DisplayManager dmgr) {
		this.dmgr = dmgr;
		initComponents();
	}

}
