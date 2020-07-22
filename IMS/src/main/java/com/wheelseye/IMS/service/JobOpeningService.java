package com.wheelseye.IMS.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wheelseye.IMS.model.Job;
import com.wheelseye.IMS.repository.JobRepository;

@Service
@Transactional
public class JobOpeningService{
	
	@Autowired
	JobRepository repo;
	
	public List<Job> listAll() {
        return repo.findAll();
    }
	
	public void save(Job job)
	{
		repo.save(job);
	}
	
	public Job get(Long id)
	{
		return repo.findById(id).get();
	}
	
	public void delete(Long id)
	{
		repo.deleteById(id);;
	}
	

}
