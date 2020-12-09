package com.spring.event;

public class Event {
	private double id;
	public String name;
	public String date;
	public String time;
	public String duration;
	public double price;
	public String status;
	
	public Event(double id, String name, String date, String time, String duration, double price, String status) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.price = price;
		this.status = "in progress";
	}
	
	public double getID() {
		return id;
	}
	
	public void setID(Long id2) {
		this.id = id2;
	}
}
