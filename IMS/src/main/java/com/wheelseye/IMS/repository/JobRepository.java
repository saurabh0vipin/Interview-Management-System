package com.wheelseye.IMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wheelseye.IMS.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>{
}
