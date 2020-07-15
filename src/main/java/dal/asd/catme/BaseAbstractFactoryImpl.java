package dal.asd.catme;

import dal.asd.catme.survey.ISurveyAbstractFactory;
import dal.asd.catme.survey.ISurveyModelAbstractFactory;
import dal.asd.catme.survey.SurveyAbstractFactoryImpl;
import dal.asd.catme.survey.SurveyModelAbstractFactoryImpl;

public class BaseAbstractFactoryImpl implements IBaseAbstractFactory {
	
	private static IBaseAbstractFactory baseAbstractFactory = null;
	private ISurveyAbstractFactory surveyAbstractFactory;
	private ISurveyModelAbstractFactory surveyModelAbstractFactory;
	
	  public static IBaseAbstractFactory instance()
	    {
	        if(baseAbstractFactory == null)
	        {
	            baseAbstractFactory = new BaseAbstractFactoryImpl();
	        }
	        return baseAbstractFactory;
	    }


	@Override
	public ISurveyAbstractFactory makeSurveyAbstractFactory() {
		 if(surveyAbstractFactory == null)
	        {
			 surveyAbstractFactory = new SurveyAbstractFactoryImpl();
	        }
	        return surveyAbstractFactory;
	}


	@Override
	public ISurveyModelAbstractFactory makeSurveyModelAbstractFactory() {
		 if(surveyModelAbstractFactory == null)
	        {
			 surveyModelAbstractFactory = new SurveyModelAbstractFactoryImpl();
	        }
	        return surveyModelAbstractFactory;
	}

}
