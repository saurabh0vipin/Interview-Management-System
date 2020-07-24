package com.wheelseye.IMS.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wheelseye.IMS.model.Application;
import com.wheelseye.IMS.model.Interview;
import com.wheelseye.IMS.model.Interviewee;
import com.wheelseye.IMS.model.Job;
import com.wheelseye.IMS.service.ApplicationService;
import com.wheelseye.IMS.service.IntervieweeStorageService;
import com.wheelseye.IMS.service.JobOpeningService;

@Controller
public class HomeController {
	
	@Autowired
	IntervieweeStorageService intervieweeStorageService;
	
	@Autowired
	ApplicationService serviceApp;
	
	@Autowired
	JobOpeningService serviceJob;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		return "index";
	}	
	
	@RequestMapping("/listinterviewee")
	public String get(Model model) {
		List<Interviewee> listInterviewees = intervieweeStorageService.listAll();
		model.addAttribute("listInterviewees", listInterviewees);
		return "interviewee/interviewee";
	}
	
	@RequestMapping("/interviewee/newinterviewee/{id}")
	public String showNewIntervieweeForm(@PathVariable(name = "id") Long job_id, Model model) {
		Interviewee interviewee = new Interviewee();
		model.addAttribute("interviewee", interviewee);
		model.addAttribute("job_id", job_id);
		return "interviewee/new_interviewee";
	}
	
	@RequestMapping(value = "/interviewee/saveinterviewee/{job_id}", method = RequestMethod.POST)
	public String saveInterviewee(@PathVariable(name = "job_id") Long job_id, @ModelAttribute("interviewee") Interviewee interviewee, @RequestParam("file") MultipartFile file){
		try {
			interviewee.setData(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		interviewee.setResumeType(file.getContentType());
		interviewee.setResumeName(file.getOriginalFilename());
		intervieweeStorageService.save(interviewee);
		
		Interview itrvew=new Interview();
		
		itrvew.setStatus("Applied");
		
		Job job=serviceJob.get(job_id);
		Application application=new Application();
		application.setDate(new Date());
		application.setJob(job);
		application.setInterviewee(interviewee);
		application.setInterview(itrvew);
		
		serviceApp.save(application);
		
		return "redirect:/";
	}
	
	@RequestMapping("/editinterviewee/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("interviewee/edit_interviewee");
		
		Interviewee interviewee = intervieweeStorageService.get(id);
		mav.addObject("interviewee", interviewee);
		return mav;
	}	
	
	@RequestMapping("/deleteinterviewee/{id}")
	public String deleteInterviewee(@PathVariable(name = "id") Long id) {
		intervieweeStorageService.delete(id);
		
		return "redirect:/listinterviewee";
	}

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId){
		Interviewee interviewee = intervieweeStorageService.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(interviewee.getResumeType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+interviewee.getResumeName()+"\"")
				.body(new ByteArrayResource(interviewee.getData()));
	}
}
