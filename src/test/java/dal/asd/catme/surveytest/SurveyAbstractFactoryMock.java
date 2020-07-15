package dal.asd.catme.surveytest;

import dal.asd.catme.survey.ISurveyAbstractFactory;
import dal.asd.catme.survey.ISurveyDao;
import dal.asd.catme.survey.ISurveyService;
import dal.asd.catme.survey.SurveyServiceImpl;

public class SurveyAbstractFactoryMock implements ISurveyAbstractFactory
{
	static ISurveyAbstractFactory surveyAbstractFactory=null;
	ISurveyService surveyService;
	ISurveyDao surveyDao;
	
	public SurveyAbstractFactoryMock()
	{
		surveyDao=new SurveyDaoMock();
		surveyService=new SurveyServiceImpl(surveyDao);
	}

	@Override
	public ISurveyService makeSurveyService() 
	{
		return surveyService;
	}

	@Override
	public ISurveyDao makeSurveyDao() 
	{
		return surveyDao;
	}

}
