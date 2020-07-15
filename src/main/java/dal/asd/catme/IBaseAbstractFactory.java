package dal.asd.catme;

import dal.asd.catme.survey.ISurveyAbstractFactory;
import dal.asd.catme.survey.ISurveyModelAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import dal.asd.catme.password.IPasswordAbstractFactory;
import dal.asd.catme.questionmanager.IQuestionManagerAbstractFactory;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import dal.asd.catme.studentlistimport.ICSVParserAbstractFactory;
import dal.asd.catme.surveyresponse.ISurveyResponseAbstractFactory;
import dal.asd.catme.surveyresponse.ISurveyResponseModelAbstractFactory;

public interface IBaseAbstractFactory
{
public ISurveyAbstractFactory makeSurveyAbstractFactory();
	public ISurveyModelAbstractFactory makeSurveyModelAbstractFactory();
    IAccessControlAbstractFactory makeAccessControlAbstractFactory();

    IAccessControlModelAbstractFactory makeAccessControlModelAbstractFactory();

    ICourseAbstractFactory makeCourseAbstractFactory();

    ICourseModelAbstractFactory makeCourseModelAbstractFactory();

    IPasswordAbstractFactory makePasswordAbstractFactory();

    IQuestionManagerAbstractFactory makeQuestionManagerAbstractFactory();

    IQuestionManagerModelAbstractFactory makeQuestionManagerModelAbstractFactory();

    ICSVParserAbstractFactory makeCSVParserAbstractFactory();

    ISurveyResponseAbstractFactory makeSurveyResponseAbstractFactory();

    ISurveyResponseModelAbstractFactory makeSurveyResponseModelAbstractFactory();
}
