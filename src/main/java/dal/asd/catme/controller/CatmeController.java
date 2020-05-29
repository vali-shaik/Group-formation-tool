package dal.asd.catme.controller;
import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.service.MailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.util.CatmeUtil;

import javax.mail.MessagingException;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class CatmeController {

	private static final Logger log = LoggerFactory.getLogger(CatmeController.class);

	@Autowired
	MailSenderService mailSenderService;
	@RequestMapping("")
	public String homePage()
	{
		log.info("Controller home page!!");

		return CatmeUtil.HOME_PAGE;
	}
}
