package com.wheelseye.IMS.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wheelseye.IMS.model.Employee;
import com.wheelseye.IMS.model.Role;
import com.wheelseye.IMS.repository.EmployeeRepository;
import com.wheelseye.IMS.repository.RoleRepository;

@Controller
public class AdminController {
	
	@Autowired
	EmployeeRepository repo;
	
	@Autowired
	RoleRepository repoRole;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@RequestMapping("/admin/view_employee")
	public ModelAndView viewEmployeesPage()
	{
		ModelAndView mv=new ModelAndView("admin_view/Employees_present");
		List<Employee> employee=(List<Employee>)repo.findAll();
		List<Employee>empl=new ArrayList<>();
		for(Employee e:employee)
		{
			if(e.getRole().getName().equals("HR") || e.getRole().getName().equals("INTERVIEWER"))
			{
				empl.add(e);
			}
		}
		mv.addObject("empl", empl);
		return mv;
	}
	
	@RequestMapping("/admin/roles")
	public ModelAndView viewRolePage()
	{
		ModelAndView mv = new ModelAndView("admin_view/roles");
		List<Role> listR = repoRole.findAll();
		List<Role> listRole=new ArrayList<>();
		for(Role r:listR)
		{
			if(r.getName().equals("HR") || r.getName().equals("INTERVIEWER"))
				listRole.add(r);
		}
		mv.addObject("listRole", listRole);
		return mv;
	}
	
	@RequestMapping("/admin/new_employee/{role_id}")
	public ModelAndView showNewEmployeePage(@PathVariable(name = "role_id") Long role_id) {
		ModelAndView mv=new ModelAndView("admin_view/new_employee");
	    Employee employee = new Employee();
	    mv.addObject("employee", employee);
	    mv.addObject("role_id", role_id);
	    return mv;
	}
	
	@RequestMapping("admin/save_employee/{id}")
	public String saveEployeeInDb(@ModelAttribute("employee") Employee employee, @PathVariable(name = "id") Long id)
	{
		Role role_obj= (Role)repoRole.findById(id).get();
		employee.setRole(role_obj);
		String encrpyted_pass= encoder.encode(employee.getPassword());
		employee.setPassword(encrpyted_pass);
		employee.setEnabled(true);
		try {
			repo.save(employee);
		    } 
		catch (Exception e) {
		      return "IncorrectData";
		    }
		
		return "redirect:/admin/view_employee";
	}
	
	@RequestMapping("/admin/delete/{id}")
	public String deleteEmployeeCredentials(@PathVariable(name="id") Long id)
	{
		repo.deleteById(id);
		return "redirect:/admin/view_employee";
	}
}
