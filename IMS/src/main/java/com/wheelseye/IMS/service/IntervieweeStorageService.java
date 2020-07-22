package com.wheelseye.IMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wheelseye.IMS.model.Interviewee;
import com.wheelseye.IMS.repository.IntervieweeRepository;


@Service
public class IntervieweeStorageService {
	
	@Autowired
	private IntervieweeRepository intervieweeRepository;
	
	public List<Interviewee> listAll() {		
		return intervieweeRepository.findAll();
	}
	
	public void save(Interviewee resume) {
		intervieweeRepository.save(resume);
	}
	
	public Interviewee get(Long id) {
		return intervieweeRepository.findById(id).get();
	}
	
	public Optional<Interviewee> getFile(Long fileId) {
		return intervieweeRepository.findById(fileId);
	}
	
	public void delete(Long id) {
		intervieweeRepository.deleteById(id);
	}
}

