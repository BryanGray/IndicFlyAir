package com.indicflyair.main;
import java.util.Comparator;

import com.indicflyair.partials.Time;

public class Query{
	private Flight comFlight[]=new Flight[2];
	private int totalTime, layover;
	private Time depTime, arrTime;
	public Flight[] getComFlight() {
		return comFlight;
	}
	public void setComFlight(Flight[] comFlight) {
		this.comFlight = comFlight;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public int getLayover() {
		return layover;
	}
	public void setLayover(int layover) {
		this.layover = layover;
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
	
	//sort by totalTime
	public static Comparator<Query> sortByTotalTime = new Comparator<Query>() {
		
		@Override
		public int compare(Query q1,Query q2) {
			return (q1.getLayover()-q2.getLayover());
		}
	};
}
