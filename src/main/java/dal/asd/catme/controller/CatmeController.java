package dal.asd.catme.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dal.asd.catme.config.CatmeSecurityConfig;
import dal.asd.catme.service.ICatmeService;
import dal.asd.catme.util.CatmeUtil;
@Controller
@RequestMapping("/")
public class CatmeController {

	@Autowired
	ICatmeService catmeService;
	@Autowired
	CatmeSecurityConfig catmeSecurityConfig;
	private static final Logger log = LoggerFactory.getLogger(CatmeController.class);
	@RequestMapping("home")
	public ModelAndView homePage()
	{
		ModelAndView modelAndView=new ModelAndView();
		if(catmeSecurityConfig.fetchRolesHomePage().equals(CatmeUtil.HOME_PAGE))
		{
			modelAndView.addObject("listOfCourses",catmeService.getAllCourses());
			modelAndView.setViewName(CatmeUtil.HOME_PAGE);
		}
		else
		{
			modelAndView.setViewName(CatmeUtil.ADMIN_PAGE);
		}
		return modelAndView;
	}
}
