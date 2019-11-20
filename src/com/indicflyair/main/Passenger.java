package com.indicflyair.main;

import java.text.ParseException;
import java.util.Calendar;

import com.indicflyair.partials.DateLabelFormatter;

public class Passenger {
	private String name,source, date;
	private int seats;
	private Query combinedFlight;
	public Passenger() {
		Calendar cal = Calendar.getInstance();
		DateLabelFormatter df=new DateLabelFormatter();
		this.name="";
		this.source="";
		try {
			this.date=df.valueToString(cal);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.seats=1;
		this.combinedFlight=null;
	}
	public Passenger(String name, String source, String date, int seats) {
		this.name = name;
		this.source = source;
		this.date = date;
		this.seats = seats;
		this.combinedFlight=null;
	}
	public String getName() {
		return name;
	}
	public String getSource() {
		return source;
	}
	public String getDate() {
		return date;
	}
	public int getSeats() {
		return seats;
	}
	public Query getCombinedFlight() {
		return combinedFlight;
	}
	public void setCombinedFlight(Query combinedFlight) {
		this.combinedFlight = combinedFlight;
	}
}
