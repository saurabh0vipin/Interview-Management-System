package com.wheelseye.IMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wheelseye.IMS.model.Job;
import com.wheelseye.IMS.model.PositionInOrganization;
import com.wheelseye.IMS.service.JobOpeningService;
import com.wheelseye.IMS.service.PositionInOrganizationService;

@Controller
public class JobVacancyController {
	
	@Autowired
	JobOpeningService serviceJob;
	
	@Autowired
	PositionInOrganizationService servicePos;
	
	@RequestMapping("/hr/job_openings")
	public ModelAndView JobVacancy()
	{
		ModelAndView mv=new ModelAndView("JobOpening/job_openings");
		List<Job> listJob = serviceJob.listAll();
		mv.addObject("listJob",listJob);
		return mv;
	}
	
	@RequestMapping("/hr/post_vacancy/{id}")
	public ModelAndView showNewVacancyPage(@PathVariable(name = "id") Long id)
	{
		ModelAndView mv=new ModelAndView("JobOpening/new_job_opening");
		
		Job job=new Job();
		PositionInOrganization pos_in_org= servicePos.get(id);
		job.setPosIn(pos_in_org);
		job.setDatePosted(""+java.time.LocalDate.now());
		mv.addObject("job",job);
		mv.addObject("id",id);
		return mv;
	}
	
	@RequestMapping(value="/hr/save_vacancy/{id}", method = RequestMethod.POST)
	public String saveJobOpening( @PathVariable(name = "id") Long id, @ModelAttribute("job") Job job) {
		
		PositionInOrganization pos_in_org= servicePos.get(id);
		job.setPosIn(pos_in_org);
		job.setDatePosted(""+java.time.LocalDate.now());
	    serviceJob.save(job);
	    return "redirect:/hr/position_in_org";
	    }
	
	
	@RequestMapping("/hr/job_openings/edit/{id}")
	public ModelAndView showJobVacancyPage(@PathVariable(name = "id") Long id) {
	    ModelAndView mv = new ModelAndView("JobOpening/edit_job_opening");
	    Job job = serviceJob.get(id);
	    mv.addObject("job", job);
	    return mv;
	}
	
	@RequestMapping(value="/hr/job_openings/save",method = RequestMethod.POST)
	public String editedSave(@ModelAttribute("job") Job job)
	{
		serviceJob.save(job);
		return "redirect:/hr/job_openings";
	}

	@RequestMapping("/hr/job_openings/delete/{id}")
	public String deleteJobOpening(@PathVariable(name="id") Long id)
	{
		serviceJob.delete(id);
		return "redirect:/hr/job_openings";
	}

}