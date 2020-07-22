package com.wheelseye.IMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wheelseye.IMS.model.PositionInOrganization;

@Repository
public interface PositionInOrganizationRepository extends JpaRepository<PositionInOrganization, Long>{

}
