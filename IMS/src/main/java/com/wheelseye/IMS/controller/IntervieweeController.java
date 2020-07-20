package com.wheelseye.IMS.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;


import com.wheelseye.IMS.model.Interviewee;

import com.wheelseye.IMS.service.IntervieweeService;



@RestController
@RequestMapping("interviewee")
public class IntervieweeController {

	@Autowired
	private IntervieweeService service;
	

	@GetMapping("interviewees")
	public List<Interviewee>getAllInterviewee()
	{   
		return service.getall();
	}
	
	@GetMapping("interviewees/{id}")
	public Interviewee getInterviewee(@PathVariable(value="id")Long id) throws Exception
	{   
		return service.get(id);
	}
	
	
	@PostMapping("interviewees")
    public ResponseEntity<Interviewee> createInterviewee(@RequestBody Interviewee interviewee)
	{
		Interviewee added= service.add(interviewee);
		return ResponseEntity.ok(added);
	}
	
	@DeleteMapping("interviewees/{id}")
	public Map<String,Boolean> deleteInterviewee(@PathVariable(value="id") Long Iid ) throws Exception
	{
		Interviewee interviewee = service.get(Iid);
		service.delete(interviewee);
				
		Map<String,Boolean> response= new HashMap<>();
		response.put("Record deleted",Boolean.TRUE);
		return response;
		
	}
	@PutMapping("interviewees/{id}")
	public ResponseEntity<Interviewee>  updateInterviewee(@PathVariable(value="id")Long Iid
			,@Validated @RequestBody Interviewee upinterviewee) throws Exception
	{
		Interviewee interviewee= service.get(Iid);
		
		interviewee.setName(upinterviewee.getName());
		interviewee.setEmailID(upinterviewee.getEmailID());
		interviewee.setPhoneNo(upinterviewee.getPhoneNo());
		interviewee.setResume(upinterviewee.getResume());
		
		Interviewee updated = service.add(interviewee);
		
		return ResponseEntity.ok(updated);
		
	}
	
 
}