package dal.asd.catme;

import dal.asd.catme.accesscontrol.IAccessControlAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.algorithm.IAlgorithmAbstractFactory;
import dal.asd.catme.algorithm.IAlgorithmModelAbstractFactory;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import dal.asd.catme.password.IPasswordAbstractFactory;
import dal.asd.catme.questionmanager.IQuestionManagerAbstractFactory;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import dal.asd.catme.studentlistimport.ICSVParserAbstractFactory;
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

}
