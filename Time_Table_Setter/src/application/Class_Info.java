package application;

import java.util.ArrayList;

public class Class_Info {
	public String classNum;
	public String className;   // 수업 이름
	public String professor;   // 교수
	public int credit;         // 학점
	public String dayInfo;     // 요일,시간   ex) M1M2W3W4 -> 월12,수34
	public ArrayList<String> time;
	
	
	public Class_Info(String classNum, String className, String professor, int credit, String dayInfo){
		this.classNum = classNum;
		this.className = className;
		this.professor = professor;
		this.credit = credit;
		this.dayInfo = dayInfo;
		this.time = new ArrayList<String>();

	}
}