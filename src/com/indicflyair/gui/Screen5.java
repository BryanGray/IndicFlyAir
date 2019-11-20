package com.indicflyair.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.indicflyair.main.Flight;
import com.indicflyair.main.Query;
import com.indicflyair.partials.Time;

public class Screen5 extends JFrame implements Screen {
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Query q;
	Screen5(Query q){
            this.q=q;
            initComponents();
            setQuery(q);
	}
        Screen5(){
            initComponents();
        }
        JLabel l1,l2,l3,t1,t2,t3;
        JTable table;
        private DefaultTableModel dm = new DefaultTableModel(0, 5);
        private JButton okButton;
        public void initComponents(){
                setTitle(AppName+" - "+forms[3]);
		setIconImage(icon.getImage());
		setBounds(300, 300, 600, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                
		GridBagConstraints gc = new GridBagConstraints();
                setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
                l1=new JLabel();
		l2=new JLabel();
		l3=new JLabel();
                t1=new JLabel();
		t2=new JLabel();
		t3=new JLabel();
                
                JPanel labelPanel=new JPanel();
                labelPanel.setLayout(new GridBagLayout());
                
                gc.weightx=1;
		gc.weighty=1;

		gc.anchor=GridBagConstraints.WEST;

		gc.gridx=0;
		gc.gridy=0;
		gc.gridheight=1;
                labelPanel.add(l1,gc);
                
		gc.gridx=0;
		gc.gridy=1;
		gc.gridheight=1;
                labelPanel.add(l2,gc);
                                
		gc.gridx=0;
		gc.gridy=2;
		gc.gridheight=1;
                labelPanel.add(l3,gc);
                
                gc.anchor=GridBagConstraints.LINE_START;

		gc.gridx=1;
		gc.gridy=0;
		gc.gridheight=1;
		labelPanel.add(t1,gc);

		gc.gridx=1;
		gc.gridy=1;
		gc.gridheight=1;
		labelPanel.add(t2,gc);

		gc.gridx=1;
		gc.gridy=2;                
		gc.gridheight=1;
		labelPanel.add(t3,gc);
                
                labelPanel.setBorder(new EmptyBorder(new Insets(0, 0, 20, 0)));
                
                String col[]= {"Path","FlightId","Total-Time","Dept-Time","Arrival-Time"};
                
		table=new JTable();
		table.setBounds(30, 40, 200, 50); 
                dm.setColumnIdentifiers(col);
		table.setModel(dm);
                
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
                
                okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                
                JScrollPane sp = new JScrollPane(table);		
		JPanel panel=new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setBorder(new EmptyBorder(new Insets(50, 80, 50, 80)));
		panel.add(labelPanel);
                panel.add(sp);
                panel.add(okButton);
		add(panel);
        }
        public void initValues(){
            Flight com[]=q.getComFlight();
            l1.setText("Route:  ");
            l2.setText("Total-Time:  ");
            l3.setText("Layover: ");
            t1.setText(com[0].getSrc()+"->"+com[0].getDest()+"->"+com[1].getDest());
            t2.setText(new Time(q.getTotalTime()).showTime(1));
            t3.setText(new Time(q.getLayover()).showTime(1));
            dm.setRowCount(0);
            String data1[]={com[0].getSrc()+"->"+com[0].getDest(),com[0].getFlightNum(),""+com[0].getDepTime().diffTime(com[0].getArrTime()),com[0].getDepTime().showTime(2),com[0].getArrTime().showTime(2)};
            dm.addRow(data1);
            String data2[]={com[1].getSrc()+"->"+com[1].getDest(),com[1].getFlightNum(),""+com[1].getDepTime().diffTime(com[1].getArrTime()),com[1].getDepTime().showTime(2),com[1].getArrTime().showTime(2)};
            dm.addRow(data2);            
        }
        public void setQuery(Query q){
            this.q=q;
            initValues();
        }
}
