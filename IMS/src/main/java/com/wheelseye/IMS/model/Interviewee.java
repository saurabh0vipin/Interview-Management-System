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
@Table(name="interviewee")
public class Interviewee {
	@Id
	@Column(name="Interviewee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long intervieweeId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email_id")
	private String emailID;
	
	@Column(name="phone_no")
	private Long phoneNo;
	
	@Column(name="resume")
	private String resume;

	@OneToMany(mappedBy="interviewee")
	private Set<Application> application =new HashSet<>();
	
	public Interviewee() {
	}


	public Interviewee(String name, String emailID, Long phoneNo, String resume) {
		super();
		this.name = name;
		this.emailID = emailID;
		this.phoneNo = phoneNo;
		this.resume = resume;
	}


	
	public Long getIntervieweeId() {
		return intervieweeId;
	}


	public void setIntervieweeId(Long intervieweeId) {
		this.intervieweeId = intervieweeId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}
	
	
	
}
