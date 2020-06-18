package dal.asd.catme.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.service.QuestionManagerServiceImpl;
import dal.asd.catme.util.CatmeUtil;

@Controller
@RequestMapping("question/")
public class QuestionManagerController {
	
private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

@Value("${questionTypes}")
private String[] questionTypes;

	QuestionManagerServiceImpl questionManagerServiceImpl = new QuestionManagerServiceImpl();
	
	public static int value =0; 
	
	@RequestMapping("createQuestion")
	public String createQuestion(Model model) 
	{
		logger.info("****QuestionManagerController - createQuestion Invoked*****");
		model.addAttribute("question", new Question());
		return "createQuestion";
	}
	
	@RequestMapping("questionType")
	public String createQuestionType(@ModelAttribute Question question,Model model) 
	{
		System.out.println("##before : "+question.toString());
		logger.info("****QuestionManagerController - createQuestionType Invoked*****");
		List<Option> options = new ArrayList<Option>();
		for(int i=0;i<5;i++) {
			Option option= new Option(++value);
			options.add(option);
		}
		System.out.println("##Options  : "+options.toString());
		question.setOptionWithOrder(options);
		System.out.println("##before : "+question.toString());
		model.addAttribute("quesiton",question);
		return "optionEditor";
		
	}
	
	@RequestMapping("addQuestion")
	public String addQuestion(@ModelAttribute Question question) 
	{
		System.out.println("AFTER MCQ Question : " +question.toString());
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
