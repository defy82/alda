package com.alda;

public class ParkingSpot {
	
	private boolean allocated;
	private int spotNum;
	private Car parkedCar;
	
	public ParkingSpot (int num) {
		this.spotNum = num;
		this.allocated = false;
		this.parkedCar = null;
	}
	
	public boolean getStatus() {
		return allocated;
	}
	
	public int getSpotNum() {
		return spotNum;
	}
	
	public String getColor() {
		return parkedCar.color;
	}
	
	public String getRegNum() {
		return parkedCar.regNumber;
	}
	
	public void park(Car car) {
		allocated = true;
		parkedCar = car;
	}
	
}
