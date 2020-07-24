package com.wheelseye.IMS.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="interview")
public class Interview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="interview_id")
	private Long interviewId;

	@Column(name="status")
	private String status;
	
	@OneToMany(mappedBy = "interview")
	private Set<Round> round=new HashSet<>();

	
	
	public Interview() {
	}

	public Interview(String status, Set<Round> round) {
		super();
		this.status = status;
		this.round = round;
	}

	public Long getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Long interviewId) {
		this.interviewId = interviewId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Round> getRound() {
		return round;
	}

	public void setRound(Set<Round> round) {
		this.round = round;
	}
	
	
	
}
