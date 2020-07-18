package com.wheelseye.IMS.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wheelseye.IMS.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	
	@Query("SELECT u FROM Employee u WHERE u.employeename = :employeename")
	public Employee getEmployeeByEmployeename(@Param("employeename") String employeename);
}
