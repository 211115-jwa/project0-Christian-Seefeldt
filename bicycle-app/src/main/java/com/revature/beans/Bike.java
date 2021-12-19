package com.revature.beans;

public class Bike {
	private int id;
	private String brand;
	private String model; 
	private String color;
	private String brakes;
	private String wheels;
	private String available;
	private boolean electric;
	private int frameSize;
	private int speeds;
	private float price;
	
	public Bike() {
		id = 0;
		brand = "Basic";
		model = "Basic";
		color = "Basic";
		brakes = "Basic";
		wheels = "Basic";
		available= "Available";
		electric = false;
		speeds = 1;
		frameSize = 25;
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
	public String getModel() {
		return model;
	}
	public void setModel(String style) {
		this.model = style;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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
	public String isAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
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
	public int getSpeeds() {
		return speeds;
	}
	public void setSpeeds(int speeds) {
		this.speeds = speeds;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setId(int id) {
		this.id = id;
	}
}
