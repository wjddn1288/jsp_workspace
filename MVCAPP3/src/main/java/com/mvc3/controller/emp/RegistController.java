package com.mvc3.controller.emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.mvc3.controller.Controller;
import com.mvc3.domain.Dept;
import com.mvc3.domain.Emp;
import com.mvc3.exception.DeptException;
import com.mvc3.exception.EmpException;
import com.mvc3.model.emp.DeptDAO;
import com.mvc3.model.emp.EmpDAO;
import com.mvc3.model.emp.EmpService;
import com.mvc3.mybatis.MybatisConfig;

//사원 신규 등록 요청을 처리하는 하위 컨트롤러(부서+사원)
public class RegistController implements Controller {
	
	EmpService empService=new EmpService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파라미터 받기
		String dname = request.getParameter("dname");
		String ename = request.getParameter("ename");
		String sal = request.getParameter("sal");
		
		//부서 DTO
		Dept dept= new Dept(); //emp
		dept.setDname(dname); //부서명 채우기
		
		//사원 DTO
		Emp emp= new Emp(); //emp
		emp.setEname(ename); //사원명 채우기
		emp.setSal(Integer.parseInt(sal)); //sal이 int형이라 형변환 해줘야댐!
		emp.setDept(dept); //부서 dept 대입, 주의!!!
		
		//3단계)
		empService.regist(emp);
	}

	@Override
	public String getViewName() {
		return "/emp/view/regist";
	}

	@Override
	public boolean isForward() {
		return false; //redirect
	}

}
