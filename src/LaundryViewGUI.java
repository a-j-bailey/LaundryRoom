import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
//import java.awt.Rectangle;
//import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
//import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//import javax.swing.plaf.basic.BasicProgressBarUI;


public class LaundryViewGUI {
	
	static Color background = new Color(0, 0, 0, 90);
	static Color headerBackground = new Color(0, 0, 0, 80);
	static Color transparent = new Color(0, 0, 0, 0);
	static Color availableMachine = new Color(87, 138, 53, 150);
	static Color otherMachine = new Color(39, 51, 56, 200);
	
	static int getRoomCode(String roomName){
		
		int roomCode = 0;
		
		switch (roomName) {
		case "Alumni":
			roomCode = 39390311;
			break;
		case "Harker 1st Floor":
			roomCode = 3939032;
			break;
		case "Harker 2nd Floor":
			roomCode = 3939033;
			break;
		case "Harker 3rd Floor":
			roomCode = 3939031;
			break;
		case "Hicks":
			roomCode = 39390313;
			break;
		case "Hopeman":
			roomCode = 39390314;
			break;
		case "Ketler":
			roomCode = 3939034;
			break;
		case "Lincoln":
			roomCode = 3939039;
			break;
		case "MAP North 1st Floor":
			roomCode = 3939038;
			break;
		case "MAP North 2nd Floor":
			roomCode = 3939037;
			break;
		case "MAP North 3rd Floor":
			roomCode = 3939036;
			break;
		case "MAP South":
			roomCode = 39390310;
			break;
		case "MAP West":
			roomCode = 39390315;
			break;
		case "MEP":
			roomCode = 3939035;
			break;
		case "Memorial":
			roomCode = 39390312;
			break;
		}
		return roomCode;
	}
	
	static void launchInfoPopUp(){
		
		JDialog info = new JDialog();
		info.setLocationRelativeTo(null);
		info.setResizable(false);
		info.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Container c = info.getContentPane();
		c.setBackground(Color.BLACK);
		Dimension d = new Dimension(400,200);
		c.setPreferredSize(d);
		/*
		JTextPane infoText = new JTextPane(
				"This software was built for fun in my free time "
				+ "and may not be updated regularly."
				+ "If you're experiencing problems with this program, "
				+ "the status of the laundry machines on campus can also be "
				+ "viewed through www.laundryview.com/gcc."
				+ "\n\n\n"
				+ "<strong>©</strong> 2017 Adam Bailey (and definitely not Stephan Nash)");
		infoText.setContentType("text/html");
		//infoText.setLineWrap(true);
		//infoText.setWrapStyleWord(true);
		info.add(infoText);
		*/
		info.pack();
		info.setResizable(false);
		info.setVisible(true);
		
	}
	
	static void launchGUI() throws IOException, InterruptedException{
		
		String[] roomNames = {"Alumni",
				"Harker 1st Floor",
				"Harker 2nd Floor",
				"Harker 3rd Floor",
				"Hicks",
				"Hopeman",
				"Ketler",
				"Lincoln",
				"MAP North 1st Floor",
				"MAP North 2nd Floor",
				"MAP North 3rd Floor",
				"MAP South",
				"MAP West",
				"MEP",
				"Memorial"};
		
		//BUILDS NEW WINDOW		
		JFrame lvWindow = new JFrame("GCC - Laundry View");
		
		JLabel backgroundImage = new JLabel(new ImageIcon(ImageIO.read(new File("src/UI/backgroundImage.jpg"))));
		backgroundImage.setLayout(new BorderLayout());
		lvWindow.setContentPane(backgroundImage);
		
		lvWindow.setLocationRelativeTo(null);
		lvWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lvWindow.setResizable(false);
						
		//HEADER JPANEL
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
		JLabel LV = new JLabel("LaundryView");
		
		JLabel gccCrest = new JLabel(new ImageIcon(ImageIO.read(new File("src/UI/GCC_Crest.png"))));
		
		LV.setFont(new Font("Sans Serif", Font.BOLD, 50));
		LV.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		LV.setForeground(Color.WHITE);
		
		headerPanel.setBackground(headerBackground);
		headerPanel.add(gccCrest);
		headerPanel.add(LV);
		lvWindow.add(headerPanel, BorderLayout.PAGE_START);
		
		//SIDE BAR JPANEL
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BorderLayout());
		sidePanel.setPreferredSize(new Dimension(200, 400));
		sidePanel.setOpaque(false);
		
