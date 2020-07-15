package dal.asd.catme;

import dal.asd.catme.survey.ISurveyAbstractFactory;
import dal.asd.catme.survey.ISurveyModelAbstractFactory;

public interface IBaseAbstractFactory {
	
	public ISurveyAbstractFactory makeSurveyAbstractFactory();
	public ISurveyModelAbstractFactory makeSurveyModelAbstractFactory();

}
