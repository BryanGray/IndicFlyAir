package com.indicflyair.main;
import java.util.ArrayList;

/*@author: Indic Amigo
 *@version: 1.0.1*/

public class FlightManager {
	private DataManager dataMgr;
	private DisplayManager displayMgr;
    private SearchManager searchMgr;
    Passenger passenger=new Passenger();
    
    public ArrayList<Flight> domList, intList;
    public static void main(String[] args) {
    	
		FlightManager myMgr = new FlightManager();
	       
        // create all managers
        myMgr.dataMgr = new DataManager(myMgr);
        myMgr.displayMgr = new DisplayManager(myMgr);
        myMgr.setSearchMgr(new SearchManager(myMgr));
       
        // initialise data
        if(args.length==2) {
        	myMgr.domList = myMgr.dataMgr.readCsv("dom_Flight.csv", myMgr.dataMgr.loadDomestic(args[0]));
            myMgr.intList = myMgr.dataMgr.readCsv("int_Flight.csv", myMgr.dataMgr.loadInternational(args[1]));
        } else if(args.length==0) {
            myMgr.domList = myMgr.dataMgr.readCsv("dom_Flight.csv", myMgr.dataMgr.loadDomestic("2016.spicejet.csv"));
            myMgr.intList = myMgr.dataMgr.readCsv("int_Flight.csv", myMgr.dataMgr.loadInternational("2016.silkair.csv"));
        } else if(args.length==1 && args[0].equalsIgnoreCase("clean")){
            Data d=new Data();
            d.clean();
            System.out.println("Data File Not Found");
            System.exit(0);
        }
        else{
            System.out.println("Data File Not Found");
            System.exit(0);
        }
        
        // show screen1
        myMgr.displayMgr.showScreen1(myMgr.displayMgr);
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public SearchManager getSearchMgr() {
		return searchMgr;
	}

	public void setSearchMgr(SearchManager searchMgr) {
		this.searchMgr = searchMgr;
	}
        public DataManager getDataMgr(){
            return dataMgr;
        }
}
