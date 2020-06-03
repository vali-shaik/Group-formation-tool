package dal.asd.catme.controller;
import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.InvalidFileFormatException;
import dal.asd.catme.service.*;
import dal.asd.catme.studentlistimport.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.util.CatmeUtil;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
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

	private static final Logger log = LoggerFactory.getLogger(CatmeController.class);
	@RequestMapping("")
	public String homePage()
	{
		log.info("Controller home page!!");

		return CatmeUtil.HOME_PAGE;
	}
	
	@RequestMapping("admin")
	public String adminPage() 
	{
		return CatmeUtil.ADMIN_PAGE;
	}

	@RequestMapping("upload")
	public String uploadFilePage(){ return "file-upload"; }

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
				c.setCourseId("CSCI5308");
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


		return "file-upload";
	}

	@RequestMapping("forgot-password")
	public String forgotPassword(){ return "forgot-password"; }

	@PostMapping("forgot-password")
	public String resetPassword(@RequestParam("bannerid") String bannerid,Model model)
	{
		System.out.println("Reseting password");
		User u = passwordResetService.resetPassword(bannerid);

		if(u==null)
		{
			model.addAttribute("message","User does not exist");
			return "forgot-password";
		}

		try
		{
			mailSenderService.sendNewPassword(u);
			model.addAttribute("success","Password Updated Successfully");
			return "forgot-password";
		} catch (MessagingException e)
		{
			model.addAttribute("message","Error sending mail. Try again");
			return "forgot-password";
		}
	}

}
