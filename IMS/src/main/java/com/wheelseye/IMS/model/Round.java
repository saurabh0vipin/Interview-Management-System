package com.wheelseye.IMS.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="round")
public class Round {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="round_id")
	private Long roundId;
	
	@Column(name="round_status")
	private String roundStatus;
	
	@Column(name="rating")
	private float rating;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="interview_id")
	private Interview interview;

	
	public Round() {
	}

	public Round(String roundStatus, float rating, Employee employee, Interview interview) {
		super();
		this.roundStatus = roundStatus;
		this.rating = rating;
		this.employee = employee;
		this.interview = interview;
	}

	public Long getRoundId() {
		return roundId;
	}

	public void setRoundId(Long roundId) {
		this.roundId = roundId;
	}

	public String getRoundStatus() {
		return roundStatus;
	}

	public void setRoundStatus(String roundStatus) {
		this.roundStatus = roundStatus;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}
	
	
}
