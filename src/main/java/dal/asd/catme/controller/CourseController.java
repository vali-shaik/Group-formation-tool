package dal.asd.catme.controller;

import static dal.asd.catme.util.MailSenderUtil.TOKEN_LENGTH;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Enrollment;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.CatmeSecurityConfig;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.exception.InvalidFileFormatException;
import dal.asd.catme.service.ICourseService;
import dal.asd.catme.service.IEnrollStudentService;
import dal.asd.catme.service.IMailSenderService;
import dal.asd.catme.service.IRoleService;
import dal.asd.catme.service.IUserService;
import dal.asd.catme.studentlistimport.ICSVReader;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.RandomTokenGenerator;

@Controller
public class CourseController 
{
	ICourseService courseService;

	IEnrollStudentService enrollStudentService;

	IUserService userService;

	IMailSenderService mailSenderService;

	CatmeSecurityConfig catmeSecurityConfig;
	
	ICSVReader csvReaderImpl;
	
	IRoleService roleService;
	
	//Creating Logger
	private static final Logger log = LoggerFactory.getLogger(CourseController.class);
	
	
	

	@GetMapping("taEnrollment/{courseId}")
	public String enrollTa(@PathVariable("courseId")String courseId,Model model) {
		model.addAttribute("courseId", courseId);
		return CatmeUtil.TA_ENROLLMENT_PAGE;
	}

	@RequestMapping("taEnrollment/access")
	public String  redirectHomePage()
	{
		//Redirecting the user to home page with associated courses
		log.info("Finding the Role of User and redirecting to respetive User's home page");
		return "redirect:/access";
	}

	@RequestMapping("taEnrollment/{courseId}")
	public String enrollTa(@PathVariable("courseId")String courseId,@RequestParam String bannerId,Model model) {
		Enrollment user = new Enrollment(bannerId,courseId);
		model.addAttribute("user", user);
		roleService=SystemConfig.instance().getRoleService();
		String message = roleService.assignTa(user);
		model.addAttribute("message", message);
		return CatmeUtil.TA_ENROLLED_PAGE;
	}
	
	
	
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
		courseService=SystemConfig.instance().getCourseService();
		modelAndView.addObject("course",courseService.displayCourseById(courseId));
		log.info("Checking Database to identify "+currentUser.getBannerId()+" access to Course :"+courseId);
		
		//Finding access level to selected course
		identifyAccess(modelAndView, courseService.findRoleByCourse(currentUser,courseId));
		modelAndView.setViewName(CatmeUtil.COURSE_PAGE);

		return modelAndView;
	}

	@RequestMapping("/manageCourse")
	public ModelAndView manageCourse(@RequestParam(name="courseId") String courseId)
	{

		log.info("Manage Course "+courseId);
		ModelAndView uploadPage = new ModelAndView();

		try
		{
			catmeSecurityConfig=SystemConfig.instance().getCatmeServiceConfig();
			List<String> roles = catmeSecurityConfig.fetchRolesHomePage();

			if(!roles.contains(CatmeUtil.INSTRUCTOR_ROLE) && !roles.contains(CatmeUtil.TA_ROLE))
			{
				uploadPage.setViewName(CatmeUtil.LOGIN_PAGE);
				return uploadPage;
			}

			uploadPage.setViewName(CatmeUtil.MANAGE_COURSE_PAGE);

			uploadPage.addObject("courseId",courseId);
			courseService=SystemConfig.instance().getCourseService();
			uploadPage.addObject("studentList",courseService.getEnrolledStudents(courseId));
		}
		catch (CatmeException e)
		{
			e.printStackTrace();
		}

		return uploadPage;
	}


	@PostMapping("/manageCourse")
	public ModelAndView uploadFile(@RequestParam("student-list-csv") MultipartFile file, @RequestParam("courseId") String courseId)
	{
		ModelAndView model = new ModelAndView();
		
		csvReaderImpl=SystemConfig.instance().getCsvReaderImpl();

//		CSVReader readerImpl = new CSVReader();

		try
		{
			csvReaderImpl.validateFile(file);
			Course c= new Course();
			c.setCourseId(courseId);
			ArrayList<Student> students =  csvReaderImpl.readFile(file.getInputStream());

			userService = SystemConfig.instance().getUserService();
			enrollStudentService=SystemConfig.instance().getEnrollStudentService();
			mailSenderService=SystemConfig.instance().getMailSenderService();

			for(Student s: students)
			{
				s.setPassword(RandomTokenGenerator.generateRandomPassword(TOKEN_LENGTH));
				if(userService.addUser(s).equals(CatmeUtil.USER_CREATED))
				{
					mailSenderService.sendCredentialsToStudent(s,c);
				}
			}

			if(enrollStudentService.enrollStudentsIntoCourse(students,c))
			{
				model.addObject("message","Students Enrolled");
			}
			else
			{
				model.addObject("message","Error Enrolling Students");
			}
		} catch (InvalidFileFormatException e)
		{
			model.addObject("message",e.getMessage());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} catch (MessagingException e)
		{
			model.addObject("message",e.getMessage());
		}
		model.setViewName(CatmeUtil.MANAGE_COURSE_PAGE);
		return model;
	}
		
}
