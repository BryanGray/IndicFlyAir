package com.indicflyair.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.indicflyair.main.DisplayManager;
import com.indicflyair.main.Passenger;
import com.indicflyair.partials.DateLabelFormatter;

public class Screen1 extends JFrame implements Screen{

	private static final long serialVersionUID = -6431500229629125807L;
	private DisplayManager dmgr;

	public Screen1(DisplayManager dmgr) {
		Screen.super.loadIcons();
		initComponents();
		this.setDMgr(dmgr);
	}

	public Screen1() {
		Screen.super.loadIcons();
		initComponents();
	}

	// Variables Declaration
	private JLabel namelabel,sourcecitylabel ,destlabel1,destlabel2,seatslabel,datelabel;
	private	JTextField nameField;
	private JDatePickerImpl datePicker;
	JComboBox<String>  sourcefield;
	JComboBox<Integer> seatsnum;
	private JButton okButton;
	private String msg;

	private void initComponents() {
		setTitle(AppName+" - "+forms[0]);
		setIconImage(icon.getImage());
		setBounds(300, 300, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Labels
		namelabel=new JLabel("Name:");
		sourcecitylabel=new JLabel("Source city: ");
		destlabel1=new JLabel("Destination city: ");
		destlabel2=new JLabel("Singapore (SIN)");
		seatslabel=new JLabel("No.of seats:");
		datelabel=new JLabel("Date of Travel: ");
		// Inputs
		nameField=new JTextField(15);
		nameField.setBorder(new LineBorder(Color.gray,1));
		nameField.setPreferredSize(new Dimension(15,25));

		sourcefield=new JComboBox<String>();
		for(String i:cities){
			sourcefield.addItem(i);
		}
		sourcefield.setPreferredSize(new Dimension(185,25));

		seatsnum=new JComboBox<Integer>();
		for(int i=1;i<=10;i++){
			seatsnum.addItem(i);
		}
		seatsnum.setPreferredSize(new Dimension(185,25));

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJFormattedTextField().setPreferredSize(new Dimension(160,25));
		datePicker.getJFormattedTextField().setBorder(new LineBorder(Color.gray,1));
		//datePicker=new JTextField(10);
		// Button and Event     
		okButton=new JButton("OK");
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String name=nameField.getText();
				String date=datePicker.getJFormattedTextField().getText();
				int tic=Integer.parseInt(seatsnum.getSelectedItem().toString());
				String source=sourcefield.getSelectedItem().toString();
				getDMgr().getMgr().setPassenger(new Passenger(name,source,date,tic));
				msg="";
				if(!date.equalsIgnoreCase("") && !name.equalsIgnoreCase("")) {
					getDMgr().showScreen2(getDMgr());
				}
				else {
					if(name.equalsIgnoreCase(""))
						msg+="Name cannot be empty";
					JOptionPane.showInternalMessageDialog(getContentPane(), msg, "Invalid Input!!", JOptionPane.INFORMATION_MESSAGE);
				}
			}});

		GridBagConstraints gc = new GridBagConstraints();
		JPanel panel=new JPanel();
		panel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80)));
		panel.setLayout(new GridBagLayout());

		gc.weightx=1;
		gc.weighty=1;

		gc.anchor=GridBagConstraints.WEST;

		gc.gridx=0;
		gc.gridy=0;
		gc.gridheight=1;
		panel.add(namelabel,gc);

		gc.gridx=0;
		gc.gridy=1;
		gc.gridheight=1;
		panel.add(sourcecitylabel,gc);

		gc.gridx=0;
		gc.gridy=2;
		gc.gridheight=1;
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
		gc.gridheight=1;
		panel.add(nameField,gc);

		gc.gridx=1;
		gc.gridy=1;
		gc.gridheight=1;
		panel.add(sourcefield,gc);

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
		gc.gridwidth=2;
		gc.gridx=0;
		gc.gridy=5;
		panel.add(okButton,gc);

		add(panel);

	}

	public void initValues() {
		Passenger passenger = this.getDMgr().getMgr().getPassenger();
		nameField.setText(passenger.getName());
		sourcefield.setSelectedItem(passenger.getSource());
		datePicker.getJFormattedTextField().setText(passenger.getDate());
		seatsnum.setSelectedItem(passenger.getSeats());

	}

	public DisplayManager getDMgr() {
		return dmgr;
	}

	public void setDMgr(DisplayManager dmgr) {
		this.dmgr = dmgr;
		initValues();
	}


}
