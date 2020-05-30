package dal.asd.catme.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dal.asd.catme.beans.Enrollment;
import dal.asd.catme.beans.User;


import dal.asd.catme.dao.IAddUserDao;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.service.ICatmeService;
import dal.asd.catme.util.CatmeUtil;
@Controller
@RequestMapping("/")
public class CatmeController {
	
	@Autowired
	ICatmeService catmeService;
	@Autowired
	DatabaseAccess database;
	@Autowired
	IAddUserDao addUserDao;
	
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
	
	@RequestMapping("signup")
	public String signupPage(@ModelAttribute User user, Model model) {
		if(1==addUserDao.addUser(user)){
			return CatmeUtil.LOGIN_PAGE;
		}
		return CatmeUtil.SIGNUP_PAGE;			
	}
		
	
	@GetMapping("taEnrollment")
	public String enrollTa(Model model) {
		//user.courseId = courseId;
		//System.out.print(user.bannerId+"\t"+user.courseId);
		model.addAttribute("user", new User());
		return CatmeUtil.TA_ENROLLMENT_PAGE;
	}
	
	@PostMapping("enrollTa")
	public String enrollTa(@ModelAttribute User user) {
		//user.courseId = courseId;
		System.out.print(user.getBannerId());
		return CatmeUtil.TA_ENROLLMENT_PAGE;
	}
}
