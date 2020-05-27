package dal.asd.catme.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.util.CatmeUtil;
@Controller
@RequestMapping("/")
public class CatmeController {

	private static final Logger log = LoggerFactory.getLogger(CatmeController.class);

	@RequestMapping("")
	public String homePage()
	{
		log.info("Controller home page!!");
		return CatmeUtil.HOME_PAGE;
	}
<<<<<<< HEAD
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


=======
>>>>>>> cecb4c87ed55d55605addaf1952fe9e2b576de7a
}
