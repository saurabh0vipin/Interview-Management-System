package com.wheelseye.IMS;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	
	@Query("SELECT u FROM Employee u WHERE u.employeename = :employeename")
	public Employee getEmployeeByEmployeename(@Param("employeename") String employeename);
}
