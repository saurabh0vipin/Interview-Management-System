package com.wheelseye.IMS.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wheelseye.IMS.model.Interviewee;

@Component
public interface IntervieweeService {
	
	public List<Interviewee>getall();
    public Interviewee add(Interviewee interviewee);
    public void delete(Interviewee interviewee);
    public Interviewee get(Long id) throws Exception;
    

}
