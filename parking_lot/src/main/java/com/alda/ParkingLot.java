package com.alda;

import java.util.Scanner;

public class ParkingLot {
	
	public static void main(String[] args) {

		boolean flag = true;
		Lot parkingLot = null;
		
		Scanner in = new Scanner(System.in);
		String input;
		
		while(flag) {
			
			input = in.nextLine();
			String[] params = input.split(" ");
			String command = params[0];
			
			if (command.equals("create_parking_lot")) {
				parkingLot = new Lot(Integer.valueOf(params[1]));
			}
			
			else {
				parkingLot.processCommand(input);
			}

		}
		
		in.close();
		
	}
}
