package com.wheelseye.IMS.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="postion_in_organization")
public class PositionInOrganization {
	@Id
	@Column(name="postion_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postionId;
	
	@Column(name="postion_name")
	private String postionName;

	
	
	public PositionInOrganization() {
	}

	public PositionInOrganization(String postionName) {
		super();
		this.postionName = postionName;
	}

	public Long getPostionId() {
		return postionId;
	}

	public void setPostionId(Long postionId) {
		this.postionId = postionId;
	}

	public String getPostionName() {
		return postionName;
	}

	public void setPostionName(String postionName) {
		this.postionName = postionName;
	}
	
	
}
