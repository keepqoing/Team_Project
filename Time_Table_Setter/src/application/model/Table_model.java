package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Table_model {
	private StringProperty   classNum;
	private IntegerProperty   grade;
	private StringProperty   subdiv;
	private StringProperty   className;       // 수업 이름
	private StringProperty   professor;       // 교수
	private IntegerProperty   credit;             // 학점
	private StringProperty   dayInfo;
	
	public Table_model() {
		this.classNum = new SimpleStringProperty();
		this.grade = new SimpleIntegerProperty();
		this.subdiv = new SimpleStringProperty();
		this.className = new SimpleStringProperty();
		this.professor = new SimpleStringProperty();
		this.credit = new SimpleIntegerProperty();
		this.dayInfo = new SimpleStringProperty();
	}
	
	public Table_model(StringProperty classNum, IntegerProperty grade, StringProperty subdiv, StringProperty className,StringProperty professor,IntegerProperty credit,StringProperty dayInfo) {
		this.classNum = classNum;
		this.grade = grade;
		this.subdiv = subdiv;
		this.className = className;
		this.professor = professor;
		this.credit = credit;
		this.dayInfo = dayInfo;
	}
	
	public StringProperty getClassNum() {
		return classNum;
	}
	
	public void setClassNum(String classNum) {
		this.classNum.set(classNum);
	}
	
	public IntegerProperty getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade.set(grade);
	}
	
	public StringProperty getSubdiv() {
		return subdiv;
	}
	
	public void setSubdiv(String subdiv) {
		this.subdiv.set(subdiv);
	}
	
	public StringProperty getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.classNum.set(className);
	}
	
	public StringProperty getProfessor() {
		return professor;
	}
	
	public void setProfessor(String professor) {
		this.classNum.set(professor);
	}
	
	public IntegerProperty getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit.set(credit);
	}
	
	public StringProperty getDayInfo() {
		return dayInfo;
	}
	
	public void setDayInfo(String dayInfo) {
		this.dayInfo.set(dayInfo);
	}
	
}
