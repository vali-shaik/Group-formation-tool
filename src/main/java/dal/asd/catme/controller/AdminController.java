package dal.asd.catme.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.beans.Course;
import dal.asd.catme.service.IAdminService;
import dal.asd.catme.util.CatmeUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
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
}
