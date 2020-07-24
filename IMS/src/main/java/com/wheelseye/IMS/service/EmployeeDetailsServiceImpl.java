package com.wheelseye.IMS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wheelseye.IMS.model.Employee;
import com.wheelseye.IMS.repository.EmployeeRepository;
import com.wheelseye.IMS.sec.MyEmployeeDetails;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String employeename) 
			throws UsernameNotFoundException {
		Employee employee = employeeRepository.getEmployeeByEmployeename(employeename);
		
		if (employee == null) {
			throw new UsernameNotFoundException("Could not find employee");
		}
		
		return new MyEmployeeDetails(employee);
	}
	
	public List<Employee> getInterviewers()
	{
		List<Employee> empl= (List<Employee>)employeeRepository.findAll();
		//System.out.println(empl.size());
		List<Employee> interviewer = new ArrayList<>();
		for(Employee emp: empl)
		{
			if(emp.getRole().getId()==3)
			{
				interviewer.add(emp);
			}
		}
		//System.out.println(interviewer.size());
		return interviewer;
	}
	
	public Employee get(Long employee_id)
	{
		Employee emp=employeeRepository.findById(employee_id).get();
		return emp;
	}
}
