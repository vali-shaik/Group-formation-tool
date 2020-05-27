package dal.asd.catme.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.beans.Course;
import dal.asd.catme.dao.AdminDao;
import dal.asd.catme.dao.IAdminDao;
import dal.asd.catme.service.AdminService;
import dal.asd.catme.service.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	IAdminService service = new AdminService();
	IAdminDao admin = new AdminDao();
	@RequestMapping("")
	public String adminPage()
	{
		return "adminPage";
	}
	
	@RequestMapping("addCourse")
	public String addCourse()
	{
		System.out.println("Inside add course");
		getCourseList();
		return "addCourse";
	}

//	@RequestMapping("getCourse")
	public void getCourseList(){
		System.out.println("Inside get course list");
		service.getAllCourses();
	}
}
