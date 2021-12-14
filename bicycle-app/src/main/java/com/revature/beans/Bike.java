package com.revature.beans;

public class Bike {
	private int id;
	private String brand;
	private String style;
	private String color;
	private String speeds;
	private String brakes;
	private String wheels;
	private boolean electric;
	private int frameSize;
	private int weightLimit;
	private int price;
	
	public Bike() {
		id = 0;
		brand = null;
		style = null;
		color = null;
		speeds = null;
		brakes = null;
		wheels = null;
		electric = false;
		frameSize = 26;
		weightLimit = 200;
		price = 500;		
	}
	
	public int getId() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;	
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
}
