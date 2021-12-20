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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((available == null) ? 0 : available.hashCode());
		result = prime * result + ((brakes == null) ? 0 : brakes.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + (electric ? 1231 : 1237);
		result = prime * result + frameSize;
		result = prime * result + id;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + speeds;
		result = prime * result + ((wheels == null) ? 0 : wheels.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bike other = (Bike) obj;
		if (available == null) {
			if (other.available != null)
				return false;
		} else if (!available.equals(other.available))
			return false;
		if (brakes == null) {
			if (other.brakes != null)
				return false;
		} else if (!brakes.equals(other.brakes))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (electric != other.electric)
			return false;
		if (frameSize != other.frameSize)
			return false;
		if (id != other.id)
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (speeds != other.speeds)
			return false;
		if (wheels == null) {
			if (other.wheels != null)
				return false;
		} else if (!wheels.equals(other.wheels))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Bike [id=" + id + ", brand=" + brand + ", model=" + model + ", color=" + color + ", brakes=" + brakes
				+ ", wheels=" + wheels + ", available=" + available + ", electric=" + electric + ", frameSize="
				+ frameSize + ", speeds=" + speeds + ", price=" + price + "]";
	}
	
}
