package application;

import java.util.ArrayList;

public class DFS {
	private int creditSum = 0;
	private int cnt = 0;
	private boolean Mcnt = false;
	private boolean Tcnt = false;
	private boolean Wcnt = false;
	private boolean Hcnt = false;
	private boolean Fcnt = false;
	
	public void dfs(Class_Info[] Class_List, int current, String currentSum, String currentTime, ArrayList<String> Class_Num_List, ArrayList<String> Class_Time_List) {
		if(current == 6) return;
		
		for(int i=0;i<Class_List[current].time.size();i++) {
			if(currentTime.contains(Class_List[current].time.get(i)))
				return;
			}


		
		//이번 원소를 포함시키지 않고 시도========================================
		dfs(Class_List,current+1,currentSum,currentTime,Class_Num_List,Class_Time_List);
		
		//============================================================
		//이번 원소를 포함시키고 시도===========================================
		if(currentSum == "") {
			currentSum = Class_List[current].classNum;	
			currentTime = Class_List[current].dayInfo;
		}
		else {
			currentSum = currentSum + "," + Class_List[current].classNum;
			currentTime = currentTime + "," + Class_List[current].dayInfo;
		}
		
		Class_Num_List.add(currentSum);
		Class_Time_List.add(currentTime);

		dfs(Class_List, current+1, currentSum, currentTime, Class_Num_List, Class_Time_List);//,currentSum,currentTime);
		//=============================================================
		
		//마지막에 다시 이번 원소를 빼줘야함
		currentSum.substring(Class_List[current].classNum.length(),currentSum.length());
		currentTime.substring(Class_List[current].dayInfo.length(), currentTime.length());
		
	}
	
	public void dfs(Class_Info[] Class_List, int current, String currentSum, String currentTime, ArrayList<String> Class_Num_List, ArrayList<String> Class_Time_List, int creditlow, int creditupp) {
		if(current == 6) return;

		
		for(int i=0;i<Class_List[current].time.size();i++) 
			if(currentTime.contains(Class_List[current].time.get(i)))
				return;
	
		//이번 원소를 포함시키지 않고 시도========================================
		dfs(Class_List,current+1,currentSum,currentTime,Class_Num_List,Class_Time_List,creditlow,creditupp);

		
		
		//============================================================
		//이번 원소를 포함시키고 시도===========================================
		if(currentSum == "") {
			currentSum = Class_List[current].classNum;	
			currentTime = Class_List[current].dayInfo;
		}
		else {
			currentSum = currentSum + "," + Class_List[current].classNum;
			currentTime = currentTime + "," + Class_List[current].dayInfo;
		}
		creditSum += Class_List[current].credit;
		System.out.println(creditSum + " " + Class_List[current].credit);

		dfs(Class_List, current+1, currentSum, currentTime, Class_Num_List, Class_Time_List,creditlow,creditupp);
		//=============================================================
		
		if(creditSum>=creditlow && creditSum<creditupp) {
			Class_Num_List.add(currentSum);
			Class_Time_List.add(currentTime);
		}
		
		//마지막에 다시 이번 원소를 빼줘야함
		currentSum.substring(Class_List[current].classNum.length(),currentSum.length());
		currentTime.substring(Class_List[current].dayInfo.length(), currentTime.length());
		creditSum -= Class_List[current].credit;	
	}
	
	public void dfs(Class_Info[] Class_List, int current, String currentSum, String currentTime, ArrayList<String> Class_Num_List, ArrayList<String> Class_Time_List, int numOfday, boolean[] chk) {
		if(current == 6) return;
		
		for(int i=0;i<Class_List[current].time.size();i++) {
			if(currentTime.contains(Class_List[current].time.get(i)))
				return;
			}
		
		
		//이번 원소를 포함시키지 않고 시도========================================
		dfs(Class_List,current+1,currentSum,currentTime,Class_Num_List,Class_Time_List,numOfday,chk);
		
		//============================================================
		//이번 원소를 포함시키고 시도===========================================
		
		
		int tp = 0;
		for(int i=0;i<Class_List[current].time.size();i++) {
			if(!currentTime.contains(Class_List[current].time.get(i).substring(0,1))) {
				if(Class_List[current].time.get(i).substring(0,1).compareTo("M") == 0 && !chk[0] && cnt <= numOfday) {
					chk[0] = true;
					cnt++;
					tp++;
				}
				else if(Class_List[current].time.get(i).substring(0,1).compareTo("T") == 0 && !chk[1] && cnt <= numOfday) {
					chk[1] = true;
					cnt++;
					tp++;
				}
				else if(Class_List[current].time.get(i).substring(0,1).compareTo("W") == 0 && !chk[2] && cnt <= numOfday) {
					chk[2] = true;
					cnt++;
					tp++;
				}
				else if(Class_List[current].time.get(i).substring(0,1).compareTo("H") == 0 && !chk[3] && cnt <= numOfday) {
					chk[3] = true;
					cnt++;
					tp++;
				}
				else if(Class_List[current].time.get(i).substring(0,1).compareTo("F") == 0 && !chk[4] && cnt <= numOfday) {
					chk[4] = true;
					cnt++;
					tp++;
				}
			}
		}

		
		if(currentSum == "") {
			currentSum = Class_List[current].classNum;	
			currentTime = Class_List[current].dayInfo;
		}
		else {
			currentSum = currentSum + "," + Class_List[current].classNum;
			currentTime = currentTime + "," + Class_List[current].dayInfo;
		}
		
		if(cnt == numOfday) {
			Class_Num_List.add(currentSum);
			Class_Time_List.add(currentTime);
			}

		dfs(Class_List, current+1, currentSum, currentTime, Class_Num_List, Class_Time_List,numOfday,chk);
		//=============================================================
		
		//마지막에 다시 이번 원소를 빼줘야함
		currentSum.substring(Class_List[current].classNum.length(),currentSum.length());
		currentTime.substring(Class_List[current].dayInfo.length(), currentTime.length());
		cnt -= tp;
		for(int i=0;i<5;i++)
			chk[i] = false;
		
		
	}
	
	
}
