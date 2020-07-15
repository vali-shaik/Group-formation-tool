package dal.asd.catme;

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
