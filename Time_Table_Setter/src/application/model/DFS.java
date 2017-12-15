package application.model;

import java.util.ArrayList;

public class DFS {
	private int creditSum = 0;
	private int cnt = 0;
	private int numofclasscnt = 0;
	
	public void dfs(ArrayList<Class_Info> Class_List, int current, String currentSum, String currentTime, ArrayList<String> Class_Num_List, ArrayList<String> Class_Time_List) {
		if(current == Class_List.size()) return;
		
		for(int i=0;i<Class_List.get(current).getTime().size();i++) {
			if(currentTime.contains(Class_List.get(current).getTime().get(i)))
				return;
		}

		if(currentSum.contains(Class_List.get(current).getClassNum().substring(0, 7)))
			return;

		//이번 원소를 포함시키지 않고 시도========================================
		dfs(Class_List,current+1,currentSum,currentTime,Class_Num_List,Class_Time_List);
		
		//============================================================
		//이번 원소를 포함시키고 시도===========================================
		if(currentSum == "") {
			currentSum = Class_List.get(current).getClassNum();	
			currentTime = Class_List.get(current).getDayInfo();
		}
		else {
			currentSum = currentSum + "," + Class_List.get(current).getClassNum();
			currentTime = currentTime + "," + Class_List.get(current).getDayInfo();
		}
		
		Class_Num_List.add(currentSum);
		Class_Time_List.add(currentTime);

		dfs(Class_List, current+1, currentSum, currentTime, Class_Num_List, Class_Time_List);//,currentSum,currentTime);
		//=============================================================
		
		//마지막에 다시 이번 원소를 빼줘야함
		currentSum.substring(Class_List.get(current).getClassNum().length(),currentSum.length());
		currentTime.substring(Class_List.get(current).getDayInfo().length(), currentTime.length());
		
	}
	
	
	
	public void dfs(ArrayList<Class_Info> Class_List, int current, String currentSum, String currentTime, ArrayList<String> Class_Num_List, ArrayList<String> Class_Time_List, int creditlow, int creditupp) {
		if(current == Class_List.size()) return;

		
		for(int i=0;i<Class_List.get(current).getTime().size();i++) 
			if(currentTime.contains(Class_List.get(current).getTime().get(i)))
				return;
		
		if(currentSum.contains(Class_List.get(current).getClassNum().substring(0, 7)))
			return;

	
		//이번 원소를 포함시키지 않고 시도========================================
		dfs(Class_List,current+1,currentSum,currentTime,Class_Num_List,Class_Time_List,creditlow,creditupp);

		
		
		//============================================================
		//이번 원소를 포함시키고 시도===========================================
		if(currentSum == "") {
			currentSum = Class_List.get(current).getClassNum();	
			currentTime = Class_List.get(current).getDayInfo();
		}
		else {
			currentSum = currentSum + "," + Class_List.get(current).getClassNum();
			currentTime = currentTime + "," + Class_List.get(current).getDayInfo();
		}
		creditSum += Class_List.get(current).getCredit();
		//System.out.println(creditSum + " " + Class_List.get(current).getCredit());

		dfs(Class_List, current+1, currentSum, currentTime, Class_Num_List, Class_Time_List,creditlow,creditupp);
		//=============================================================
		
		if(creditSum>=creditlow && creditSum<=creditupp) {
			Class_Num_List.add(currentSum);
			Class_Time_List.add(currentTime);
		}
		
		//마지막에 다시 이번 원소를 빼줘야함
		currentSum.substring(Class_List.get(current).getClassNum().length(),currentSum.length());
		currentTime.substring(Class_List.get(current).getDayInfo().length(), currentTime.length());
		creditSum -= Class_List.get(current).getCredit();	
	}
	
	
	
