package dal.asd.catme.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.service.IListQuestionsService;
import dal.asd.catme.service.IQuestionManagerService;
import dal.asd.catme.util.CatmeUtil;

@Controller
@RequestMapping("questions")
public class QuestionManagerController
{
    IListQuestionsService listQuestionsService;
    //IQuestionManagerService questionManagerServiceImpl = new QuestionManagerServiceImpl();
    IQuestionManagerService questionManagerServiceImpl;
    
    //Creating Logger
    private static final Logger log = LoggerFactory.getLogger(QuestionManagerController.class);
    
    @Value("${questionTypes}")
    private String[] questionTypes;

    @RequestMapping("")
    public ModelAndView loadPage()
    {
        User currentUser=new User();

        //Fetching current User's user name
        currentUser.setBannerId(SecurityContextHolder.getContext().getAuthentication().getName());

        ModelAndView questionsPage = new ModelAndView();

        questionsPage.setViewName(CatmeUtil.QUESTION_MANAGER_HOME);

        listQuestionsService = SystemConfig.instance().getListQuestionsService();

        try
        {
            listQuestionsService.getQuestions(currentUser.getBannerId());
            List<Question> questionList = listQuestionsService.sortByTitle();
            questionsPage.addObject("questions",questionList);

        }
        catch (QuestionDatabaseException e)
        {
            String message = e.getMessage();
            questionsPage.addObject("message",message);
        }

        return questionsPage;
    }

    @GetMapping("sort")
    public ModelAndView sortTable(@RequestParam(name = "with") String sortBy)
    {

        ModelAndView questionsPage = new ModelAndView();

        questionsPage.setViewName(CatmeUtil.QUESTION_MANAGER_HOME);


        List<Question> questionList;
        if(sortBy.equalsIgnoreCase("Date"))
        {
            questionList = listQuestionsService.sortByDate();
        }
        else
        {
            questionList = listQuestionsService.sortByTitle();
        }
        questionsPage.addObject("questions",questionList);
        questionsPage.addObject("sortBy",sortBy);

        return questionsPage;
    }
	
	
	
	@RequestMapping("/createQuestion")
	public String createQuestion(Model model) 
	{
		log.info("****QuestionManagerController - createQuestion Invoked*****");
		model.addAttribute("question", new Question());
		model.addAttribute("questionTitle", "");
		return "createQuestion";
	}
	
	@RequestMapping("/questionType")
	public String createQuestionType(@ModelAttribute Question question) 
	{
		
		log.info("****QuestionManagerController - createQuestionType Invoked*****");
		String userId=SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("***user***"+userId);
		questionManagerServiceImpl=SystemConfig.instance().getQuestionManagerService();
		return questionManagerServiceImpl.findQuestionType(question,userId);
		
		
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
    	
    		log.info("****QuestionManagerController - addQuestion Invoked*****");
    		String userId=SecurityContextHolder.getContext().getAuthentication().getName();
    		log.info("***here*** "+question.toString());
    		questionManagerServiceImpl=SystemConfig.instance().getQuestionManagerService();
    		int questionId = questionManagerServiceImpl.createQuestion(question, userId);
    		int result =questionManagerServiceImpl.createOptions(questionId,question.getOptionWithOrder());
    		if(result>0) {
    			return CatmeUtil.QUESTION_CREATION_SUCCESS;
    	}
    			else{
    				return CatmeUtil.QUESTION_FAILURE_PAGE;
    			}

    }
	
	@ModelAttribute("questionTypes")
	public String[] getQuestionTypes(){
		return questionTypes;	
	}
	
	

}
