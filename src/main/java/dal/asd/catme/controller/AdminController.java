package dal.asd.catme.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.util.CatmeUtil;

@Controller
@RequestMapping("admin/")
public class AdminController {
	
	//Creating Logger
	private static final Logger log = LoggerFactory.getLogger(CatmeController.class);
	
	//Displaying Admin home page
	@RequestMapping("home")
	public String adminHomePage()
	{
		log.info("Displaying Admin page");
		return CatmeUtil.ADMIN_PAGE;
	}

}
