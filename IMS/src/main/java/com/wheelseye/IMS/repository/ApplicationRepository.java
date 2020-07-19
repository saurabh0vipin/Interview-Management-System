package com.wheelseye.IMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wheelseye.IMS.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{

}
