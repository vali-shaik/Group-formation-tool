package dal.asd.catme.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dal.asd.catme.service.IUserService;
import dal.asd.catme.util.RandomTokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.CatmeSecurityConfig;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.exception.InvalidFileFormatException;
import dal.asd.catme.service.ICourseService;
import dal.asd.catme.service.IEnrollStudentService;
import dal.asd.catme.service.IMailSenderService;
import dal.asd.catme.studentlistimport.CSVReader;
import dal.asd.catme.util.CatmeUtil;

import javax.mail.MessagingException;

import static dal.asd.catme.util.MailSenderUtil.TOKEN_LENGTH;

@Controller
public class CourseController 
{
	ICourseService courseService;

	IEnrollStudentService enrollStudentService;

	IUserService userService;

	IMailSenderService mailSenderService;

	CatmeSecurityConfig catmeSecurityConfig;
	
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

//			String dis = "Type: "+file.getContentType();
//			dis+="\nName: "+file.getOriginalFilename();
//			model.addAttribute("message",dis);

			CSVReader reader = new CSVReader();

			try
			{
				reader.validateFile(file);
				Course c= new Course();
				c.setCourseId(courseId);
				ArrayList<Student> students =  reader.readFile(file.getInputStream());

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
