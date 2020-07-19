package com.wheelseye.IMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wheelseye.IMS.model.Round;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long>{

}
