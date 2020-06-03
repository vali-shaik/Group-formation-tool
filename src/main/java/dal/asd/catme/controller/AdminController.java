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
import dal.asd.catme.beans.User;
import dal.asd.catme.service.IAdminService;
import dal.asd.catme.service.IListDetails;
import dal.asd.catme.util.CatmeUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IAdminService service;
	
	@Autowired
	IListDetails listDetail;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping("dashboard")
	public String adminPage() 
	{
		logger.info("****Admin Controller - Admin Page Invoked*****");
		
		return CatmeUtil.ADMIN;
	}
	
	@RequestMapping("addCourse")
	public String addCourse(Model model)
	{
		logger.info("****Admin Controller - Add Course Invoked*****");
		model.addAttribute("course", new Course());
		return CatmeUtil.ADD_COURSE;
	}
	
	@PostMapping("addCourseToDatabase")
	public String addCourseToDatabase(@ModelAttribute Course course)
	{
		logger.info("****Admin Controller - Add Course to Database Invoked*****");
		int result =service.addCourse(course);
		if(result>0)
			return CatmeUtil.SUCCESS_PAGE;
		else
			return CatmeUtil.ERROR;
	}
	

	@ModelAttribute("courses")
	public List<Course> getCourseList(){
		return listDetail.getAllCourses();
	}
	
	@ModelAttribute("users")
	public List<User> getUsersList(){
		Course course = new Course();
		return listDetail.getUsers(course);	
	}
	
	
	@PostMapping(value="deleteCourse")
	public String deleteCourse(@RequestParam String course)
	{
		logger.info("****Admin Controller - Delete Course Invoked*****");
		System.out.println(course);
		int result=service.deleteCourse(course);
		if(result>0)
			return CatmeUtil.SUCCESS_PAGE;
		else
			return CatmeUtil.ERROR;
		
	}
	
	@PostMapping("addInstructor")
	public String addInstructor(@RequestParam String course,@RequestParam String user) {
		logger.info("****Admin Controller - Add Instructor Invoked*****");
		int result=service.addInstructorToCourse(user,course);
		if(result==1)
			return CatmeUtil.SUCCESS_PAGE;
		else if(result>1)
			return CatmeUtil.USER_PAGE;
		else
			return CatmeUtil.ERROR;
	}
}