		JList<String> roomSelector = new JList<String>(roomNames);
		roomSelector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roomSelector.setForeground(Color.WHITE);
		roomSelector.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		roomSelector.setBackground(headerBackground);
				
		Image icon = ImageIO.read(new File("src/UI/information.png"));
		JButton info = new JButton(new ImageIcon(icon));
		info.setBackground(background);
		info.setContentAreaFilled(false);
		info.setBorderPainted(false);
		info.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(lvWindow,
						"This software was built for fun in my free time. "
								+ "\nTherefore, I will not be updating it regularly"
								+ "\n\nIf you're experiencing problems with this program, "
								+ "\nthe status of the laundry machines on campus can also be "
								+ "\nviewed through www.laundryview.com/gcc."
								+ "\n\n\n"
								+ "© 2017 Adam Bailey"
								+ "\nabcreative.io",
								"Info", JOptionPane.PLAIN_MESSAGE);
				//launchInfoPopUp();
			}
		});
		
		sidePanel.add(roomSelector, BorderLayout.CENTER);
		sidePanel.add(info, BorderLayout.PAGE_END);
		lvWindow.add(sidePanel, BorderLayout.LINE_START);
		
		//MAIN VIEW JPANEL
		JPanel roomInfo = new JPanel();
		roomInfo.setLayout(new BoxLayout(roomInfo, BoxLayout.X_AXIS));
		roomInfo.setPreferredSize(new Dimension(800,400));
		roomInfo.setOpaque(false);
		
		JLabel roomPrompt = new JLabel("Please Select A Room...");
		roomPrompt.setFont(new Font("Sans Serif", Font.BOLD, 20));
		roomPrompt.setForeground(Color.WHITE);
		
		roomInfo.add(Box.createHorizontalGlue());
		roomInfo.add(roomPrompt);
		roomInfo.add(Box.createHorizontalGlue());

		lvWindow.add(roomInfo, BorderLayout.LINE_END);
		
		//FOOTER JPANEL
		JLabel text = new JLabel("© 2017 Adam Bailey");
		text.setForeground(Color.LIGHT_GRAY);
		lvWindow.add(text, BorderLayout.PAGE_END);
		
		//LAUNCH WINDOW
        lvWindow.pack();
        lvWindow.setVisible(true);
        
        ListSelectionListener roomSelected = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				String selection = roomNames[roomSelector.getSelectedIndex()];
				try {
					ArrayList<machine> roomMachines = launchRoom(selection);
					JScrollPane thisRoom = machineDisplay(roomMachines);
					thisRoom.setOpaque(false);
					roomInfo.removeAll();
		        	roomInfo.revalidate();
		        	roomInfo.repaint();
		        	sidePanel.repaint();
		        	roomInfo.add(thisRoom);
				} catch (IOException ioe) {
					System.err.println("ERROR: L-203");
				}
			};
		};		
		roomSelector.addListSelectionListener(roomSelected);
	}
	
	static ArrayList<machine> launchRoom(String roomName) throws IOException{
		
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
					status = "Offline";
				} else if (next.equals("available")) {
					status = "Available";
					time = 0;
				} else if (next.equals("cycle")){
					status = "Cycle Ended";
					time = 0;
				} else {
					status = "In Use";
					if (next.equals("est.")){
						infoScanner.next();
						infoScanner.next();
						time = infoScanner.nextInt();
					} else if (next.equals("ended")){
						time = infoScanner.nextInt();
					}
				}
				infoScanner.close();
				machine newMachine = new machine(machineNumber, type, time, status);
				roomMachines.add(newMachine);
			}
		}
		in.close();
		return roomMachines;
	}
	
	static JScrollPane machineDisplay(ArrayList<machine> machineList) throws IOException{
		
		JPanel thisRoom = new JPanel();
		
		int numMachines = machineList.size();
		
		thisRoom.setLayout(new GridLayout(numMachines, 1));
		
		for (int i=0; i<numMachines; i++){
			JPanel currMachine = new JPanel(new FlowLayout(FlowLayout.LEADING));
			
			machine thisMachine = machineList.get(i);
			int num = thisMachine.getNum();
			JLabel machineNum = new JLabel("" + num, JLabel.CENTER);
			JLabel machineType = new JLabel(thisMachine.getType(), JLabel.CENTER);
			JLabel machineStatus = new JLabel(thisMachine.getStatus(), JLabel.CENTER);
			
			String iconFile = "";
			if(thisMachine.getType().equals("Washer")){
				iconFile = "src/UI/washing-machine.png";
			} else {
				iconFile = "src/UI/dryer.png";
			}
			Image image = ImageIO.read(new File(iconFile));
			JLabel icon = new JLabel(new ImageIcon(image));
			
			currMachine.add(icon);
			
			machineNum.setOpaque(false);
			machineType.setOpaque(false);
			machineStatus.setOpaque(false);
			icon.setOpaque(false);
						
			if(thisMachine.getStatus() == "Available"){
				machineStatus.setForeground(Color.GREEN);
				machineType.setForeground(Color.GREEN);
				machineNum.setForeground(Color.GREEN);
				currMachine.setBackground(availableMachine);
			} else if (thisMachine.getStatus() == "Cycle Ended"){
				machineStatus.setForeground(Color.RED);
				machineType.setForeground(Color.RED);
				machineNum.setForeground(Color.RED);
				currMachine.setBackground(otherMachine);
			} else if (thisMachine.getStatus() == "Offline"){
				machineStatus.setForeground(new Color(255, 255, 255, 50));
				machineType.setForeground(new Color(255, 255, 255, 50));
				machineNum.setForeground(new Color(255, 255, 255, 50));
				currMachine.setBackground(otherMachine);
			} else if (thisMachine.getStatus() == "In Use") {
				machineStatus.setForeground(Color.LIGHT_GRAY);
				machineType.setForeground(Color.LIGHT_GRAY);
				machineNum.setForeground(Color.LIGHT_GRAY);
				currMachine.setBackground(otherMachine);
			}
			
			machineNum.setPreferredSize(new Dimension(20,20));
			machineType.setPreferredSize(new Dimension(100, 20));
			machineStatus.setPreferredSize(new Dimension(100, 20));
			
			currMachine.add(machineNum);
			currMachine.add(machineType);
			currMachine.add(machineStatus);

			if(thisMachine.getTime() != 0){
				JLabel machineTime = new JLabel(thisMachine.getTime() + " minutes remaining", JLabel.CENTER);
				machineTime.setPreferredSize(new Dimension(200, 20));
				machineTime.setForeground(Color.LIGHT_GRAY);
				currMachine.add(machineTime);
				
				int progress = 45 - thisMachine.getTime();
				JProgressBar progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 45);
				progressBar.setValue(progress);
								
				progressBar.setPreferredSize(new Dimension(200, 10));
				
				currMachine.add(progressBar);
			}			
			thisRoom.add(currMachine);
		}
		thisRoom.setOpaque(false);
		thisRoom.setBackground(background);
		JScrollPane scrollPane = new JScrollPane(thisRoom, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getViewport().setOpaque(false);
		return scrollPane;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		launchGUI();
										
	}
}