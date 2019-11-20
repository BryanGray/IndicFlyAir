package com.indicflyair.gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.indicflyair.main.DisplayManager;
import com.indicflyair.main.Flight;
import com.indicflyair.main.Passenger;
import com.indicflyair.main.Query;
import com.indicflyair.partials.Time;

public class Screen3 extends JFrame implements Screen{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3600150961725512678L;
	private DisplayManager dmgr;
        private Screen5 screen5;
	public Screen3(DisplayManager dmgr) { 
		initComponents();
		this.setDMgr(dmgr);
	}

	public Screen3() {
		initComponents();
	}

	// Variables Declaration
	private JTable table;
	private JButton proceedButton, backButton;
	private JPanel panel;
	private String colnames[]={"Route","Flight","Layover","Total-Time","Dept. Time", "Arr. Time"};
	private DefaultTableModel dm = new DefaultTableModel(0, 5);
        private JMenuItem viewInfo;
	private void initComponents() {
		setTitle(AppName+" - "+forms[2]);
		setBounds(300, 300, 800, height);
		setIconImage(icon.getImage());
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		
		//Table
		table=new JTable();
		table.setBounds(30,40,200,200);
		dm.setColumnIdentifiers(colnames);
		table.setModel(dm);

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

		backButton=new JButton("Back");
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				getDMgr().showScreen2(dmgr);
			}}
				);		        

		
		JPopupMenu rightMenu = new JPopupMenu();
		viewInfo = new JMenuItem("View Info",infoIcon);
		
		rightMenu.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(rightMenu, new Point(0, 0), table));
                        if (rowAtPoint > -1) {
                            table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
				
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		rightMenu.add(viewInfo);
		table.setComponentPopupMenu(rightMenu);
		
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
                screen5=new Screen5();

	}
	public void initValues() {
		Passenger p = this.getDMgr().getMgr().getPassenger();

		if(!p.getName().equalsIgnoreCase("")){

			ArrayList<Query> ql = dmgr.getMgr().getSearchMgr().searchFlights(p);

			String trow[]=new String[6];
			dm.setRowCount(0);
			for(int i=0;i<ql.size();i++){
				Query q=ql.get(i);
				Flight com[]=q.getComFlight();
				trow[0]=com[0].getSrc()+"-"+com[0].getDest()+"-"+com[1].getDest();
				trow[1]=com[0].getFlightNum()+"-"+com[1].getFlightNum();
				trow[2]=new Time(q.getLayover()).showTime(1);
				trow[3]=new Time(q.getTotalTime()).showTime(1);
				trow[4]=q.getDepTime().showTime(2);
				trow[5]=q.getArrTime().showTime(2);
				dm.addRow(trow);
			}
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
                        viewInfo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int row=table.getSelectedRow();
					screen5.setQuery(ql.get(row));
                                        screen5.setVisible(true);
				}
			});
		}		

	}

	public DisplayManager getDMgr() {
		return dmgr;
	}

	public void setDMgr(DisplayManager dmgr) {
		this.dmgr = dmgr;
		initValues();
	}

}