	public void dfs(ArrayList<Class_Info> Class_List, int current, String currentSum, String currentTime, ArrayList<String> Class_Num_List, ArrayList<String> Class_Time_List, int numOfday, boolean[] chk) {
		if(current == Class_List.size()) return;
		
		for(int i=0;i<Class_List.get(current).getTime().size();i++) {
			if(currentTime.contains(Class_List.get(current).getTime().get(i)))
				return;
			}
		
		if(currentSum.contains(Class_List.get(current).getClassNum().substring(0, 7)))
			return;

		
		//이번 원소를 포함시키지 않고 시도========================================
		dfs(Class_List,current+1,currentSum,currentTime,Class_Num_List,Class_Time_List,numOfday,chk);
		
		//============================================================
		//이번 원소를 포함시키고 시도===========================================
		
		
		int tp = 0;
		for(int i=0;i<Class_List.get(current).getTime().size();i++) {
			if(!currentTime.contains(Class_List.get(current).getTime().get(i).substring(0,1))) {
				if(Class_List.get(current).getTime().get(i).substring(0,1).compareTo("M") == 0 && !chk[0] && cnt <= numOfday) {
					chk[0] = true;
					cnt++;
					tp++;
				}
				else if(Class_List.get(current).getTime().get(i).substring(0,1).compareTo("T") == 0 && !chk[1] && cnt <= numOfday) {
					chk[1] = true;
					cnt++;
					tp++;
				}
				else if(Class_List.get(current).getTime().get(i).substring(0,1).compareTo("W") == 0 && !chk[2] && cnt <= numOfday) {
					chk[2] = true;
					cnt++;
					tp++;
				}
				else if(Class_List.get(current).getTime().get(i).substring(0,1).compareTo("H") == 0 && !chk[3] && cnt <= numOfday) {
					chk[3] = true;
					cnt++;
					tp++;
				}
				else if(Class_List.get(current).getTime().get(i).substring(0,1).compareTo("F") == 0 && !chk[4] && cnt <= numOfday) {
					chk[4] = true;
					cnt++;
					tp++;
				}
			}
		}

		
		if(currentSum == "") {
			currentSum = Class_List.get(current).getClassNum();	
			currentTime = Class_List.get(current).getDayInfo();
		}
		else {
			currentSum = currentSum + "," + Class_List.get(current).getClassNum();
			currentTime = currentTime + "," + Class_List.get(current).getDayInfo();
		}
		
		if(cnt == numOfday) {
			Class_Num_List.add(currentSum);
			Class_Time_List.add(currentTime);
			}

		dfs(Class_List, current+1, currentSum, currentTime, Class_Num_List, Class_Time_List,numOfday,chk);
		//=============================================================
		
		//마지막에 다시 이번 원소를 빼줘야함
		currentSum.substring(Class_List.get(current).getClassNum().length(),currentSum.length());
		currentTime.substring(Class_List.get(current).getDayInfo().length(), currentTime.length());
		cnt -= tp;
		for(int i=0;i<5;i++)
			chk[i] = false;
	
	}
	
	
	public void dfs(ArrayList<Class_Info> Class_List, int current, String currentSum, String currentTime, ArrayList<String> Class_Num_List, ArrayList<String> Class_Time_List, int creditlow, int creditupp, int numOfday, boolean[] chk,int minclassnum) {
		if(current == Class_List.size()) return;

		
		for(int i=0;i<Class_List.get(current).getTime().size();i++) 
			if(currentTime.contains(Class_List.get(current).getTime().get(i)))
				return;
		
		if(currentSum.contains(Class_List.get(current).getClassNum().substring(0, 7)))
			return;

	
		//이번 원소를 포함시키지 않고 시도========================================
		dfs(Class_List,current+1,currentSum,currentTime,Class_Num_List,Class_Time_List,creditlow,creditupp,numOfday,chk,minclassnum);

		
		
		//============================================================
		//이번 원소를 포함시키고 시도===========================================
		
		int tp = 0;
		for(int i=0;i<Class_List.get(current).getTime().size();i++) {
			if(!currentTime.contains(Class_List.get(current).getTime().get(i).substring(0,1))) {
				if(Class_List.get(current).getTime().get(i).substring(0,1).compareTo("M") == 0 && !chk[0] && cnt <= numOfday) {
					chk[0] = true;
					cnt++;
					tp++;
				}
				else if(Class_List.get(current).getTime().get(i).substring(0,1).compareTo("T") == 0 && !chk[1] && cnt <= numOfday) {
					chk[1] = true;
					cnt++;
					tp++;
				}
				else if(Class_List.get(current).getTime().get(i).substring(0,1).compareTo("W") == 0 && !chk[2] && cnt <= numOfday) {
					chk[2] = true;
					cnt++;
					tp++;
				}
				else if(Class_List.get(current).getTime().get(i).substring(0,1).compareTo("H") == 0 && !chk[3] && cnt <= numOfday) {
					chk[3] = true;
					cnt++;
					tp++;
				}
				else if(Class_List.get(current).getTime().get(i).substring(0,1).compareTo("F") == 0 && !chk[4] && cnt <= numOfday) {
					chk[4] = true;
					cnt++;
					tp++;
				}
			}
		}
		
		if(currentSum == "") {
			currentSum = Class_List.get(current).getClassNum();	
			currentTime = Class_List.get(current).getDayInfo();
		}
		else {
			currentSum = currentSum + "," + Class_List.get(current).getClassNum();
			currentTime = currentTime + "," + Class_List.get(current).getDayInfo();
		}
		creditSum += Class_List.get(current).getCredit();
		numofclasscnt++;
		
		if(creditSum>=creditlow && creditSum<=creditupp && cnt == numOfday && numofclasscnt>=minclassnum) {
			Class_Num_List.add(currentSum);
			Class_Time_List.add(currentTime);
		}

		dfs(Class_List, current+1, currentSum, currentTime, Class_Num_List, Class_Time_List,creditlow,creditupp,numOfday,chk,minclassnum);
		//=============================================================
		
		//마지막에 다시 이번 원소를 빼줘야함
		currentSum.substring(Class_List.get(current).getClassNum().length(),currentSum.length());
		currentTime.substring(Class_List.get(current).getDayInfo().length(), currentTime.length());
		creditSum -= Class_List.get(current).getCredit();	
		cnt -= tp;
		numofclasscnt--;
		for(int i=0;i<5;i++)
			chk[i] = false;
	}
	
