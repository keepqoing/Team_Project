package application;

import java.util.ArrayList;

public class Class_Info {
	public String classNum;
	public String className;   // ���� �̸�
	public String professor;   // ����
	public int credit;         // ����
	public String dayInfo;     // ����,�ð�   ex) M1M2W3W4 -> ��12,��34
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