package dal.asd.catme.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.service.ICatmeService;
import dal.asd.catme.service.ICourseService;
import dal.asd.catme.util.CatmeUtil;

@Controller
@RequestMapping("profile/")
public class ProfileController 
{
	//Creating Logger
	private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
	
	@Autowired
	ICourseService courseService;
	
	@Autowired
	ICatmeService catmeServie;
	
	@RequestMapping("access")
	public String  redirectHomePage()
	{
		//Redirecting the user to home page with associated courses
		log.info("Finding the Role of User and redirecting to respetive User's home page");
		return "redirect:/access";
	}
	
	@RequestMapping("student")
	public ModelAndView  displayStudentHomePage() throws CatmeException
	{
		//Displaying the student home page
		log.info("User is identified as a Student");
		ModelAndView modelAndView =new ModelAndView();
		
		//Fetching courses of current Student
		modelAndView.addObject("listOfCourses",courseService.getCourses(CatmeUtil.STUDENT_ROLE));
		modelAndView.setViewName(CatmeUtil.HOME_PAGE);
		return modelAndView;
	}
	
	@RequestMapping("ta")
	public ModelAndView displayTaHomePage() throws CatmeException
	{
		//Displaying the student/TA home page
		log.info("User is identified as a Student/TA");
		ModelAndView modelAndView =new ModelAndView();
		
		//Fetching courses of current Student
		modelAndView.addObject("listOfCourses",courseService.getCourses(CatmeUtil.TA_ROLE));
		modelAndView.setViewName(CatmeUtil.HOME_PAGE);
		return modelAndView;
	}
	
	@RequestMapping("instructor")
	public ModelAndView displayInstructorHomePage() throws CatmeException
	{
		//Displaying the Instructor home page
		log.info("User is identified as a Instructor");
		ModelAndView modelAndView =new ModelAndView();
		
		//Fetching courses of current Student
		modelAndView.addObject("listOfCourses",courseService.getCourses(CatmeUtil.INSTRUCTOR_ROLE));
		modelAndView.setViewName(CatmeUtil.HOME_PAGE);
		return modelAndView;
	}
	
	@RequestMapping("guest")
	public ModelAndView  displayGuestHomePage() throws CatmeException
	{
		//Displaying the Guest home page
		log.info("User is identified as Guest");
		ModelAndView modelAndView =new ModelAndView();
		
		//Fetching courses of current Student
		modelAndView.addObject("listOfCourses",courseService.getCourses(CatmeUtil.GUEST_ROLE));
		modelAndView.setViewName(CatmeUtil.HOME_PAGE);
		return modelAndView;
	}

}