	public void dfs(ArrayList<Class_Info> Class_List, int current, String currentSum, String currentTime, ArrayList<String> Class_Num_List, ArrayList<String> Class_Time_List, int creditlow, int creditupp, int minclassnum) {
		if(current == Class_List.size()) return;

		
		for(int i=0;i<Class_List.get(current).getTime().size();i++) 
			if(currentTime.contains(Class_List.get(current).getTime().get(i)))
				return;
		
		if(currentSum.contains(Class_List.get(current).getClassNum().substring(0, 7)))
			return;

	
		//이번 원소를 포함시키지 않고 시도========================================
		dfs(Class_List,current+1,currentSum,currentTime,Class_Num_List,Class_Time_List,creditlow,creditupp,minclassnum);

		
		
		//============================================================
		//이번 원소를 포함시키고 시도===========================================
		if(currentSum == "") {
			currentSum = Class_List.get(current).getClassNum();	
			currentTime = Class_List.get(current).getDayInfo();
		}
		else {
			currentSum = currentSum + "," + Class_List.get(current).getClassNum();
			currentTime = currentTime + "," + Class_List.get(current).getDayInfo();
		}
		creditSum += Class_List.get(current).getCredit();
		numofclasscnt++;

		dfs(Class_List, current+1, currentSum, currentTime, Class_Num_List, Class_Time_List,creditlow,creditupp,minclassnum);
		//=============================================================
		
		if(creditSum>=creditlow && creditSum<=creditupp && numofclasscnt >= minclassnum) {
			Class_Num_List.add(currentSum);
			Class_Time_List.add(currentTime);
		}
		
		//마지막에 다시 이번 원소를 빼줘야함
		currentSum.substring(Class_List.get(current).getClassNum().length(),currentSum.length());
		currentTime.substring(Class_List.get(current).getDayInfo().length(), currentTime.length());
		creditSum -= Class_List.get(current).getCredit();	
		numofclasscnt--;
	}
	
	public void dfs(ArrayList<Class_Info> Class_List, int current, String currentSum, String currentTime, ArrayList<String> Class_Num_List, ArrayList<String> Class_Time_List, int minclassnum) {
		if(current == Class_List.size()) return;
		
		for(int i=0;i<Class_List.get(current).getTime().size();i++) {
			if(currentTime.contains(Class_List.get(current).getTime().get(i)))
				return;
			}
		
		if(currentSum.contains(Class_List.get(current).getClassNum().substring(0, 7)))
			return;

		
		//이번 원소를 포함시키지 않고 시도========================================
		dfs(Class_List,current+1,currentSum,currentTime,Class_Num_List,Class_Time_List,minclassnum);
		
		//============================================================
		//이번 원소를 포함시키고 시도===========================================
		if(currentSum == "") {
			currentSum = Class_List.get(current).getClassNum();	
			currentTime = Class_List.get(current).getDayInfo();
		}
		else {
			currentSum = currentSum + "," + Class_List.get(current).getClassNum();
			currentTime = currentTime + "," + Class_List.get(current).getDayInfo();
		}
		numofclasscnt++;
		
		if(numofclasscnt >= minclassnum) {
		Class_Num_List.add(currentSum);
		Class_Time_List.add(currentTime);
		}
		
		dfs(Class_List, current+1, currentSum, currentTime, Class_Num_List, Class_Time_List,minclassnum);//,currentSum,currentTime);
		//=============================================================
		
		//마지막에 다시 이번 원소를 빼줘야함
		currentSum.substring(Class_List.get(current).getClassNum().length(),currentSum.length());
		currentTime.substring(Class_List.get(current).getDayInfo().length(), currentTime.length());
		numofclasscnt--;
		
	}
	
	
}
