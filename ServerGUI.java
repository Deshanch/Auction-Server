//server GUI : view of server

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.TimerTask;


public class ServerGUI extends JFrame {
	
	//price table
	//table to show details of selected 8 items		
	JTable  table = new JTable(){
		
		//disable editing cells in the table
		public boolean isCellEditable(int row, int column) {
            return false;
         }
	};	   
	
	
	//record table
	//table to show bidding records
	JTable  record = new JTable(){
		
		//disable editing cells in the table
		public boolean isCellEditable(int row, int column) {
            return false;
         }
	};	
	
	DefaultTableModel model = new DefaultTableModel();	//for price table
	DefaultTableModel model2 = new DefaultTableModel();	//for record table

	
	//initialize column names for price table
	String [] columns = {"Symbol","Name","Current Price"};	
		
	//Initialize symbol column & name column content for price table
	String [] symbols = {"FB", "VRTU", "MSFT", "GOOGL", "YHOO", "XLNX", "TSLA", "TXN"};
	String [] names = {"Facebook", "Virtusa Corporation - common stock", "Microsoft Corporation - Common Stock", "Google Inc. - Class A Common Stock","Yahoo! Inc. - Common Stock", "Xilinx","Tesla Motors","Texas Instruments Incorporated - Common Stock"};
		

	//initialize column names for record table
	String [] record_columns = {"Stock Item", "Price", "Client", "Date", "Time"};
	
	
    public ServerGUI(ArrayList< ArrayList<String> > bids) {
		
    	//price table
    	model.setColumnIdentifiers(columns);	
		table.setModel(model);
		table.setRowHeight(table.getRowHeight() + 20);
		
    	//record table
    	model2.setColumnIdentifiers(record_columns);	
		record.setModel(model2);
		record.setRowHeight(record.getRowHeight() + 20);
				
		
		
		//update record table : use data in arraylist bids
		for(int i=0; i<bids.size(); i++) {
			model2.addRow(new Object[] {bids.get(i).get(0), bids.get(i).get(1), bids.get(i).get(2), bids.get(i).get(3), bids.get(i).get(4)});		
		}
		
		
		
		//update price table
	    //add rows with symbol, name and price
		//rename class name "TestGUI" to Server class, when combining
		for(int i=0; i<8; i++) {
			model.addRow(new Object[] {symbols[i],names[i],TestGUI.getPrice(symbols[i])});		
		}
		

		//update GUI every 500ms 
		//rename class name "TestGUI" to Server class, when combining
		new java.util.Timer().schedule(new TimerTask(){
			public void run() {
				
				//update price table
				for(int i=0; i<8; i++) {
					table.getModel().setValueAt(TestGUI.getPrice(symbols[i]),i,2);
				}
				
				//update records table
				for(int i=0; i<bids.size(); i++) {
					model2.addRow(new Object[] {bids.get(i).get(0), bids.get(i).get(1), bids.get(i).get(2), bids.get(i).get(3), bids.get(i).get(4)});		
				}
				
			} 		
		},0,500);
		
		
		//frame properties & layout
		JFrame frame = new JFrame();		
		frame.setSize(1000,1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Auction Server");

		
	   		
		JScrollPane scrollPane1 = new JScrollPane(table);
		JScrollPane scrollPane2 = new JScrollPane(record);
		
		scrollPane1.setBorder(BorderFactory.createTitledBorder ("Current Prices For Selected Stock Items"));
		scrollPane2.setBorder(BorderFactory.createTitledBorder ("Record Of Auctions Offered By Clients"));
		
		
		frame.add(scrollPane1, BorderLayout.CENTER);
		frame.add(scrollPane2, BorderLayout.SOUTH);
		frame.validate();
		
							
    } 
    	
   
}