package com.wheelseye.IMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wheelseye.IMS.model.Interviewee;

@Repository
public interface IntervieweeRepository extends JpaRepository<Interviewee, Long>{

}
