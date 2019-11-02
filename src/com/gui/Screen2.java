package com.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.main.DisplayManager;
import com.main.Passenger;
import com.main.Query;

public class Screen2 extends JFrame implements Screen{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DisplayManager dmgr;

	public Screen2(DisplayManager dmgr) {        
        this.setDMgr(dmgr);
        initComponents();
	}
	
	public Screen2() {
		initComponents();
	}
	
	// Variables Declaration
	private JLabel namelabel,sourcecitylabel ,destlabel1,destlabel2,seatslabel,datelabel;
	private	JLabel nameField, sourceField, seatsnum, datePicker;
	private JButton proceedButton, backButton;

	private void initComponents() {
		setTitle(AppName+" - "+forms[1]);
		setIconImage(icon.getImage());
		setBounds(300, 300, width, height);
		Passenger passenger = this.getDMgr().getMgr().getPassenger();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Labels
		namelabel=new JLabel("Name:");
        sourcecitylabel=new JLabel("Source city: ");
        destlabel1=new JLabel("Destination city: ");
        destlabel2=new JLabel("Singapore (SIN)");
        seatslabel=new JLabel("No.of seats:");
        datelabel=new JLabel("Date of Travel: ");
        // Inputs
        nameField=new JLabel(passenger.getName());
        sourceField=new JLabel(passenger.getSource());
        
        seatsnum=new JLabel(Integer.toString(passenger.getSeats()));
        
        //JDatePickerImpl datePicker = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), null), null); 
        datePicker=new JLabel(passenger.getDate());
        // Button and Event     
        proceedButton=new JButton("Proceed");
        proceedButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getDMgr().showScreen3(dmgr);
			}
		});
        
        backButton=new JButton("Back");
        backButton.addActionListener(new ActionListener(){
        	@Override
            public void actionPerformed(ActionEvent e){
                getDMgr().showScreen1(dmgr);
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
        panel.add(namelabel,gc);
        
        gc.gridx=0;
        gc.gridy=1;
        panel.add(sourcecitylabel,gc);
        
        gc.gridx=0;
        gc.gridy=2;
        panel.add(destlabel1,gc);
        
        gc.gridx=0;
        gc.gridy=3;
        panel.add(seatslabel,gc);
        
        gc.gridx=0;
        gc.gridy=4;
        panel.add(datelabel,gc);
        
        
        gc.anchor=GridBagConstraints.LINE_START;
        
        gc.gridx=1;
        gc.gridy=0;
        panel.add(nameField,gc);
        
        gc.gridx=1;
        gc.gridy=1;
        panel.add(sourceField,gc);
        
        gc.gridx=1;
        gc.gridy=2;
        panel.add(destlabel2,gc);
        
        gc.gridx=1;
        gc.gridy=3;
        panel.add(seatsnum,gc);
        
        gc.gridx=1;
        gc.gridy=4;
        panel.add(datePicker,gc);
        
        gc.anchor=GridBagConstraints.SOUTH;
        gc.gridx=0;
        gc.gridy=5;
        gc.gridwidth=2; 
        panel.add(btnPanel,gc);
        
        add(panel);
	}

	public DisplayManager getDMgr() {
		return dmgr;
	}

	public void setDMgr(DisplayManager dmgr) {
		this.dmgr = dmgr;
		initComponents();
	}
	
}
