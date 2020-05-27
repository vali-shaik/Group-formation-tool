package dal.asd.catme.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
