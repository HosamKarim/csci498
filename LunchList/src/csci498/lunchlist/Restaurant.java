package csci498.lunchlist;

import java.util.Calendar;



public class Restaurant {

	private String name = "";
	private String address = "";
	private String type="";
	private String notes="";
	private int day = 0;
	private int month = 0;
	private int year = 0;
	
	public String getName() { 
		return(name);
	}
	
	public void setName(String name) { 
		this.name = name;
	}
	
	public String getAddress() { 
		return(address);
	}
	
	public void setAddress(String address) { 
		this.address = address;
	}
	
	public String getType(){
		return(type);
	}
	
	public String toString(){
		return (getName());
	}
	
	public void setType(String type) { 
		this.type = type;
	}
	
	public String getNotes(){
		return (notes);
	}
	
	public void setNotes(String notes){
		this.notes = notes;
	}
	
	public int getDay(){
		return (day);
	}
	
	public void setDay(int day){
		this.day = day;
	}
	public int getMonth(){
		return (month);
	}
	
	public void setMonth(int month){
		this.month = month;
	}
	public int getYear(){
		return (year);
	}
	
	public void setYear(int year){
		this.year = year;
	}
}
