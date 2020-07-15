package dal.asd.catme;

import dal.asd.catme.survey.ISurveyAbstractFactory;
import dal.asd.catme.surveytest.SurveyAbstractFactoryMock;

public class BaseAbstractFactoryMock implements IBaseAbstractFactory 
{
	private static IBaseAbstractFactory baseAbstractFactory = null;
	private ISurveyAbstractFactory surveyAbstractFactory;

	public static IBaseAbstractFactory instance()
	{
		if (baseAbstractFactory == null)
		{
			baseAbstractFactory = new BaseAbstractFactoryMock();
		}
		return baseAbstractFactory;
	}


	@Override
	public ISurveyAbstractFactory makeSurveyAbstractFactory() 
	{ 
		if(surveyAbstractFactory == null)
		{
			surveyAbstractFactory = new SurveyAbstractFactoryMock();
		}
	return surveyAbstractFactory;
	}

}


