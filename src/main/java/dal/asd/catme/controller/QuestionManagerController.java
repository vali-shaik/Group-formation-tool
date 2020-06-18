package dal.asd.catme.controller;

import dal.asd.catme.beans.Question;
import dal.asd.catme.beans.QuestionTitle;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.service.IListQuestionsService;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("questions")
public class QuestionManagerController
{
    IListQuestionsService listQuestionsService;

    //Creating Logger
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

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
