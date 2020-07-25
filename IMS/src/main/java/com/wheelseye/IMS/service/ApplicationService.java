package com.wheelseye.IMS.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wheelseye.IMS.model.Application;
import com.wheelseye.IMS.model.Interview;
import com.wheelseye.IMS.model.Interviewee;
import com.wheelseye.IMS.repository.ApplicationRepository;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	ApplicationRepository repo;
	
	public List<Application> listAll() {
        return repo.findAll();
    }
	
	public void save(Application application)
	{
		repo.save(application);
	}
	
	public Application get(Long id)
	{
		return repo.findById(id).get();
	}
	
	public void delete(Long id)
	{
		repo.deleteById(id);;
	}
	
	public Application getApplication(Interview interview)
	{
		List<Application> application= repo.findAll();
		for(Application app: application)
		{
			if(app.getInterview()==interview)
				return app;
		}
		return null;
	}
	
	public boolean anyRecentApplication(Interviewee interviewee)
	{
		List<Application> application=repo.findAll();
		for(Application app: application)
		{
			if(app.getInterviewee()==interviewee && app.getDate().plusDays(90).isAfter(LocalDateTime.now()))
				return true;
				
		}
		return false;
	}
}
