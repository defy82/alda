package com.alda;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ParkingLot {
	
	public static void main(String[] args) throws FileNotFoundException {

		boolean flag = true;
		Lot parkingLot = null;
		
		Scanner in;
		if (args.length == 0)
			in = new Scanner(System.in);
		else 
			in = new Scanner(new File(args[0]));
		
		String input;
		
		while(flag) {
			
			if (in.hasNextLine())
				input = in.nextLine();
			else {
				flag = false;
				continue;
			}
			
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
