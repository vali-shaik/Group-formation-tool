package dal.asd.catme;

import dal.asd.catme.accesscontrol.IAccessControlAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.algorithm.IAlgorithmAbstractFactory;
import dal.asd.catme.algorithm.IAlgorithmModelAbstractFactory;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.password.IPasswordAbstractFactory;
import dal.asd.catme.questionmanager.IQuestionManagerAbstractFactory;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import dal.asd.catme.studentlistimport.ICSVParserAbstractFactory;
import dal.asd.catme.survey.ISurveyAbstractFactory;
import dal.asd.catme.survey.ISurveyModelAbstractFactory;
import dal.asd.catme.surveyresponse.ISurveyResponseAbstractFactory;
import dal.asd.catme.surveyresponse.ISurveyResponseModelAbstractFactory;

public interface IBaseAbstractFactory {
	public IAccessControlAbstractFactory makeAccessControlAbstractFactory();

	public IAccessControlModelAbstractFactory makeAccessControlModelAbstractFactory();

	public ICourseAbstractFactory makeCourseAbstractFactory();

	public ICourseModelAbstractFactory makeCourseModelAbstractFactory();

	public IPasswordAbstractFactory makePasswordAbstractFactory();

	public IQuestionManagerAbstractFactory makeQuestionManagerAbstractFactory();

	public IQuestionManagerModelAbstractFactory makeQuestionManagerModelAbstractFactory();

	public ICSVParserAbstractFactory makeIcsvParserAbstractFactory();

	public ISurveyResponseAbstractFactory makeSurveyResponseAbstractFactory();

	public ISurveyResponseModelAbstractFactory makeSurveyResponseModelAbstractFactory();

	public IAlgorithmAbstractFactory makeAlgorithmAbstractFactory();

	public IAlgorithmModelAbstractFactory makeAlgorithmModelAbstractFactory();

public interface IBaseAbstractFactory
{
    ISurveyAbstractFactory makeSurveyAbstractFactory();

    ISurveyModelAbstractFactory makeSurveyModelAbstractFactory();

    IAccessControlAbstractFactory makeAccessControlAbstractFactory();

    IAccessControlModelAbstractFactory makeAccessControlModelAbstractFactory();

    ICourseAbstractFactory makeCourseAbstractFactory();

    ICourseModelAbstractFactory makeCourseModelAbstractFactory();

    IDatabaseAbstractFactory makeDatabaseAbstractFactory();

    IPasswordAbstractFactory makePasswordAbstractFactory();

    IQuestionManagerAbstractFactory makeQuestionManagerAbstractFactory();

    IQuestionManagerModelAbstractFactory makeQuestionManagerModelAbstractFactory();

    ICSVParserAbstractFactory makeCSVParserAbstractFactory();

    ISurveyResponseAbstractFactory makeSurveyResponseAbstractFactory();

    ISurveyResponseModelAbstractFactory makeSurveyResponseModelAbstractFactory();
}
