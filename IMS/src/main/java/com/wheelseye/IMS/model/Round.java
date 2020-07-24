package com.wheelseye.IMS.model;

import java.time.LocalDateTime;

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
	
	@Column(name="start_date")
	private LocalDateTime startDate;
	
	@Column(name="duration")
	private Integer duration;
	
	@Column(name="feedback")
	private String feedback;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="interview_id")
	private Interview interview;

	public Round() {
		this.rating=(float) 0.0;
	}

	public Round(String roundStatus, float rating, LocalDateTime startDate, Integer duration, String feedback,
			Employee employee, Interview interview) {
		super();
		this.roundStatus = roundStatus;
		this.rating = rating;
		this.startDate = startDate;
		this.duration = duration;
		this.feedback = feedback;
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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
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
