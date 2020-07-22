package com.wheelseye.IMS.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="position_in_organization")
public class PositionInOrganization {
	@Id
	@Column(name="position_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long positionId;
	
	@Column(name="position_name")
	private String positionName;
	
	@OneToMany(mappedBy = "posIn", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Job> job = new ArrayList<>();
	
	public PositionInOrganization() {
	}

	public PositionInOrganization(String positionName) {
		super();
		this.positionName = positionName;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public List<Job> getJob() {
		return job;
	}

	public void setJob(List<Job> job) {
		this.job = job;
	}
	
	
}
