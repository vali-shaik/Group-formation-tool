package dal.asd.catme.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dal.asd.catme.beans.Course;
import dal.asd.catme.service.IAdminService;
import dal.asd.catme.util.CatmeUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	@Qualifier("adminService")
	IAdminService service;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping("dashboard")
	public String adminPage() 
	{
		logger.info("****Admin Controller - Admin Page Invoked*****");
		
		return CatmeUtil.ADMIN;
	}
	
	@RequestMapping("addCourse")
	public String addCourse()
	{
		logger.info("****Admin Controller - Add Course Invoked*****");
		return CatmeUtil.ADD_COURSE;
	}

	@ModelAttribute("courses")
	public List<Course> getCourseList(){
		return service.getAllCourses();
		
	}
	
	@PostMapping(value="deleteCourse")
	public String deleteCourse(@RequestParam String course)
	{
		logger.info("****Admin Controller - Delete Course Invoked*****");
		int result=service.deleteCourse(course.substring(0, course.length()-1));
		if(result>0)
			return CatmeUtil.DELETE_COURSE;
		else
			return CatmeUtil.ERROR_PAGE;
		
	}
}
