package com.wheelseye.IMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wheelseye.IMS.model.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long>{

}
