package com.wheelseye.IMS.sec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wheelseye.IMS.model.Employee;
import com.wheelseye.IMS.model.Role;

public class MyEmployeeDetails implements UserDetails{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private Employee employee;
	
	public MyEmployeeDetails(Employee employee) {
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Role roles = employee.getRole();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
			authorities.add(new SimpleGrantedAuthority(roles.getName()));
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		return employee.getEmployeename();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return employee.isEnabled();
	}
}
