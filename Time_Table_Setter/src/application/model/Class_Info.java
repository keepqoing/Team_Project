package application.model;

import java.util.ArrayList;

import application.util.Parser;

public class Class_Info {
	private String classNum;
	private int grade;
	private String subdiv;
	private String className;       // 수업 이름
	private String professor;       // 교수
	private int credit;             // 학점
	private String dayInfo;         // 요일,시간   ex) M1M2W3W4 -> 월12,수34
	private ArrayList<String> time;
	
	Parser par = new Parser();
	
	public Class_Info() {
		time = new ArrayList<String>();
	}
	
	public Class_Info(String classNum, int grade, String subdiv, String className, String professor, int credit, String dayInfo){
		this.classNum = classNum;
		this.grade = grade;
		this.subdiv = subdiv;
		this.className = className;
		this.professor = professor;
		this.credit = credit;
		this.dayInfo = dayInfo;
		this.time = new ArrayList<String>();

	}
	
	public String getClassNum() {
		return classNum;
	}
	
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public String getSubdiv() {
		return subdiv;
	}
	
	public void setSubdiv(String subdiv) {
		this.subdiv = subdiv;
	}
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getProfessor() {
		return professor;
	}
	
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	public String getDayInfo() {
		return dayInfo;
	}
	
	public void setDayInfo(String dayInfo) {
		this.dayInfo = dayInfo;
	}
	
	public ArrayList<String> getTime(){
		return time;
	}	
	
	public void setTime(String insert) {
		this.time = par.GET(insert);
	}
	




}