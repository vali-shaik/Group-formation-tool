package dal.asd.catme.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.config.CatmeSecurityConfig;
import dal.asd.catme.service.ICatmeService;
@Controller
@RequestMapping("/")
public class CatmeController {

	@Autowired
	ICatmeService catmeService;
	@Autowired
	CatmeSecurityConfig catmeSecurityConfig;
	private static final Logger log = LoggerFactory.getLogger(CatmeController.class);
	@RequestMapping("home")
	public String homePage()
	{
		return catmeSecurityConfig.fetchRolesHomePage();
	}	
}
