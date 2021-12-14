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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSpeeds() {
		return speeds;
	}
	public void setSpeeds(String speeds) {
		this.speeds = speeds;
	}
	public String getBrakes() {
		return brakes;
	}
	public void setBrakes(String brakes) {
		this.brakes = brakes;
	}
	public String getWheels() {
		return wheels;
	}
	public void setWheels(String wheels) {
		this.wheels = wheels;
	}
	public boolean isElectric() {
		return electric;
	}
	public void setElectric(boolean electric) {
		this.electric = electric;
	}
	public int getFrameSize() {
		return frameSize;
	}
	public void setFrameSize(int frameSize) {
		this.frameSize = frameSize;
	}
	public int getWeightLimit() {
		return weightLimit;
	}
	public void setWeightLimit(int weightLimit) {
		this.weightLimit = weightLimit;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setId(int id) {
		this.id = id;
	}
}
