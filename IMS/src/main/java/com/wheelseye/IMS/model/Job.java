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
@Table(name="job")
public class Job {
	@Id
	@Column(name="job_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobId;
	
	@Column(name="job_title")
	private String jobTitle;
	
	@Column(name="date_posted")
	private String datePosted;
	
	@ManyToOne
	@JoinColumn(name="position_id")
	private PositionInOrganization posIn;
	
	
	public Job() {
	}

	public Job(String jobTitle, String datePosted) {
		super();
		this.jobTitle = jobTitle;
		this.datePosted = datePosted;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
	}

	public PositionInOrganization getPosIn() {
		return posIn;
	}

	public void setPosIn(PositionInOrganization posIn) {
		this.posIn = posIn;
	}
	
	
}
