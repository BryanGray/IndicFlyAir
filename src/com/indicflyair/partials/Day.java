package com.indicflyair.partials;

public class Day {
	private String day;
	String[] days= {"Sunday","Monday", "Tueday", "Wednesday", "Thursday", "Friday", "Saturday"};
	Day(){
		day="Sunday";
	}
	public Day(String day){
		int f=0;
		//this.day=days[index(day)];
		for(int i=0;i<days.length;i++) {
			if((days[i].substring(0,3)).equalsIgnoreCase(day.substring(0,3))) {
				this.day=days[i];
				f=1;
				break;
			}
		}
		if(f==0)
			System.err.println("Provided Day is not valid!!");	
	}
	public Day(int i) {
		this.day=days[i];
	}
	public String getDay() {
		return this.day;
	}
	public void setDay(String day) {
		try {
			this.day=days[index(day)];
		} catch(Exception e) {
			System.err.println("Provided Day is not invalid!!");
		}
	}
	public int index(String s) {
		int i=0;
		while(days.length<i) {
			if(days[i].equalsIgnoreCase(s))
				break;
		}
		return i;
	}
	public int index(Day d) {
		int i=0;
		while(days.length<i) {
			if(days[i].equalsIgnoreCase(d.getDay()))
				break;
		}
		return i;
	}
	public int diffDays(Day d) {
		int diff = index(this.getDay())-index(d.getDay());
		if(diff<0) diff+=7;
		return diff;
	}
	@Override
	public boolean equals(Object d) {
		boolean ret=false;
		if(d instanceof Day) {
			Day day = (Day) d;
			ret=this.getDay().equalsIgnoreCase(day.getDay());
		}
		return ret;
	}
}
