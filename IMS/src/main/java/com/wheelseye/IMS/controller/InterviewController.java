package com.wheelseye.IMS.controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wheelseye.IMS.mail.HtmlEmailSender;
import com.wheelseye.IMS.model.Application;
import com.wheelseye.IMS.model.Employee;
import com.wheelseye.IMS.model.Interview;
import com.wheelseye.IMS.model.Round;
import com.wheelseye.IMS.sec.MyEmployeeDetails;
import com.wheelseye.IMS.service.ApplicationService;
import com.wheelseye.IMS.service.EmployeeDetailsServiceImpl;
import com.wheelseye.IMS.service.InterviewService;
import com.wheelseye.IMS.service.RoundService;

@Controller
public class InterviewController {
	
	@Autowired
	InterviewService service;
	
	@Autowired
	EmployeeDetailsServiceImpl serviceEmp;
	
	@Autowired
	RoundService serviceR;
	
	@Autowired
	ApplicationService serviceApp;
	
	@RequestMapping("/hr/sch_interviews")
	public ModelAndView viewInterviewPage()
	{
		ModelAndView mv=new ModelAndView("hr_views/available_interviews");
		List<Application> listApplication=serviceApp.toBeScheduled();
		
		mv.addObject("listApplication", listApplication);
		return mv;
	}
		
	@RequestMapping("/hr/show_interviewers/{interview_id}")
	public ModelAndView viewInterviewerPage(@PathVariable(name="interview_id") Long interview_id)
	{
		ModelAndView mv=new ModelAndView("hr_views/interviewersList");
		List<Employee> interviewers = serviceEmp.getInterviewers() ;
		mv.addObject("interviewers", interviewers);
		mv.addObject("interview_id", interview_id);
		return mv;
	}
	
	@GetMapping("/hr/availibiltycheck/{eId}/{iId}")
	public ModelAndView viewAvailibilityPage(@PathVariable(value="eId") Long eId, @PathVariable(value="iId") Long iId)
	{
		ModelAndView mv= new ModelAndView("availibility_page");
		mv.addObject("interviewer",serviceEmp.get(eId));
		mv.addObject("interview", service.get(iId));
		return mv;
	}
	
	@PostMapping("/hr/availability/{eId}/{iId}")
	public ModelAndView machingOverlap(@PathVariable(value="eId")Long eId,@RequestParam(value="date")String date,
			@RequestParam(value="duration")Integer dur,@PathVariable(value="iId")Long iId) throws ParseException
	{
		String message = new String("");
		System.out.println(date);
	    
	    ModelAndView mav = new ModelAndView();
	    
	    mav.addObject("eId", eId);
	    mav.addObject("iId",iId);
	    mav.addObject("message", message);
	 	List<Round> rounds=serviceR.getByEmployeeIdAndStatus(eId,"Scheduled");
	 	if(rounds.size()==0)
	 	{message="Available";
	 	}
		
		LocalDateTime newDate= LocalDateTime.parse(date);
		System.out.println(newDate);
		for(Round round:rounds)
		{ 
			LocalDateTime rdate=round.getStartDate();
			//System.out.println(rdate);
			LocalDateTime redate=rdate.plusHours(round.getDuration());
			//System.out.println(redate);
			if((newDate.compareTo(rdate)>=0 && newDate.compareTo(redate)<0)||
					(newDate.plusHours(dur).compareTo(rdate)>0 && newDate.plusHours(dur).compareTo(redate)<=0))
			{
				
				message="Unavailable";
				break;
			}
			else
			{
				message="Available";
			}
			
		}
		System.out.println(message);
		
		mav.setViewName("availibility_page");
		mav.addObject("date", date);
		mav.addObject("duration",dur);
		mav.addObject("message", message);
		
		return mav;
		
	}
	
