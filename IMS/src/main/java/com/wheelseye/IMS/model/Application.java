package com.wheelseye.IMS.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="application")
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="application_id")
	private Long applicationId;
	
	@Column(name="date_applied_on")
	private String date;
	
	@ManyToOne
	@JoinColumn(name="interviewee_id")
	private Interviewee interviewee;
	
	@ManyToOne
	@JoinColumn(name="job_id")
	private Job job;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="interview_id")
	private Interview interview;
	
	

	public Application() {
	}

	public Application(String date, Interviewee interviewee, Job job, Interview interview) {
		super();
		this.date = date;
		this.interviewee = interviewee;
		this.job = job;
		this.interview = interview;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Interviewee getInterviewee() {
		return interviewee;
	}

	public void setInterviewee(Interviewee interviewee) {
		this.interviewee = interviewee;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}
	
	
}
