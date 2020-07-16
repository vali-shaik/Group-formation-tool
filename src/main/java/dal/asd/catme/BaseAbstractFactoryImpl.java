package dal.asd.catme;

import dal.asd.catme.accesscontrol.AccessControlAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.AccessControlModelAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.IAccessControlAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.algorithm.AlgorithmAbstractFactoryImpl;
import dal.asd.catme.algorithm.AlgorithmModelAbstractFactoryImpl;
import dal.asd.catme.algorithm.IAlgorithmAbstractFactory;
import dal.asd.catme.algorithm.IAlgorithmModelAbstractFactory;
import dal.asd.catme.courses.CourseAbstractFactoryImpl;
import dal.asd.catme.courses.CourseModelAbstractFactoryImpl;
import dal.asd.catme.courses.ICourseAbstractFactory;
import dal.asd.catme.courses.ICourseModelAbstractFactory;
import dal.asd.catme.password.IPasswordAbstractFactory;
import dal.asd.catme.password.PasswordAbstractFactoryImpl;
import dal.asd.catme.questionmanager.IQuestionManagerAbstractFactory;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import dal.asd.catme.questionmanager.QuestionManagerAbstractFactoryImpl;
import dal.asd.catme.questionmanager.QuestionManagerModelAbstractFactoryImpl;
import dal.asd.catme.studentlistimport.CSVParserAbstractFactoryImpl;
import dal.asd.catme.studentlistimport.ICSVParserAbstractFactory;
import dal.asd.catme.surveyresponse.ISurveyResponseAbstractFactory;
import dal.asd.catme.surveyresponse.ISurveyResponseModelAbstractFactory;
import dal.asd.catme.surveyresponse.SurveyResponseAbstractFactoryImpl;
import dal.asd.catme.surveyresponse.SurveyResponseModelAbstractFactoryImpl;

public class BaseAbstractFactoryImpl implements IBaseAbstractFactory
{
    private static IBaseAbstractFactory baseAbstractFactory = null;

    public static IBaseAbstractFactory instance()
    {
        if(baseAbstractFactory == null)
        {
            baseAbstractFactory = new BaseAbstractFactoryImpl();
        }
        return baseAbstractFactory;
    }

    public IAccessControlAbstractFactory makeAccessControlAbstractFactory()
    {
        return AccessControlAbstractFactoryImpl.instance();
    }

    public IAccessControlModelAbstractFactory makeAccessControlModelAbstractFactory()
    {
        return AccessControlModelAbstractFactoryImpl.instance();
    }

    public IAlgorithmAbstractFactory makeAlgorithmAbstractFactory()
    {
        return AlgorithmAbstractFactoryImpl.instance();
    }

    public IAlgorithmModelAbstractFactory makeAlgorithmModelAbstractFactory()
    {
        return AlgorithmModelAbstractFactoryImpl.instance();
    }

    
    public ICourseAbstractFactory makeCourseAbstractFactory()
    {
        return CourseAbstractFactoryImpl.instance();
    }

    public ICourseModelAbstractFactory makeCourseModelAbstractFactory()
    {
        return CourseModelAbstractFactoryImpl.instance();
    }

    public IPasswordAbstractFactory makePasswordAbstractFactory()
    {
        return PasswordAbstractFactoryImpl.instance();
    }

    public IQuestionManagerAbstractFactory makeQuestionManagerAbstractFactory()
    {
        return QuestionManagerAbstractFactoryImpl.instance();
    }

    public IQuestionManagerModelAbstractFactory makeQuestionManagerModelAbstractFactory()
    {
        return QuestionManagerModelAbstractFactoryImpl.instance();
    }

    public ICSVParserAbstractFactory makeIcsvParserAbstractFactory()
    {
        return CSVParserAbstractFactoryImpl.instance();
    }

    public ISurveyResponseAbstractFactory makeSurveyResponseAbstractFactory()
    {
        return SurveyResponseAbstractFactoryImpl.instance();
    }

    public ISurveyResponseModelAbstractFactory makeSurveyResponseModelAbstractFactory()
    {
        return SurveyResponseModelAbstractFactoryImpl.instance();
    }
}
