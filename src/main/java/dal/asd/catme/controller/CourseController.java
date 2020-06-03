package dal.asd.catme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dal.asd.catme.beans.User;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.service.ICourseService;
import dal.asd.catme.util.CatmeUtil;

@Controller
public class CourseController 
{
	@Autowired
	ICourseService courseService;
	
	//Creating Logger
	private static final Logger log = LoggerFactory.getLogger(CourseController.class);
	
	//Finds the access level on course based on User's role
	public ModelAndView identifyAccess(ModelAndView modelAndView,String role)
	{
		log.info("Identifying the User's access to selected course as "+role);
		
		//Enabling access to access to course based on role
		switch(role)
		{
		
		case CatmeUtil.TA_ROLE:
			
			//Enabling access as TA and disabling Instructor access
			log.info("Identified as TA for the selected course");
			modelAndView.addObject("isTa",true);
			modelAndView.addObject("isInstructor",false);
			break;
			
		case CatmeUtil.INSTRUCTOR_ROLE:
			
			//Enabling access as Instructor and disabling Ta access
			log.info("Identified as Instructor for the selected course");
			modelAndView.addObject("isInstructor",true);
			modelAndView.addObject("isTa",false);
			break;
			
		default:
			
			//Disabling both Instructor and TA access
			log.info("User does not have TA/Instructor access to selected course");
			modelAndView.addObject("isInstructor",false);
			modelAndView.addObject("isTa",false);
			break;
			
		}

		return modelAndView;
	}
	
	@RequestMapping("/courseDisplay")
	public ModelAndView diplayCoursePage(@RequestParam(name="courseId") String courseId) throws CatmeException
	{
		log.info("Selected Course page ID: "+courseId);
		User currentUser=new User();
		
		//Fetching current User's user name
		currentUser.setBannerId(SecurityContextHolder.getContext().getAuthentication().getName());
		log.info("User is: "+currentUser.getBannerId());
		ModelAndView modelAndView=new ModelAndView();
		
		//Fetching course based on selected course id
		modelAndView.addObject("course",courseService.displayCourseById(courseId));
		log.info("Checking Database to identify "+currentUser.getBannerId()+" access to Course :"+courseId);
		
		//Finding access level to selected course
		identifyAccess(modelAndView, courseService.findRoleByCourse(currentUser,courseId));
		modelAndView.setViewName(CatmeUtil.COURSE_PAGE);

		return modelAndView;
	}
	

	
}
