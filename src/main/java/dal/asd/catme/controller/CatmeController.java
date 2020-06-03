package dal.asd.catme.controller;
import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.CourseDaoImpl;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.InvalidFileFormatException;
import dal.asd.catme.service.*;
import dal.asd.catme.studentlistimport.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dal.asd.catme.config.CatmeSecurityConfig;
import dal.asd.catme.service.ICatmeService;
import dal.asd.catme.util.CatmeUtil;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	IPasswordResetService passwordResetService;

	@Autowired
	IMailSenderService mailSenderService;

	@Autowired
	IEnrollStudentService enrollStudentService;

	@Autowired
	CourseDaoImpl courseDao;

	CatmeSecurityConfig catmeSecurityConfig;

	private static final Logger log = LoggerFactory.getLogger(CatmeController.class);
	
	@RequestMapping("admin")
	public String adminPage()
	{
		return CatmeUtil.ADMIN_PAGE;
	}
	@RequestMapping("home")
	public ModelAndView homePage()
	{
		ModelAndView modelAndView=new ModelAndView();
		if(catmeSecurityConfig.fetchRolesHomePage().equals(CatmeUtil.HOME_PAGE))
		{
			modelAndView.addObject("listOfCourses",catmeService.getAllCourses());
			modelAndView.setViewName(CatmeUtil.HOME_PAGE);
		}
		else
		{
			//logic for populating data on Admin page
			modelAndView.setViewName(CatmeUtil.ADMIN_PAGE);
		}
		return modelAndView;
	}

	@RequestMapping("upload")
	public ModelAndView uploadFilePage()
	{
		ModelAndView uploadPage = new ModelAndView();
		uploadPage.setViewName(CatmeUtil.UPLOAD_CSV_PAGE);

		String courseId = "5307";

		Connection con=null;
		try
		{
			con = database.getConnection();
			uploadPage.addObject("courseId",courseId);
			uploadPage.addObject("studentList",courseDao.getRegisteredStudents(courseId,con));
		} catch (SQLException throwables)
		{
			try
			{
				con.close();
			} catch (SQLException|NullPointerException e)
			{
				e.printStackTrace();
			}
		}
		return uploadPage;
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


		return CatmeUtil.UPLOAD_CSV_PAGE;
	}

	@RequestMapping("forgot-password")
	public String forgotPassword()
	{
		return CatmeUtil.FORGOT_PASSWORD_PAGE;
	}
	@PostMapping("forgot-password")
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
