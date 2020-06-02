package dal.asd.catme.controller;
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

	
}
