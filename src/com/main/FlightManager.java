package com.main;
import java.util.ArrayList;

/*@author: Indic Amigo*/

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
        myMgr.domList = myMgr.dataMgr.loadDomestic("2016.spicejet.csv");
        myMgr.intList = myMgr.dataMgr.loadInternational("2016.silkair.csv");
       
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

}
