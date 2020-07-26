package com.wheelseye.IMS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wheelseye.IMS.model.Interview;
import com.wheelseye.IMS.repository.InterviewRepository;

@Service
public class InterviewService {
	
	@Autowired
	InterviewRepository repo;
    
	
    public List<Interview>listAll()
    {
    	return repo.findAll();
    }
//    public List<Interview> toBeScheduled()
//    {
//    	List<Interview> sch= repo.findAll();
//    	List<Interview> res=new ArrayList();
//    	for(Interview s: sch)
//    	{
//    		if(s.getStatus().equals("QualifiedRound1") || s.getStatus().equals("QualifiedRound2") || s.getStatus().equals("Applied"))
//    		{
//    			res.add(s);
//    		}
//    	}
//    	return res;
//    }
    
    public void save(Interview interview)
    {
    	repo.save(interview);
       
    }
  
    public void delete(Interview interview)
    {
    	 repo.delete(interview);
    }
    
   
    public Interview get(Long id)
    {
    	return repo.findById(id).get();
    			
    
    }
    
}
