package dal.asd.catme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.service.ICatmeService;

@Controller
@RequestMapping("/")
public class CatmeController {
	
	
	@RequestMapping("")
	public String homePage()
	{
		return "home";
	}
	@RequestMapping("login")
	public String loginPage()
	{
		return "login";
	}
	
	@RequestMapping("admin")
	public String adminPage()
	{
		return "adminPage";
	}
	
	@RequestMapping("addCourse")
	public String addCourse()
	{
		return "addCourse";
	}


}