	@RequestMapping("/hr/scheduleround/{employee_id}/{interview_id}/{date}/{duration}")
	public ModelAndView makeNewRound(@PathVariable(value="interview_id") Long iId,@PathVariable(value="employee_id") Long eId, @PathVariable(value="date")String date,@PathVariable(value="duration")Integer dur)
	{
		ModelAndView mv=new ModelAndView();
		Employee interviewer=serviceEmp.get(eId);
		Interview interview=service.get(iId);
		
		String status= interview.getStatus();
		System.out.println(status);
		if(status.equals("Applied"))
		{
			interview.setStatus("ScheduledRound1");
			service.save(interview);
		}
		else if(status.equals("QualifiedRound1"))
		{
			interview.setStatus("ScheduledRound2");
			service.save(interview);
		}
		else if(status.equals("QualifiedRound2"))
		{
			interview.setStatus("ScheduledRound3");
			service.save(interview);
		}
		else //resheduled fall
		{
			Round rnd = serviceR.getRoundByInterviewIdAndRoundStatus(iId,"Scheduled");
			rnd.setDuration(dur);
			rnd.setStartDate(LocalDateTime.parse(date));
			rnd.setEmployee(interviewer);
			serviceR.save(rnd);
			mv.setViewName("availibility_page");
			return mv;
		}
		
		Round round= new Round();
		round.setDuration(dur);
		round.setEmployee(interviewer);
		round.setInterview(interview);
		round.setRoundStatus("Scheduled");
		round.setStartDate(LocalDateTime.parse(date));
		//----------------------
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		MyEmployeeDetails currUser = (MyEmployeeDetails)authentication.getPrincipal();
		
		String intervieweeMailId=serviceR.getIntervieweeMailId(interview);
		String jobPosition=serviceR.getJobPos(interview);
		String rnd=interview.getStatus();
		String hrName=currUser.getUsername();
		Long phoneNo=currUser.getPhone();
		//-------------------
		
		Long intervieweeId=serviceR.getIntervieweeId(interview);
		
		//----------------------
		HtmlEmailSender mailer = new HtmlEmailSender();
		try {
			mailer.sendRoundEmail(intervieweeMailId, rnd, jobPosition, date,  hrName, phoneNo);
			mailer.sendInterviewerMail(intervieweeId, interviewer.getEmailid(), rnd, jobPosition, date, hrName, phoneNo);
			System.out.println("Email Successfully Sent.");
		} catch (Exception ex) {
			System.out.println("Failed to sent email.");
			ex.printStackTrace();
		}
		
		serviceR.save(round);
		mv.setViewName("availibility_page");
		return mv;
	}
	
	
		
	@RequestMapping("/hr/rounds")
	public ModelAndView showRoundPage()
	{
		ModelAndView mv= new ModelAndView("round_view/rounds");
		List<Round> round=serviceR.listAll();
		mv.addObject("round", round);
		return mv;
	}
	
	
	@RequestMapping("/hr/resch_cancel_interviews")
	public ModelAndView reshedule_cancelPage()
	{
		List<Round> schRound= serviceR.getAllscheduledRound();
		ModelAndView mv=new ModelAndView("hr_views/sch_cancl_view");
		mv.addObject("schRound", schRound);
		return mv;
	}
	
	@RequestMapping("/hr/cancel_round/{id}")
	public String cancelRound(@PathVariable(name="id") Long id)
	{
		Long rnd=serviceR.getInterviewId(id);
		Interview interview=service.get(rnd);
		String status=interview.getStatus();
		if(status.equals("ScheduledRound1"))
		{
			interview.setStatus("Applied");
		}
		else if(status.equals("ScheduledRound2"))
		{
			interview.setStatus("QualifiedRound1");
		}
		else
		{
			interview.setStatus("QualifiedRound2");
		}
		serviceR.delete(id);
		return "redirect:/hr/resch_cancel_interviews";
	}
	
	@RequestMapping("/hr/reshedule_round/{id}")
	public String resheduleRound(@PathVariable(name="id") Long id)
	{
		Long x=serviceR.getInterviewId(id);
//		serviceR.delete(id);
		return "redirect:/hr/show_interviewers/"+x;
	}
	
