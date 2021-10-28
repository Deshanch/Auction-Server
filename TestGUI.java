//main class to test GUI
import java.util.ArrayList;
import java.lang.*;

public class TestGUI {

	//initialize arraylist of sublists : for auction prices
	public static ArrayList< ArrayList<String> > auction =  new ArrayList<ArrayList<String> >();
	
	
	//initialize arraylist of sublists : for bidding records
	public static ArrayList< ArrayList<String> > bids =  new ArrayList<ArrayList<String> >();
	
	
	public static void main(String[] args) {
		
		
		//populate arraylist of auction prices with data 
		//remove after combining
		ArrayList<String> a1 = new ArrayList<String>();
		a1.add("FB");
		a1.add("100.00");
		auction.add(a1);
		
		
		ArrayList<String> a2 = new ArrayList<String>();
		a2.add("VRTU");
		a2.add("200.00");
		auction.add(a2);
		
		ArrayList<String> a3 = new ArrayList<String>();
		a3.add("MSFT");
		a3.add("300.00");
		auction.add(a3);
		
		
		ArrayList<String> a4 = new ArrayList<String>();
		a4.add("GOOGL");
		a4.add("400.00");
		auction.add(a4);		
		
		ArrayList<String> a5 = new ArrayList<String>();
		a5.add("YHOO");
		a5.add("500.00");
		auction.add(a5);
		
		
		ArrayList<String> a6 = new ArrayList<String>();
		a6.add("XLNX");
		a6.add("600.00");
		auction.add(a6);
		
		ArrayList<String> a7 = new ArrayList<String>();
		a7.add("TSLA");
		a7.add("700.00");
		auction.add(a7);
		
		
		ArrayList<String> a8 = new ArrayList<String>();
		a8.add("TXN");
		a8.add("800.00");
		auction.add(a8);			
		
			
		//populate arraylist of bid records with data
		//remove after combining
		ArrayList<String> b1 = new ArrayList<String>();
		b1.add("FB");
		b1.add("100.00");
		b1.add("Perera");
		b1.add("01-09-2020");
		b1.add("12:00");
		bids.add(b1);	
		
		
		ArrayList<String> b2 = new ArrayList<String>();
		b2.add("VRTU");
		b2.add("200.00");
		b2.add("Praveen");
		b2.add("02-09-2020");
		b2.add("13:00");
		bids.add(b2);	
		
		ArrayList<String> b3 = new ArrayList<String>();
		b3.add("MSFT");
		b3.add("300.00");
		b3.add("Deshan");
		b3.add("03-09-2020");
		b3.add("14:00");
		bids.add(b3);	
		
		
		ArrayList<String> b4 = new ArrayList<String>();
		b4.add("GOOGL");
		b4.add("400.00");
		b4.add("Silva");
		b4.add("04-09-2020");
		b4.add("15:00");
		bids.add(b4);		
		
		
		ArrayList<String> b5 = new ArrayList<String>();
		b5.add("YHOO");
		b5.add("500.00");
		b5.add("John");
		b5.add("05-09-2020");
		b5.add("16:00");
		bids.add(b5);	
		
		
		ArrayList<String> b6 = new ArrayList<String>();
		b6.add("XLNX");
		b6.add("600.00");
		b6.add("Anne");
		b6.add("06-09-2020");
		b6.add("17:00");
		bids.add(b6);	
		
		ArrayList<String> b7 = new ArrayList<String>();
		b7.add("TSLA");
		b7.add("700.00");
		b7.add("Mathew");
		b7.add("07-09-2020");
		b7.add("18:00");
		bids.add(b7);	
		
		
		ArrayList<String> b8 = new ArrayList<String>();
		b8.add("TXN");
		b8.add("800.00");
		b8.add("Charles");
		b8.add("08-09-2020");
		b8.add("19:00");
		bids.add(b8);	
		
		
		
		
		//create serverGUI object
		ServerGUI serverGui = new ServerGUI(bids);
				
	}

	
	
	//method to get price of a symbol
	//copy this to Server class
	public static String getPrice(String symbol) {
				
		int n = auction.size();
		for (int i=0; i<n; i++) {
			ArrayList<String> auction_row = auction.get(i);
			
			if(symbol.equals(auction_row.get(0))) {
				return auction_row.get(auction_row.size()-1);
			}			
		}		
		return null;		
	}
	 
		
}