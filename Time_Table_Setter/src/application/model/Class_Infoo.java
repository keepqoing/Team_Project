package application.model;

import java.util.ArrayList;

public class Class_Infoo {
	private String classNum;
	private int grade;
	private String subdiv;
	private String className;       // ���� �̸�
	private String professor;       // ����
	private int credit;             // ����
	private String dayInfo;         // ����,�ð�   ex) M1M2W3W4 -> ��12,��34
	private ArrayList<String> time;
	
	
//	public Class_Infoo(String classNum, int grade, String subdiv, String className, String professor, int credit, String dayInfo){
//		this.classNum = classNum;
//		this.grade = grade;
//		this.subdiv = subdiv;
//		this.className = className;
//		this.professor = professor;
//		this.credit = credit;
//		this.dayInfo = dayInfo;
//		this.time = new ArrayList<String>();
//	}

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
}
