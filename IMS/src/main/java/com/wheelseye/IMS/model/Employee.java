package com.wheelseye.IMS.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {
	@Id
	@Column(name = "emp_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	

	private String employeename;
	private String emailid;
	private String password;
	private Long phonenumber;
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	@OneToMany(mappedBy="employee")
	private Set<Round> round =new HashSet<>();
	
	public Employee() {
	}

	public Employee(String employeename, String emailid, String password, Long phonenumber, boolean enabled, Role role,
			Set<Round> round) {
		super();
		this.employeename = employeename;
		this.emailid = emailid;
		this.password = password;
		this.phonenumber = phonenumber;
		this.enabled = enabled;
		this.role = role;
		this.round = round;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(Long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Round> getRound() {
		return round;
	}

	public void setRound(Set<Round> round) {
		this.round = round;
	}


}
