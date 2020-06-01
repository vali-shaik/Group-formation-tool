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
import dal.asd.catme.dao.IRoleDao;
import dal.asd.catme.dao.IUserDao;
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
	IUserDao userDao;
	@Autowired
	IRoleDao roleDao;
	
	
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
		//Enrollment o = new Enrollment();
		//o.bannerId = "B00835717";
		//o.courseId = 5406;
		//System.out.print(roleDao.assignTa(o));
		return CatmeUtil.ADMIN_PAGE;
	}
	
	@RequestMapping("signup")
	public String signupPage(@ModelAttribute User user, Model model) {
		if(1==userDao.addUser(user)){
			return CatmeUtil.LOGIN_PAGE;
		}
		return CatmeUtil.SIGNUP_PAGE;			
	}	
	
	@GetMapping("taEnrollment")
	public String enrollTa(Model model) {
		model.addAttribute("user", new Enrollment());
		return CatmeUtil.TA_ENROLLMENT_PAGE;
	}
	
	@PostMapping("taEnrollment")
	public String enrollTa(@ModelAttribute Enrollment user) {
		//user.courseId = courseId;
		System.out.print(user.getBannerId());
		return "enrollTa";
	}

	
}
// @RequestParam(name="courseId", required=true) String courseId, @ModelAttribute Enrollment user
