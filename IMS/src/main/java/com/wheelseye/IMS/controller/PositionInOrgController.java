package com.wheelseye.IMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wheelseye.IMS.model.PositionInOrganization;
import com.wheelseye.IMS.service.JobOpeningService;
import com.wheelseye.IMS.service.PositionInOrganizationService;

@Controller
public class PositionInOrgController {

	
	@Autowired
	PositionInOrganizationService service;
	
	@Autowired
	JobOpeningService serviceJob;
	@RequestMapping("/hr/position_in_org")
	public ModelAndView JobPostionInOrg()
	{
		ModelAndView mv=new ModelAndView("PositionInOrg/position_in_org");
		List<PositionInOrganization> listOrgPos = service.listAll();
		
		mv.addObject("listOrgPos",listOrgPos);
		return mv;
	}
	
	@RequestMapping("/hr/edit/{id}")
	public ModelAndView showEditPostionInOrgPage(@PathVariable(name = "id") Long id) {
	    ModelAndView mv = new ModelAndView("PositionInOrg/edit_pos_in_Org");
	    PositionInOrganization positionInOrganization = service.get(id);
	    mv.addObject("positionInOrganization", positionInOrganization);
	    return mv;
	}
	
	@RequestMapping("/hr/position_in_org/new_pos")
	public ModelAndView showNewPosInOrgPage() {
		ModelAndView mv=new ModelAndView("PositionInOrg/new_job_pos");
	    PositionInOrganization positionInOrganization = new PositionInOrganization();
	    mv.addObject("positionInOrganization", positionInOrganization);
	     
	    return mv;
	}
	
	@RequestMapping(value="/back", method=RequestMethod.POST)
	public String goBack()
	{
		return "redirect:/indexAccess";
	}
	
	@RequestMapping("/hr/delete/{id}")
	public String deleteJobPos(@PathVariable(name="id") Long id)
	{
		service.delete(id);
		return "redirect:/hr/position_in_org";
	}

	
	@RequestMapping(value = "/hr/save", method = RequestMethod.POST)
	public String saveJobPos(@ModelAttribute("positionInOrganization") PositionInOrganization positionInOrganization) {
		if(positionInOrganization.getPositionName().isEmpty())
		{
			return "IncorrectData";
		}
	    service.save(positionInOrganization);
	    return "redirect:/hr/position_in_org";
	}
	
}
