/**
 * 
 */
package com.alda;

import java.util.ArrayList;

/**
 * @author parag
 *
 */
public class Lot {
	
	final String park = "park";
	final String exit = "leave";
	final String status = "status";
	final String slotNumForColor = "slot_numbers_for_cars_with_colour";
	final String slotNumForRegNum = "slot_number_for_registration_number";
	final String regNumForColor = "registration_numbers_for_cars_with_colour";
	
	private int size;
	private MinHeap queue;
	private ParkingSpot[] spots;

	public Lot(int size) {
		this.size = size;
		this.spots = new ParkingSpot[size];
		this.queue = new MinHeap(this.size);
		
		for (int i = 0; i < size; i++) {
			this.spots[i] = new ParkingSpot(i + 1);
			queue.insert(i);
		}
	}

	
	public void processCommand(String input) {
		
		String[] params = input.split(" ");
		String command = params[0];
		
		ArrayList<ParkingSpot> result = null;
		
		switch(command){
			case park:
				parkCar(new Car(params[1], params[2]));
				break;
			case exit:
				exitCar(Integer.valueOf(params[1]));
				break;
			case status:
				status();
				break;
			case regNumForColor:
				result = search(spots, (ParkingSpot spot) -> spot.getStatus() && spot.getColor().equals(params[1]));
				printDetails(result, (ParkingSpot spot) -> spot.getRegNum());
				break;
			case slotNumForColor:
				result = search(spots, (ParkingSpot spot) -> spot.getStatus() && spot.getColor().equals(params[1]));
				printDetails(result, (ParkingSpot spot) -> String.valueOf(spot.getSpotNum()));
				break;
			case slotNumForRegNum:
				result = search(spots, (ParkingSpot spot) -> spot.getStatus() && spot.getRegNum().equals(params[1]));
				printDetails(result, (ParkingSpot spot) -> String.valueOf(spot.getSpotNum()));
				break;
				
			default:
				System.out.println("Invalid command\n");
		}
		
	}
	
	
	private void parkCar (Car car) {
		if (queue.getSize() > 0) {
			int index = queue.remove();
			spots[index].park(car);
			System.out.printf("Alloted slot number: %d\n", spots[index].getSpotNum()); 
		}
		
		else System.out.println("Sorry the parking lot is full!");
	}
	
	private void exitCar (int index) {
		spots[index - 1].clear();
		queue.insert(index - 1);
	}
	
	private void status() {
		System.out.printf("Slot No. \t Registration No. \t Colour\n");
		for (int i = 0; i < size; i++) {
			if (spots[i].getStatus()) System.out.printf("%d \t\t %s \t\t %s\n", spots[i].getSpotNum(), spots[i].getRegNum(), spots[i].getColor());
		}
	}

	public interface Var {
		String quality(ParkingSpot spot);
	}
	
	private void printDetails(ArrayList<ParkingSpot> input, Var qual) {
		for (int i = 0; i < input.size(); i++) System.out.println(qual.quality(input.get(i)));
	}
	
	public interface Filter {
		boolean test(ParkingSpot spot);
	}
	
	private ArrayList<ParkingSpot> search(ParkingSpot[] spots, Filter filter){
		ArrayList<ParkingSpot> result = new ArrayList<ParkingSpot>();
		
		for (int i = 0; i < size; i++) {
			if (filter.test(spots[i])) result.add(spots[i]);
		}
		
		return result;
	}

}
