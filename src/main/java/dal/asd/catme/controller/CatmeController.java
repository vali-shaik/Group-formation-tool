package dal.asd.catme.controller;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.CatmeSecurityConfig;
import dal.asd.catme.dao.CourseDaoImpl;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.exception.InvalidFileFormatException;
import dal.asd.catme.service.*;
import dal.asd.catme.studentlistimport.CSVReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dal.asd.catme.beans.Enrollment;
import dal.asd.catme.beans.User;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.service.ICatmeService;
import dal.asd.catme.service.IRoleService;
import dal.asd.catme.service.IUserService;
import dal.asd.catme.util.CatmeUtil;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


@Controller
@RequestMapping("/")
public class CatmeController {

	@Autowired
	ICatmeService catmeService;

	@Autowired
	DatabaseAccess database;
	@Autowired
	IUserService userService;
	@Autowired
	IRoleService roleService;
	
	

	@Autowired
	IPasswordResetService passwordResetService;

	@Autowired
	IMailSenderService mailSenderService;

	@Autowired
	IEnrollStudentService enrollStudentService;

	@Autowired
	CourseDaoImpl courseDao;

	private static final Logger log = LoggerFactory.getLogger(CatmeController.class);
	
	@RequestMapping("admin")
	public String adminPage()
	{
		return CatmeUtil.ADMIN_PAGE;
	}
	
	
	@GetMapping("signup")
	public String signupPage(Model model) {
		model.addAttribute("user",new User());
		
		return CatmeUtil.SIGNUP_PAGE;
	}
	
	@PostMapping("signup")
	public String signupPage(@ModelAttribute User user, Model model) {
		String message = userService.addUser(user);
		model.addAttribute("message", message);
		return CatmeUtil.SIGNEDUP_PAGE;
	}
	
	
	@GetMapping("taEnrollment/{courseId}")
	public String enrollTa(@PathVariable("courseId")String courseId,Model model) {
		model.addAttribute("courseId", courseId);
		return CatmeUtil.TA_ENROLLMENT_PAGE;
	}
	
	@PostMapping("taEnrollment/{courseId}")
	public String enrollTa(@PathVariable("courseId")String courseId,@RequestParam String bannerId,Model model) {
		Enrollment user = new Enrollment(bannerId,courseId);
		model.addAttribute("user", user);
		String message = roleService.assignTa(user);
		model.addAttribute("message", message);
		return CatmeUtil.TA_ENROLLED_PAGE;
	}

	

	@Autowired
	CatmeSecurityConfig catmeServiceConfig;

	//Creating Logger

	//Decides landing page of application based on user authorization
	@RequestMapping("access")
	public String findLandingPage() throws CatmeException
	{
		log.info("Finding the landing page of application based on access level"); 
		
		//Defining home page for user
		String page="";
		
		//Fetching the all roles of current user
		List<String> roles= catmeServiceConfig.fetchRolesHomePage();
		
		//Checking for current user role and redirecting to respective landing page
		if(roles!=null)
		{
			if(roles.contains("ROLE_ADMIN"))
			{
				//Admin role
				log.info("User idetified as Admin"); 
				return "redirect:/"+CatmeUtil.ADMIN_HOME;
			}
			if(roles.contains("ROLE_INSTRUCTOR"))
			{
				//Instructor role
				log.info("User idetified as Instructor");
				return "redirect:/"+CatmeUtil.INSTRUCTOR_HOME;
			}
			if(roles.contains("ROLE_TA"))
			{
				//TA role
				log.info("User idetified as TA");
				return "redirect:/"+CatmeUtil.TA_HOME;
			}
			if(roles.contains("ROLE_STUDENT"))
			{
				//Student role
				log.info("User idetified as Student");
				return "redirect:/"+CatmeUtil.STUDENT_HOME;
			}
			if(roles.contains("ROLE_GUEST"))
			{
				//Guest role
				log.info("User idetified as Guest");
				return "redirect:/"+CatmeUtil.GUEST_HOME;
			}
		}
		//If User does not have any of the defined roles, leads to error page
		if(page.trim().length()==0)
		{
			page="error";
			throw new CatmeException("User Role is not found in Database");
		}
		return page;
	}

	//Displaying Login page for authentication
	@RequestMapping("login")
	public String loginPage()
	{
		log.info("Redirected to Login page");
		return CatmeUtil.LOGIN_PAGE;
	}

	@PostMapping("upload")
	public String uploadFile(@RequestParam("student-list-csv") MultipartFile file, Model model) {


		if(file.isEmpty())
		{
			log.info("File is Empty");
			model.addAttribute("message","Please Upload File");
		}


		else if(file.getSize()>=10*1024*1024)
		{
			log.info("File is Big");
			model.addAttribute("message","Please Upload File less than 10 mb");
		}

		else if(!file.getContentType().equals("text/csv")){

			model.addAttribute("message","Please Select CSV File");
		}

		else
		{
			String dis = "Type: "+file.getContentType();
			dis+="\nName: "+file.getOriginalFilename();
			model.addAttribute("message",dis);

			CSVReader reader = new CSVReader();

			try
			{
				Course c= new Course();
				c.setCourseName("Advance SDC");
				c.setCourseId("5308");
				ArrayList<Student> students =  reader.readFile(file.getInputStream());

				if(enrollStudentService.enrollStudentsIntoCourse(students,c))
				{
					model.addAttribute("message","Students Enrolled");
				}
				else
				{
					model.addAttribute("message","Error Enrolling Students");
				}



			} catch (InvalidFileFormatException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}


		return CatmeUtil.MANAGE_COURSE_PAGE;
	}

	@RequestMapping("forgotPassword")
	public String forgotPassword()
	{
		return CatmeUtil.FORGOT_PASSWORD_PAGE;
	}
	@PostMapping("forgotPassword")
	public String resetPassword(@RequestParam("bannerid") String bannerid,Model model)
	{
		System.out.println("Reseting password");
		User u = passwordResetService.resetPassword(bannerid);

		if(u==null)
		{
			model.addAttribute("message","User does not exist");
			return CatmeUtil.FORGOT_PASSWORD_PAGE;
		}

		try
		{
			mailSenderService.sendNewPassword(u);
			model.addAttribute("success","Password Updated Successfully");
			return CatmeUtil.FORGOT_PASSWORD_PAGE;
		} catch (MessagingException e)
		{
			model.addAttribute("message","Error sending mail. Try again");
			return CatmeUtil.FORGOT_PASSWORD_PAGE;
		}
	}

}