	@RequestMapping("/hr/change_status")
	public ModelAndView changeStatusPage()
	{
		ModelAndView mv=new ModelAndView("hr_views/accept_reject_promote");
		List<Round>round=serviceR.getFinishedRounds();
		
		mv.addObject("round", round);
		return mv;
	}
	@RequestMapping("/hr/accept/{id}")
	public String accept(@PathVariable(value="id")Long id)
	{
		Interview interview=serviceR.getById(id).getInterview();
		interview.setStatus("Accepted");
		
		//--------------------------
		String intervieweeMailId=serviceR.getIntervieweeMailId(interview);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		MyEmployeeDetails currUser = (MyEmployeeDetails)authentication.getPrincipal();
		String hrName=currUser.getUsername();
		String jobPosition=serviceR.getJobPos(interview);
		String intervieweeName=serviceR.getIntervieweeName(interview);
		HtmlEmailSender mailer = new HtmlEmailSender();
		try {
			mailer.sendAcceptMail(intervieweeMailId, hrName, intervieweeName, jobPosition);
			System.out.println("Email Successfully Sent.");
		} catch (Exception ex) {
			System.out.println("Failed to sent email.");
			ex.printStackTrace();
		}
		//------------------------
		
		service.save(interview);
		return "redirect:/hr/change_status";
	}
	
	@RequestMapping("/hr/reject/{id}")
	public String reject(@PathVariable(value="id")Long id)
	{
		Interview interview=serviceR.getById(id).getInterview();
		interview.setStatus("Rejected");
		
		//--------------------------
				String intervieweeMailId=serviceR.getIntervieweeMailId(interview);
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				MyEmployeeDetails currUser = (MyEmployeeDetails)authentication.getPrincipal();
				String hrName=currUser.getUsername();
				String jobPosition=serviceR.getJobPos(interview);
				String intervieweeName=serviceR.getIntervieweeName(interview);
				HtmlEmailSender mailer = new HtmlEmailSender();
				try {
					mailer.sendRejectMail(intervieweeMailId, hrName, intervieweeName, jobPosition);
					System.out.println("Email Successfully Sent.");
				} catch (Exception ex) {
					System.out.println("Failed to sent email.");
					ex.printStackTrace();
				}
		//------------------------
				
		service.save(interview);
		return "redirect:/hr/change_status";
	}
	
	@RequestMapping("/hr/promote_stage/{id}")
	public String Promote(@PathVariable(value="id")Long id)
	{
		Interview interview=serviceR.getById(id).getInterview();
		
		if(interview.getStatus().equals("ScheduledRound1"))
		{
			interview.setStatus("QualifiedRound1");
		}
		else if(interview.getStatus().equals("ScheduledRound2"))
		{
			interview.setStatus("QualifiedRound2");
		}
		else
		{
			interview.setStatus("Accepted");
		}
		service.save(interview);
		return "redirect:/hr/change_status";
	}
	
	@RequestMapping("/interviewer/rate")
	public ModelAndView rateRound()
	{
		ModelAndView mv=new ModelAndView("round_view/rate_rounds");
		List<Round>roundAll=serviceR.listAll();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		MyEmployeeDetails currUser = (MyEmployeeDetails)authentication.getPrincipal();
		Long interviewer_id=currUser.getUserId();
		List<Round>round=new ArrayList<>();
		for(Round r:roundAll)
		{
			if(r.getEmployee().getId().equals(interviewer_id) && r.getRoundStatus().equals("Scheduled"))
			{
				round.add(r);
			}
		}
		mv.addObject("round", round);
		return mv;
	}
	
	@RequestMapping("/interviewer/rate_rounds/{round_id}")
	public ModelAndView ratingsAndFeedBack(@PathVariable(value="round_id") Long round_id)
	{
		ModelAndView mv= new ModelAndView("round_view/rating_edit");
		Round rnd=serviceR.getById(round_id);
		mv.addObject("rnd", rnd);
		return mv;
	}
	
	@RequestMapping("/interviewer/save_rating/{round_id}")
	public String saveRatingAndFeedBack(@PathVariable(value="round_id") Long round_id,@ModelAttribute("rnd") Round rnd)
	{
		Round round=serviceR.getById(round_id);
		rnd.setDuration(round.getDuration());
		rnd.setEmployee(round.getEmployee());
		rnd.setInterview(round.getInterview());
		rnd.setRoundId(round.getRoundId());
		rnd.setRoundStatus("Finished");
		rnd.setStartDate(round.getStartDate());
		serviceR.save(rnd);
		return "redirect:/interviewer/rate";
	}
	
	
}
