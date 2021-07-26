/**
 * 
 */
package com.alda;

/**
 * @author parag
 *
 */
public class Lot {
	
	private ParkingSpot[] spots;
	private int size;
	private MinHeap queue;
	
	public Lot(int size) {
		this.size = size;
		this.spots = new ParkingSpot[size];
		this.queue = new MinHeap(this.size);
		
		for (int i = 0; i < size; i++) {
			this.spots[i] = new ParkingSpot(i + 1);
			queue.insert(i);
		}
	}
	
	public void parkCar (Car car) {
		if (queue.getSize() > 0) {
			int index = queue.remove();
			spots[index].park(car);
			System.out.printf("Alloted slot number: %d\n", spots[index].getSpotNum()); 
		}
		
		else System.out.println("Sorry the parkign lot is full!");
	}
	
	public void processCommand(String input) {
		
		String[] params = input.split(" ");
		String command = params[0];
		
		switch(command){
			case "park":
				parkCar(new Car(params[1], params[2]));
				break;
			default:
				System.out.println("Invalid command\n");
		}
		
	}

}
