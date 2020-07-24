package com.wheelseye.IMS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wheelseye.IMS.model.Round;
import com.wheelseye.IMS.repository.RoundRepository;

@Service
public class RoundService {
	
	@Autowired
	RoundRepository repo;
	
	public void save(Round round)
	{
		repo.save(round);
	}
	public List<Round> listAll()
	{
		return repo.findAll();
	}
	
	public Round getById(Long id)
	{
		Round res=(Round)repo.findById(id).get();
		return res;
	}
	public void delete(Long id)
	{
		repo.deleteById(id);
	}
	public Long getInterviewId(Long id)
	{
		return repo.findById(id).get().getInterview().getInterviewId();
	}
	
	public List<Round> getFinishedRounds()
	{
		List<Round> round=repo.findAll();
		List<Round> res=new ArrayList<>();
		for(Round r:round)
		{
			if(r.getRoundStatus().equals("Finished") && !(r.getInterview().getStatus().equals("Accepted") && r.getInterview().getStatus().equals("Rejected")))
				res.add(r);
		}
		return res;
	}
	
	
	public List<Round> getAllscheduledRound()
	{
		List<Round> round= repo.findAll();
		List<Round> res=new ArrayList<>();
		for(Round r:round)
		{
			if(r.getRoundStatus().equals("Scheduled"))
				res.add(r);
		}
		return res;
	}
	
	public List<Round> getByEmployeeIdAndStatus(Long employee_id,String status)
	{
		List<Round> rounds=(List<Round>)repo.findAll();
		System.out.println(employee_id);
		System.out.println(status);
		List<Round>res=new ArrayList();
		System.out.println(rounds.size());
		
		for(Round r:rounds)
		{
			if(r.getEmployee().getId()== employee_id && status.contentEquals(r.getRoundStatus()))
			{
				res.add(r);
			}
		}
		System.out.println(res.size());
		return res;
		
	}
}
