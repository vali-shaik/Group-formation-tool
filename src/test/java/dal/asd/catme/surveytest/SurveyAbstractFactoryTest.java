package dal.asd.catme.surveytest;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.survey.ISurveyDao;
import dal.asd.catme.survey.ISurveyService;

public class SurveyAbstractFactoryTest 
{
	@Test
	public void makeSurveyServiceTest()
	{
		ISurveyService surveyService = BaseAbstractFactoryImpl.instance().makeSurveyAbstractFactory().makeSurveyService();
        assertNotNull(surveyService);

	}
	@Test
	public void makeSurveyDaoTest()
	{
		ISurveyDao surveyDao = BaseAbstractFactoryImpl.instance().makeSurveyAbstractFactory().makeSurveyDao();
        assertNotNull(surveyDao);
	}
}
