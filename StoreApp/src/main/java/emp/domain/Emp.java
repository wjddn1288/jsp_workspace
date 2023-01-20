package emp.domain;

import lombok.Data;

@Data
public class Emp {
	private  int empno;
	private String ename;
	private String job;
	private String mgr;
	private int hiredate;
	private int sal;
	private int deptno;
}
