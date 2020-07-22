package com.wheelseye.IMS.controller;

import java.io.IOException;
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

import com.wheelseye.IMS.model.Interviewee;
import com.wheelseye.IMS.service.IntervieweeStorageService;

@Controller
public class HomeController {
	
	@Autowired
	IntervieweeStorageService intervieweeStorageService;
	
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
	
	@RequestMapping("/newinterviewee")
	public String showNewIntervieweeForm(Model model) {
		Interviewee interviewee = new Interviewee();
		model.addAttribute("interviewee", interviewee);
		
		return "interviewee/new_interviewee";
	}
	
	@RequestMapping(value = "/saveinterviewee", method = RequestMethod.POST)
	public String saveInterviewee(@ModelAttribute("interviewee") Interviewee interviewee, @RequestParam("file") MultipartFile file){
		try {
			interviewee.setData(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		interviewee.setResumeType(file.getContentType());
		interviewee.setResumeName(file.getOriginalFilename());
		
		intervieweeStorageService.save(interviewee);
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
