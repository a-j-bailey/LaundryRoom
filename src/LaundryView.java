import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class LaundryView {
	
	static void parseInput() throws IOException{
		
		Scanner in = new Scanner(System.in);
		
		boolean repeat = false;
		
		do{
			repeat = false;
			
			System.out.println("What laundry room would you like to check?");
			
			String input = in.nextLine();
			
			input = input.toUpperCase();

			switch (input) {
			case "HELP":
				printHelp();
				break;
			case "INFO":
				printInfo();
				break;
			case "ALUMNI":
			case "HARKER 1":
			case "HARKER 2":
			case "HARKER 3":
			case "HICKS":
			case "HOPEMAN":
			case "KETLER":
			case "LINCOLN":
			case "MAP NORTH 1":
			case "MAP NORTH 2":
			case "MAP NORTH 3":
			case "MAP SOUTH":
			case "MAP WEST":
			case "MEP":
			case "MEMORIAL":
				launchRoom(input);
				break;
			default:
				System.out.println("Please type a valid name."
						+ "\nType 'HELP' for a list of valid names.\n");
				repeat = true;
			}
		} while (repeat);
		in.close();
	}
	
	static void printHelp() throws IOException{
		System.out.println("Here are the valid dorm names:");
		System.out.println("Alumni"
				+ "\nHarker 1"
				+ "\nHarker 2"
				+ "\nHarker 3"
				+ "\nHicks"
				+ "\nHopeman"
				+ "\nKetler"
				+ "\nLincoln"
				+ "\nMAP North 1"
				+ "\nMAP North 2"
				+ "\nMAP North 3"
				+ "\nMAP South"
				+ "\nMAP West"
				+ "\nMEP"
				+ "\nMemorial");
		parseInput();
	}
	
	static void printInfo(){
		
	}
	
	static int getRoomCode(String roomName){
		
		int roomCode = 0;
		
		switch (roomName) {
		case "ALUMNI":
			roomCode = 39390311;
			break;
		case "HARKER 1":
			roomCode = 3939032;
			break;
		case "HARKER 2":
			roomCode = 3939033;
			break;
		case "HARKER 3":
			roomCode = 3939031;
			break;
		case "HICKS":
			roomCode = 39390313;
			break;
		case "HOPEMAN":
			roomCode = 39390314;
			break;
		case "KETLER":
			roomCode = 3939034;
			break;
		case "LINCOLN":
			roomCode = 3939039;
			break;
		case "MAP NORTH 1":
			roomCode = 3939038;
			break;
		case "MAP NORTH 2":
			roomCode = 3939037;
			break;
		case "MAP NORTH 3":
			roomCode = 3939036;
			break;
		case "MAP SOUTH":
			roomCode = 39390310;
			break;
		case "MAP WEST":
			roomCode = 39390315;
			break;
		case "MEP":
			roomCode = 3939035;
			break;
		case "MEMORIAL":
			roomCode = 39390312;
			break;
		}
		return roomCode;
	}
	
	static void launchRoom(String roomName) throws IOException{
		
		System.out.println("\n\n\nLoading...\n\n\n");
		
		int roomCode = getRoomCode(roomName);
		String roomAddress = ("http://classic.laundryview.com/classic_laundry_room_ajax.php?lr=" + roomCode);
		URL url = new URL(roomAddress);
		Scanner in = new Scanner(url.openStream());
		
		ArrayList<machine> roomMachines = new ArrayList<>();
		
		in.useDelimiter("<|\\>|\\_");
		
		String type = "";
		int machineNumber = 0;
		String status = "";
		int time = 0;
		
		System.out.println("NUMBER:\tTYPE:\t\tSTATUS:\t\t\tTIME LEFT:");
		
		while(in.hasNext()){
			String section = in.next();
			if(section.equals("washer")){
				type = "Washer";
			} else if(section.equals("dryer")){
				type = "Dryer";
			} else if(section.equals("div class=\"desc\"")){
				String info = in.next();
				Scanner infoScanner = new Scanner(info);
				machineNumber = infoScanner.nextInt();
				infoScanner.close();
			} else if(section.equals("span class=\"stat\"")){
				String info = in.next();
				Scanner infoScanner = new Scanner(info);
				String next = infoScanner.next();
				if(next.equals("unknown")){
					status = "Offline\t";
				} else if (next.equals("available")) {
					status = "Available";
					time = 0;
				} else if (next.equals("cycle")){
					status = "Cycle Ended";
					time = 0;
				} else {
					status = "In Use\t";
					if (next.equals("est.")){
						infoScanner.next();
						infoScanner.next();
						time = infoScanner.nextInt();
					} else if (next.equals("ended")){
						time = infoScanner.nextInt();
					}
				}
				infoScanner.close();
				System.out.println(machineNumber + "\t" + type
						+ "\t\t" + status + "\t\t" + time + " minutes left.");
				machine newMachine = new machine(machineNumber, type, time, status);
				roomMachines.add(newMachine);
			}
		}
		in.close();
		System.out.println("");
		parseInput();
	}
	
	public static void main(String[] args) throws IOException {
				
		parseInput();
								
	}
}