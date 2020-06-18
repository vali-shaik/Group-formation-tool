package dal.asd.catme.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;
import dal.asd.catme.service.QuestionManagerServiceImpl;

@Controller
@RequestMapping("/question")
public class QuestionManagerController {
	
private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

@Value("${questionTypes}")
private String[] questionTypes;

	QuestionManagerServiceImpl questionManagerServiceImpl = new QuestionManagerServiceImpl();
	
	//public static int value =0; 
	
	@RequestMapping("/createQuestion")
	public String createQuestion(Model model) 
	{
		logger.info("****QuestionManagerController - createQuestion Invoked*****");
		model.addAttribute("question", new Question());
		return "createQuestion";
	}
	
	@RequestMapping("/questionType")
	public String createQuestionType(@ModelAttribute Question question) 
	{
		System.out.println("##before : "+question.toString());
		logger.info("****QuestionManagerController - createQuestionType Invoked*****");
		List<Option> options = new ArrayList<Option>();
		for(int i=1;i<6;i++) {
			Option option= new Option(i);
			options.add(option);
		}
		System.out.println("##Options  : "+options.toString());
		question.setOptionWithOrder(options);
		System.out.println("##before : "+question.toString());
		return "optionEditor";
		
	}
	
	
	@RequestMapping(value="/addQuestion", method=RequestMethod.POST, params="action=addOption")
	public String addOption(@ModelAttribute Question question) {
		
		List<Option> options=question.getOptionWithOrder();
		options.add(new Option(options.size()+1));
		question.setOptionWithOrder(options);
		return "optionEditor";
	}

	@RequestMapping(value="/addQuestion", method=RequestMethod.POST, params="action=create")
	public String addQuestion(@ModelAttribute Question question) {
		
		logger.info("****QuestionManagerController - addQuestion Invoked*****");
		logger.info("***here*** "+question.getOptionWithOrder());
		return "questionCreationSuccess";
	}
	
	
	@ModelAttribute("questionTypes")
	public String[] getQuestionTypes(){
		System.out.println("questionTypes "+questionTypes);
		return questionTypes;	
	}
	
	

}
