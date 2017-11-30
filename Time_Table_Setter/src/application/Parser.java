package application;

import java.util.ArrayList;

public class Parser {
	public ArrayList<String> GET_TIME(String insert) {;
		ArrayList<String> Time = new ArrayList<String>();	
		String[] temp = insert.split(",");		
		for(int i=0;i<temp.length;i++) {
			if(temp[i].substring(0,1).compareTo("M") == 0) {
				int index = Integer.parseInt(temp[i].substring(1));
				switch(index) {
				case 1:
					Time.add("M1");
					break;
				case 2:
					Time.add("M2");
					break;
				case 3:
					Time.add("M3");
					break;
				case 4:
					Time.add("M4");
					break;
				case 5:
					Time.add("M5");
					break;
				case 6:
					Time.add("M6");
					break;
				case 7:
					Time.add("M7");
					break;
				case 8:
					Time.add("M8");
					break;
				case 9:
					Time.add("M9");
					break;
				}
			}
			else if(temp[i].substring(0,1).compareTo("T") == 0) {
				int index = Integer.parseInt(temp[i].substring(1));
				switch(index) {
				case 1:
					Time.add("T1");
					break;
				case 2:
					Time.add("T2");
					break;
				case 3:
					Time.add("T3");
					break;
				case 4:
					Time.add("T4");
					break;
				case 5:
					Time.add("T5");
					break;
				case 6:
					Time.add("T6");
					break;
				case 7:
					Time.add("T7");
					break;
				case 8:
					Time.add("T8");
					break;
				case 9:
					Time.add("T9");
					break;
				}
			}
			else if(temp[i].substring(0,1).compareTo("W") == 0) {
				int index = Integer.parseInt(temp[i].substring(1));
				switch(index) {
				case 1:
					Time.add("W1");
					break;
				case 2:
					Time.add("W2");
					break;
				case 3:
					Time.add("W3");
					break;
				case 4:
					Time.add("W4");
					break;
				case 5:
					Time.add("W5");
					break;
				case 6:
					Time.add("W6");
					break;
				case 7:
					Time.add("W7");
					break;
				case 8:
					Time.add("W8");
					break;
				case 9:
					Time.add("W9");
					break;
				}
			}
			else if(temp[i].substring(0,1).compareTo("H") == 0) {
				int index = Integer.parseInt(temp[i].substring(1));
				switch(index) {
				case 1:
					Time.add("H1");
					break;
				case 2:
					Time.add("H2");
					break;
				case 3:
					Time.add("H3");
					break;
				case 4:
					Time.add("H4");
					break;
				case 5:
					Time.add("H5");
					break;
				case 6:
					Time.add("H6");
					break;
				case 7:
					Time.add("H7");
					break;
				case 8:
					Time.add("H8");
					break;
				case 9:
					Time.add("H9");
					break;
				}
			}
				
				else {
					int index = Integer.parseInt(temp[i].substring(1));
					switch(index) {
					case 1:
						Time.add("F1");
						break;
					case 2:
						Time.add("F2");
						break;
					case 3:
						Time.add("F3");
						break;
					case 4:
						Time.add("F4");
						break;
					case 5:
						Time.add("F5");
						break;
					case 6:
						Time.add("F6");
						break;
					case 7:
						Time.add("F7");
						break;
					case 8:
						Time.add("F8");
						break;
					case 9:
						Time.add("F9");
						break;
				}
			}
		}
		
		return Time;
	}
}
