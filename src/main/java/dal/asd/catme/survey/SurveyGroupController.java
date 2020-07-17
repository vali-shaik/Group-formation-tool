package dal.asd.catme.survey;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dal.asd.catme.BaseAbstractFactoryImpl;

@Controller
public class SurveyGroupController 
{
	ISurveyGroupService surveyGroupService=BaseAbstractFactoryImpl.instance().makeSurveyAbstractFactory().makeSurveyGroupService();
	@RequestMapping("/surveyGroups/{surveyId}")
	public String surveyGroups()
	{
		return null;
	}
}
