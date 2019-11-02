package com.main;
import java.util.ArrayList;

import com.partials.Day;
import com.partials.Time;

public class Flight {
	private String flightNum, src,dest;
	protected String airline;
	private ArrayList<Day> daysOfWeek;
	protected Time depTime, arrTime;
	public String getSrc() {
		return src;
	}


	public void setSrc(String src) {
		this.src = src;
	}


	public String getDest() {
		return dest;
	}


	public void setDest(String dest) {
		this.dest = dest;
	}


	public String getAirline() {
		return airline;
	}


	public void setAirline(String airline) {
		this.airline = airline;
	}


	public ArrayList<Day> getDaysOfWeek() {
		return daysOfWeek;
	}


	public void setDaysOfWeek(ArrayList<Day> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}


	public Time getDepTime() {
		 return depTime;
	}


	public void setDepTime(Time depTime) {
		this.depTime = depTime;
	}


	public Time getArrTime() {
		 return arrTime;
	}


	public void setArrTime(Time arrTime) {
		this.arrTime = arrTime;
	}


	public int getSeats() {
		return seats;
	}


	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getFlightNum() {
		return flightNum;
	}


	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}
	
	private int seats;


	public Flight(String src,String dest, ArrayList<Day> daysOfWeek, String flightNum, String depTime, String arrTime)	{
		this.src=src;
		this.dest=dest;
		this.daysOfWeek=daysOfWeek;
		this.setFlightNum(flightNum);
		this.depTime= new Time(depTime);
		this.arrTime=new Time(arrTime);
		this.airline="";
		this.seats=150;
	}
}
