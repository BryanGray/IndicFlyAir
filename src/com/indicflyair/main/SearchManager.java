package com.indicflyair.main;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.indicflyair.partials.DateLabelFormatter;
import com.indicflyair.partials.Day;
import com.indicflyair.partials.Time;

public class SearchManager {
	private FlightManager mgr;
	
	public SearchManager(FlightManager mgr) {
		this.mgr = mgr;
	}
	
	public Query combineFlight(Flight intFlight, Flight domFlight) {
		Query q = new Query();
		q.getComFlight()[0]=domFlight;
		q.getComFlight()[1]=intFlight;
		q.setArrTime(intFlight.getArrTime());
		q.setDepTime(domFlight.getDepTime());
		Time t1=domFlight.getArrTime(), t2=intFlight.getDepTime();
		int layover=(int)t1.diffTime(t2);
		q.setLayover(layover);
		t1=domFlight.getDepTime(); t2=intFlight.getArrTime();
		int totalTime=(int)t1.diffTime(t2)-150;
		q.setTotalTime(totalTime);
		return q;
	}
	
	@SuppressWarnings("deprecation")
	public ArrayList<Flight> checkDomesticFlight(Passenger p) {
		ArrayList<Flight> f=new ArrayList<Flight>();
		for(int i=0; i< mgr.domList.size(); i++) {
			if(mgr.domList.get(i).getSrc().equalsIgnoreCase(p.getSource()) && mgr.domList.get(i).getDaysOfWeek().contains(new Day(stringToValue(p).getDay())))
				f.add(mgr.domList.get(i));
		}
		return f;
	}
	
	public ArrayList<Query> searchFlights(Passenger p){
		ArrayList<Query> queryList=new ArrayList<Query>();
		ArrayList<Flight> checkedDomList=checkDomesticFlight(p);			
		for(int i=0;i<checkedDomList.size();i++) {
			for(int j=0;j<mgr.intList.size();j++) {
				
				if(checkQuery(p, mgr.intList.get(j), checkedDomList.get(i))) {
					queryList.add(combineFlight(mgr.intList.get(j), checkedDomList.get(i)));					
				}
			}
		}
		return queryList;
	}
	
	public Date stringToValue(Passenger p) {
		DateLabelFormatter dlf=new DateLabelFormatter();
		Date travel=new Date();
		try {
			travel = (Date)dlf.stringToValue(p.getDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return travel;
	}
	
	public ArrayList<Query> sortFlights(ArrayList<Query> qList){
		Collections.sort(qList,Query.sortByTotalTime);
		return qList;
	}
	
	@SuppressWarnings("deprecation")
	public boolean checkQuery(Passenger p, Flight intFlight, Flight domFlight) {
		Time t1=domFlight.getArrTime(), t2=intFlight.getDepTime(),t;
		long layover=t1.diffTime(t2);
		if((t1.hours>t2.hours || (t1.minutes>t2.minutes && t1.hours==t2.hours)) && t1.days-t2.days<=0) {
			if(!intFlight.getDaysOfWeek().contains(new Day((stringToValue(p).getDay()+1) % 7)))
				return false;
			t=new Time(t2.hours*60+t2.minutes);
			t.days=1;
			layover=t1.diffTime(t);				
		} else {
			if(!intFlight.getDaysOfWeek().contains(new Day(stringToValue(p).getDay())))
				return false;
		}
		if(intFlight.getSrc().equalsIgnoreCase(domFlight.getDest()) && (layover>=120 && layover<=360) && (intFlight.getSeats()>=p.getSeats() && domFlight.getSeats()>=p.getSeats())) {
	        //System.out.println(layover+" "+intFlight.getFlightNum()+" "+domFlight.getFlightNum());
			return true;
		}
		
		return false;
	}
}