package dal.asd.catme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.service.ICatmeService;
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
	@RequestMapping("login")
	public String loginPage()
	{
		log.info("CatmeController login page!!");
		return CatmeUtil.LOGIN_PAGE;
	}


}
