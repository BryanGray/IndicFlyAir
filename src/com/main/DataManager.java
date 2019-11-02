package com.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.partials.Day;

	public class DataManager {
		protected String arrtime, deptime, days , source, dest, fl_no;
	    private FlightManager mgr;
	   
	    public DataManager(FlightManager mgr) {
	        this.mgr = mgr;
	    }
			
			
		public StringTokenizer breakColumn(String oneLine){
			StringTokenizer st=new StringTokenizer(oneLine,"|");		
			return st;
		}
		
		public static ArrayList<Day> genDayArray(String s)
		{
			
			ArrayList<Day> d =new ArrayList<Day>();
			if(s.equalsIgnoreCase("DAILY"))
			{
				String[] days= {"Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
				for(String i:days) 
				{
					Day r=new Day(i);
					d.add(r);
				}
				
			}
			else 
			{
				StringTokenizer d1 = new StringTokenizer(s,", ");
				while(d1.hasMoreTokens()) 
				{
					String day=d1.nextToken();
					Day r=new Day(day);
					d.add(r);
				}
			}
			return d;
		}
		
		public  ArrayList<Flight> loadDomestic(String filename)
		{
			Flight f; ArrayList<Flight> FlightList = new ArrayList<Flight>(); 
			try {
				BufferedReader br =new BufferedReader(new FileReader(filename));
				String input; 
				DataManager dm=new DataManager(mgr);
				int count=0;
				while((input=br.readLine())!=null){
					if(count++>4)
					{
						StringTokenizer st =dm.breakColumn(input);
						source = st.nextToken();
						source=source.charAt(0)+source.toLowerCase().substring(1);
						dest = st.nextToken();
						dest=dest.charAt(0)+dest.toLowerCase().substring(1);
						days = st.nextToken();
						fl_no = st.nextToken();
						deptime = st.nextToken();
						arrtime = st.nextToken();
						f=new Flight(source,dest,genDayArray(days),fl_no,deptime,arrtime);
						FlightList.add(f);
					}
				}
				
				
				
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found");
			}
			catch(IOException e){
				System.out.println("IO Error");
			}
			return FlightList;
		}
		
		public ArrayList<Flight> loadInternational(String filename)
		{
			Flight f; BufferedReader br;
			ArrayList<Flight> FlightList = new ArrayList<Flight>();
			try {
				br=new BufferedReader(new FileReader(filename));
				String input;
				DataManager dm=new DataManager(mgr);
				int count=0;
				while((input=br.readLine())!=null){
					if(count++>4)
					{
						StringTokenizer st =dm.breakColumn(input);
						source = st.nextToken();
						source=(source.substring(0,source.indexOf('('))).trim();
						dest = "Singapore";
						days = st.nextToken();
						fl_no = st.nextToken();
						String t = st.nextToken();
						deptime = deptimeInt(t);
						arrtime = arrtimeInt(t);
						f=new Flight(source,dest,genDayArray(days),fl_no,deptime,arrtime);
						FlightList.add(f);
					}
				}
				
				
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found");
			}
			catch(IOException e){
				System.out.println("IO Error");
			}
			
			
			return FlightList;
		}
		
		public static String deptimeInt(String s)
		{
			StringTokenizer st= new StringTokenizer(s,"/");
			return st.nextToken();
		}
		
		public static String arrtimeInt(String s)
		{
			StringTokenizer st= new StringTokenizer(s,"/");
			st.nextToken();
			return st.nextToken();
		}

	   
	}

