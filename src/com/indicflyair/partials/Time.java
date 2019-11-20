package com.indicflyair.partials;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;


public class Time {
	public int days, hours, minutes, seconds;
	
	public Time(){
		days=0; hours=0; minutes=0; seconds=0;
	}
	public Time(int min){
            minutes=min%60;
            hours=min/60;
        }
	@SuppressWarnings("deprecation")
	public Time(String s){
		s=s.toLowerCase();

		StringTokenizer st=new StringTokenizer(s," :+");
		if(s.contains("am") || s.contains("pm")) {
			SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm a");
			try {
				Date d = dateFormat.parse(s);
				hours=d.getHours();
				minutes = d.getMinutes();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}			
		} else {
			String t=st.nextToken();
			hours=Integer.parseInt(t.substring(0,2));
			minutes=Integer.parseInt(t.substring(2,4));
			if(st.hasMoreTokens())
				days=Integer.parseInt(st.nextToken());
			else
				days=0;
		}
		seconds=0;
	}
	public String showTime(int i)
	{
		String s="";
		switch(i){
                    case 1:
                        s= hours+"hr "+minutes+"min";
                        break;
                    case 2:
                        if(hours<10 && minutes < 10)
                    {
                            s = "0"+Integer.toString(hours)+":0"+Integer.toString(minutes);
                    }
                    else if(hours<10 && minutes >=10)
                            s = "0"+Integer.toString(hours)+":"+Integer.toString(minutes);
                    else if(hours>=10 && minutes >=10)
                            s = Integer.toString(hours)+":"+Integer.toString(minutes);
                    else
                            s = Integer.toString(hours)+":0"+Integer.toString(minutes);

                        break;
                    default:
                    if(hours<10 && minutes < 10)
                    {
                            s = "0"+Integer.toString(hours)+":0"+Integer.toString(minutes);
                    }
                    else if(hours<10 && minutes >=10)
                            s = "0"+Integer.toString(hours)+":"+Integer.toString(minutes);
                    else if(hours>=10 && minutes >=10)
                            s = Integer.toString(hours)+":"+Integer.toString(minutes);
                    else
                            s = Integer.toString(hours)+":0"+Integer.toString(minutes);

                    s = s +"+"+Integer.toString(days);
                }
		return s;
	}
	public long diffTime(Time t) {
		
		SimpleDateFormat format =new SimpleDateFormat("HH:mm+D");
		Date date1,date2;
		int flag=0;
		long difference=0;
		try {
			if((this.hours>t.hours || (this.hours==t.hours && this.minutes>t.minutes) && this.days-t.days<=0)) {
				t.days=1;
				flag=1;
			}
			
			date1 = format.parse(this.showTime(0));
			date2 = format.parse(t.showTime(0));
			difference = date2.getTime() - date1.getTime();
			if(flag==1) {
				t.days=0;
			}
		} catch (ParseException e) {
			System.err.println("Parse Error");
		} 
		return difference/60000;
	}
	/*public static void main(String[] args) {
		Time t1=new Time("0340");
		Time t2=new Time("2310");
		System.out.println(t1.diffTime(t2));
	}*/
}
