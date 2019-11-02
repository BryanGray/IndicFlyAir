package com.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.main.DisplayManager;
import com.main.Flight;
import com.main.Passenger;
import com.main.Query;

public class Screen3 extends JFrame implements Screen{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3600150961725512678L;
	private DisplayManager dmgr;

	public Screen3(DisplayManager dmgr) {        
        this.setDMgr(dmgr);
        initComponents();
	}
	
	public Screen3() {
		initComponents();
	}
	
	// Variables Declaration
	private JTable table;
	private JButton proceedButton, backButton;
	private JPanel panel;

	private void initComponents() {
		setTitle(AppName+" - "+forms[2]);
		setBounds(300, 300, 800, height);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel=new JPanel();
		Passenger p = this.getDMgr().getMgr().getPassenger();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(!p.getName().equalsIgnoreCase("")){
			ArrayList<Query> ql = dmgr.getMgr().getSearchMgr().searchFlights(p);
			/*if(ql.size()==0) {
				String msg="No available flights today";
				JOptionPane.showInternalMessageDialog(getContentPane(), msg, "Flight Info!", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
				getDMgr().showScreen1(dmgr);
			}*/
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            String flight[][]= new String[ql.size()][6];
            for(int i=0;i<ql.size();i++){
            	Query q=ql.get(i);
                Flight com[]=q.getComFlight();
                flight[i][0]=com[0].getSrc()+"-"+com[0].getDest()+"-"+com[1].getDest();
                flight[i][1]=com[0].getFlightNum()+"-"+com[1].getFlightNum();
                flight[i][2]=Integer.toString(q.getLayover());
                flight[i][3]=Integer.toString(q.getTotalTime());
                flight[i][4]=q.getDepTime().showTime();
                flight[i][5]=q.getArrTime().showTime();
            }
			
			
	        String colnames[]={"Route","Flight","Layover","Total-Time","Dept. Time", "Arr. Time"};
	        
	        
			
			//Table
	        table=new JTable(flight,colnames);
	        table.setBounds(30,40,200,200);
	        //Table Column Widths
	        table.getColumnModel().getColumn(1).setPreferredWidth(25);
	        table.getColumnModel().getColumn(2).setPreferredWidth(15);
	        table.getColumnModel().getColumn(3).setPreferredWidth(15);
	        table.getColumnModel().getColumn(4).setPreferredWidth(20);
	        table.getColumnModel().getColumn(5).setPreferredWidth(20);
	        
	        

	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
	        table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
	        table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
	        table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
	        table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
	        table.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
	        
	        
	        // adding it to JScrollPane 
	        JScrollPane sp = new JScrollPane(table); 
	        
	        // Button and Event     
	        proceedButton=new JButton("Confirm");
	        proceedButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {					
					if(table.getSelectionModel().isSelectionEmpty()) {
						String msg="Please Select An Option";
						JOptionPane.showInternalMessageDialog(getContentPane(), msg, "Not Selected!!", JOptionPane.OK_OPTION);
						
					} else {
						int row = table.getSelectedRow();
						p.setCombinedFlight(ql.get(row));
						getDMgr().showScreen4(dmgr);
					}					
				}
			});
	        backButton=new JButton("Back");
	        backButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	                getDMgr().showScreen2(dmgr);
	            }}
	        );		        
	
	        	        
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	        panel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80)));
	        panel.add(sp);
	        
	        // Button Panel
	        JPanel btnPanel=new JPanel();
	        proceedButton.setBounds(50,10,25,30);
	        backButton.setBounds(50,10,25,30);
	        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
	        

	        btnPanel.add(Box.createRigidArea(new Dimension(15, 0)));
	        btnPanel.add(backButton);
	        btnPanel.add(proceedButton);

	        panel.add(btnPanel);
	        
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
	
	public JPanel addLabelPanel(String str) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(str);
		panel.add(label);
		return panel;
	}
}
