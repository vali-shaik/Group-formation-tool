package dal.asd.catme.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.config.CatmeSecurityConfig;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.CatmeUtil;

@Controller
@RequestMapping("/")
public class CatmeController {

	@Autowired
	CatmeSecurityConfig catmeServiceConfig;

	//Creating Logger
	private static final Logger log = LoggerFactory.getLogger(CatmeController.class);

	//Decides landing page of application based on user authorization
	@RequestMapping("access")
	public String findLandingPage() throws CatmeException
	{
		log.info("Finding the landing page of application based on access level"); 
		
		//Defining home page for user
		String page="";
		
		//Fetching the all roles of current user
		List<String> roles= catmeServiceConfig.fetchRolesHomePage();
		
		//Checking for current user role and redirecting to respective landing page
		if(roles!=null)
		{
			if(roles.contains("ROLE_ADMIN"))
			{
				//Admin role
				log.info("User idetified as Admin"); 
				return "redirect:/"+CatmeUtil.ADMIN_HOME;
			}
			if(roles.contains("ROLE_INSTRUCTOR"))
			{
				//Instructor role
				log.info("User idetified as Instructor");
				return "redirect:/"+CatmeUtil.INSTRUCTOR_HOME;
			}
			if(roles.contains("ROLE_TA"))
			{
				//TA role
				log.info("User idetified as TA");
				return "redirect:/"+CatmeUtil.TA_HOME;
			}
			if(roles.contains("ROLE_STUDENT"))
			{
				//Student role
				log.info("User idetified as Student");
				return "redirect:/"+CatmeUtil.STUDENT_HOME;
			}
			if(roles.contains("ROLE_GUEST"))
			{
				//Guest role
				log.info("User idetified as Guest");
				return "redirect:/"+CatmeUtil.GUEST_HOME;
			}
		}
		//If User does not have any of the defined roles, leads to error page
		if(page.trim().length()==0)
		{
			page="error";
			throw new CatmeException("User Role is not found in Database");
		}
		return page;
	}

	//Displaying Login page for authentication
	@RequestMapping("login")
	public String loginPage()
	{
		log.info("Redirected to Login page");
		return CatmeUtil.LOGIN_PAGE;
	}
}
