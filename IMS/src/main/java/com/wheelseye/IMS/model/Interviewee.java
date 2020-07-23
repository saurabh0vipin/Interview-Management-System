package com.wheelseye.IMS.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="interviewee")
public class Interviewee {
	@Id
	@Column(name="Interviewee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String intervieweeName;
	
	@Column(name="phone_no", unique = true)
	private String intervieweePhoneNumber;
	
	@Column(name="email_id", unique = true)
	private String intervieweeMail;
	
	@Column(name="Experience")
	private String yearsOfExperience;
	
	@Column(name="resume_name")
	private String resumeName;
	
	@Column(name="resume_format")
	private String resumeType;
	
	@Lob
	private byte[] data;
	
	@OneToMany(mappedBy="interviewee", cascade = CascadeType.ALL)
	List<Application> application =new ArrayList<>();
	
	public Interviewee() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResumeName() {
		return resumeName;
	}

	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}

	public String getResumeType() {
		return resumeType;
	}

	public void setResumeType(String resumeType) {
		this.resumeType = resumeType;
	}

	public String getIntervieweeName() {
		return intervieweeName;
	}

	public void setIntervieweeName(String intervieweeName) {
		this.intervieweeName = intervieweeName;
	}

	public String getIntervieweePhoneNumber() {
		return intervieweePhoneNumber;
	}

	public void setIntervieweePhoneNumber(String intervieweePhoneNumber) {
		this.intervieweePhoneNumber = intervieweePhoneNumber;
	}

	public String getIntervieweeMail() {
		return intervieweeMail;
	}

	public void setIntervieweeMail(String intervieweeMail) {
		this.intervieweeMail = intervieweeMail;
	}

	public String getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}

