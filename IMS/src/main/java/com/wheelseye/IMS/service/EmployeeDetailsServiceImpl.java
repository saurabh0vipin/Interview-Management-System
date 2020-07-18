package com.wheelseye.IMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wheelseye.IMS.model.Employee;
import com.wheelseye.IMS.repository.EmployeeRepository;
import com.wheelseye.IMS.sec.MyEmployeeDetails;

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
}
