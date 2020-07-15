package dal.asd.catme.survey;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.courses.Course;
import dal.asd.catme.exception.QuestionDatabaseException;
import dal.asd.catme.questionmanager.IListQuestionsService;
import dal.asd.catme.questionmanager.Question;
import dal.asd.catme.util.CatmeUtil;

@Controller
public class SurveyController {

	IListQuestionsService listQuestionsService;
	ISurveyService surveyService= BaseAbstractFactoryImpl.instance().makeSurveyAbstractFactory().makeSurveyService();

	@RequestMapping("/createSurvey")
	public ModelAndView loadSurveyPage(@RequestParam(name = "courseId") String courseId)
	{
		ModelAndView modelAndView=new ModelAndView();
		User currentUser = new User();
		Course course=new Course();
		course.setCourseId(courseId);
		Survey survey=null;
		List<Question> questionBank=new ArrayList<>();
		currentUser.setBannerId(SecurityContextHolder.getContext().getAuthentication().getName());
		listQuestionsService = SystemConfig.instance().getListQuestionsService();
		try
		{
			questionBank=listQuestionsService.getQuestions(currentUser.getBannerId());
			survey=surveyService.loadSurvey(course);
			survey.setIsPublished(surveyService.isSurveyPublished(course));
		} catch (QuestionDatabaseException e) 
		{
			e.printStackTrace();
		}

		modelAndView.setViewName("createSurvey");
		modelAndView.addObject("questionBank", questionBank);
		modelAndView.addObject("survey",survey);
		modelAndView.addObject("course",course);
		modelAndView.addObject("isPublished", survey.getIsPublished());
		System.out.println("Question list"+survey.getSurveyQuestions().toString());		
		System.out.println("Question bank : "+questionBank);
		System.out.println("Survey : "+survey.toString());
		return modelAndView;
	}

	@RequestMapping("/survey/add")
	public String addQuestionToSurvey(@ModelAttribute Question question,@ModelAttribute Survey survey,@ModelAttribute Course course)
	{
		try 
		{
			surveyService.addToSurvey(survey, question);
		} 
		catch (QuestionDatabaseException e) 
		{
			e.printStackTrace();
		}
		return CatmeUtil.ADD_QUESTION_SURVEY_PAGE+course.getCourseId();
	}

	@RequestMapping(value = "/survey/saveAndPublish", method = RequestMethod.POST, params = "action=Save")
	public ModelAndView saveSurvey(@ModelAttribute Survey survey,@ModelAttribute Course course)
	{
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName(CatmeUtil.SAVE_SURVEY_PAGE);
		int returnedRows=0;
		try 
		{ 
			returnedRows=surveyService.saveSurvey(survey); 
			modelAndView.addObject("returnedRows", returnedRows);
		} 
		catch (QuestionDatabaseException e)
		{ 
			e.printStackTrace(); 
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/survey/saveAndPublish", method = RequestMethod.POST, params = "action=Publish")
	public ModelAndView publishSurvey(@ModelAttribute Survey survey,@ModelAttribute Course course)
	{
		ModelAndView modelAndView= new ModelAndView();
		modelAndView.setViewName(CatmeUtil.PUBLISH_SURVEY_PAGE);
		int returnedRows=0;
		try {
			returnedRows=surveyService.publishSurvey(survey);
			modelAndView.addObject("returnedRows", returnedRows);
		} catch (QuestionDatabaseException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	@RequestMapping("/survey/{surveyId}/deleteQuestion/{questionid}/course/{courseId}")
	public String deleteQuestion(@PathVariable("questionid") int questionId,@PathVariable("surveyId") int surveyId,@PathVariable("courseId") String courseId)
	{
		Survey survey=new Survey();
		survey.setSurveyId(surveyId);
		Question question=new Question();
		question.setQuestionId(questionId);
		try 
		{
			surveyService.deleteSurveyQuestion(survey, question);
		} 
		catch (QuestionDatabaseException e)
		{
			e.printStackTrace();
		}
		return CatmeUtil.ADD_QUESTION_SURVEY_PAGE+courseId;
	}


}
