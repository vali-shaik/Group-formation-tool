package dal.asd.catme;

import dal.asd.catme.survey.ISurveyAbstractFactory;
import dal.asd.catme.survey.SurveyAbstractFactoryImpl;

public class BaseAbstractFactoryImpl implements IBaseAbstractFactory {
	
	private static IBaseAbstractFactory baseAbstractFactory = null;
	private ISurveyAbstractFactory surveyAbstractFactory;
	
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

}
