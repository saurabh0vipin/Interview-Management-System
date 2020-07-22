package com.wheelseye.IMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wheelseye.IMS.model.Job;
import com.wheelseye.IMS.service.JobOpeningService;

@Controller
public class IntervieweeController {

	@Autowired
	JobOpeningService serviceJob;
	@RequestMapping("/interviewee/available_jobs")
	public ModelAndView viewJobsPage()
	{
		ModelAndView mv=new ModelAndView("Applyjobs/available_jobs");
		List<Job> listJob=serviceJob.listAll();
		mv.addObject("listJob", listJob);
		return mv;
	}
	
}
