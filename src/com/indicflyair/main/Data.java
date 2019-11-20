package com.indicflyair.main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.indicflyair.partials.Day;


public class Data {
	protected String arrtime, deptime, days , source, dest, fl_no;
	/*private FlightManager mgr;

	    public DataManager(FlightManager mgr) {
	        this.mgr = mgr;
	    }*/
	   //public static void main(String args[])
	public void clean()
        {
		Data dm=new Data();

		ArrayList<Flight> l1 = dm.loadDomestic("2016.spicejet.csv");
		ArrayList<Flight> l2 = dm.loadInternational("2016.silkair.csv");
		dm.dom_csv(l1);
		dm.int_csv(l2);			
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
			Data dm = new Data();
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

			br.close();


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
			Data dm=new Data();
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

	public  void dom_csv(ArrayList<Flight> l)
	{

		try {
			FileWriter csvWriter = new FileWriter("dom_Flight.csv");
			csvWriter.append("Number");
			csvWriter.append(",");
			csvWriter.append("Seats");
			csvWriter.append("\n");
			for(int i = 0; i< l.size();i++)
			{
				Flight f = l.get(i);

				csvWriter.append(String.valueOf(f.getFlightNum()));
				csvWriter.append(",");
				csvWriter.append(String.valueOf(f.getSeats()));
				csvWriter.append("\n");
			}
			csvWriter.flush();
			csvWriter.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}

	public void int_csv(ArrayList<Flight> l)
	{

		try {
			FileWriter csvWriter = new FileWriter("int_Flight.csv");
			csvWriter.append("Number");
			csvWriter.append(",");
			csvWriter.append("Seats");
			csvWriter.append("\n");
			for(int i = 0; i< l.size();i++)
			{
				Flight f = l.get(i);

				csvWriter.append(String.valueOf(f.getFlightNum()));
				csvWriter.append(",");
				csvWriter.append(String.valueOf(f.getSeats()));
				csvWriter.append("\n");
			}
			csvWriter.flush();
			csvWriter.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}


}

