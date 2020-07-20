package com.wheelseye.IMS.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wheelseye.IMS.exception.ResourceNotFoundException;
import com.wheelseye.IMS.model.Interviewee;
import com.wheelseye.IMS.repository.IntervieweeRepository;

@Component
public class IntervieweeServiceImpl implements IntervieweeService {

    @Autowired
    IntervieweeRepository intervieweerepo;
    
    @Override
    public List<Interviewee>getall()
    {
    	return intervieweerepo.findAll();
    }
    
    
    
    @Override
    public Interviewee add(Interviewee interviewee)
    {
    	intervieweerepo.save(interviewee);
        return interviewee;
    }
    
    @Override
    public void delete(Interviewee interviewee)
    {
    	 intervieweerepo.delete(interviewee);
    }
    
    @Override
    public Interviewee get(Long id) throws ResourceNotFoundException
    {
    	Interviewee interviewee =intervieweerepo.findById(id).
    			orElseThrow(()->new ResourceNotFoundException("Interviewee with id"+id+"not found"));
    return interviewee;
    }
    
}
