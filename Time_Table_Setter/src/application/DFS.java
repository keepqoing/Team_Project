package application;

import java.util.ArrayList;

public class DFS {
	private int creditSum = 0;
	
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

		dfs(Class_List, current+1, currentSum, currentTime, Class_Num_List, Class_Time_List);//,currentSum,currentTime);
		//=============================================================
		
		//마지막에 다시 이번 원소를 빼줘야함
		currentSum.substring(Class_List[current].classNum.length(),currentSum.length());
		currentTime.substring(Class_List[current].dayInfo.length(), currentTime.length());
		
		
		Class_Num_List.add(currentSum);
		Class_Time_List.add(currentTime);
	}
	
	public void dfs(Class_Info[] Class_List, int current, String currentSum, String currentTime, ArrayList<String> Class_Num_List, ArrayList<String> Class_Time_List, int creditlow) {
		if(current == 6) return;
		
		for(int i=0;i<Class_List[current].time.size();i++) {
			if(currentTime.contains(Class_List[current].time.get(i)))
				return;
			}
		
		//이번 원소를 포함시키지 않고 시도========================================
		dfs(Class_List,current+1,currentSum,currentTime,Class_Num_List,Class_Time_List,creditlow);

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
		//creditlow -= Class_List[current].credit;

		dfs(Class_List, current+1, currentSum, currentTime, Class_Num_List, Class_Time_List,creditlow);//,currentSum,currentTime);
		//=============================================================
		
		//마지막에 다시 이번 원소를 빼줘야함
		currentSum.substring(Class_List[current].classNum.length(),currentSum.length());
		currentTime.substring(Class_List[current].dayInfo.length(), currentTime.length());
		creditSum -= Class_List[current].credit;
		//creditlow += Class_List[current].credit;

		System.out.println(creditSum + " " + creditlow);
		if(creditSum>=creditlow) {
		Class_Num_List.add(currentSum);
		Class_Time_List.add(currentTime);
		}
	}
	
	
}
