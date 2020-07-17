package dal.asd.catme.survey;

import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.util.CatmeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.BaseAbstractFactoryImpl;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SurveyGroupController 
{
	ISurveyGroupService surveyGroupService=BaseAbstractFactoryImpl.instance().makeSurveyAbstractFactory().makeSurveyGroupService();
	@RequestMapping("/surveyGroups/{surveyId}")
	public ModelAndView surveyGroups(@PathVariable("surveyId") int surveyId)
	{
		List<List<User>> groups=surveyGroupService.displaySurveyGroups(surveyId);
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName(CatmeUtil.SURVEY_GROUP);
		if(groups==null)
		{
			modelAndView.addObject("message","Error Creating groups");
			return modelAndView;
		}
		modelAndView.addObject("groups",groups);
		return modelAndView;
	}
}
