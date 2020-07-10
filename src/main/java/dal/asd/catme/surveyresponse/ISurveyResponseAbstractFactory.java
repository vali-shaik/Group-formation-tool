package dal.asd.catme.surveyresponse;

public interface ISurveyResponseAbstractFactory
{
    ISurveyResponseDao getSurveyResponseDao();
    ISurveyResponseService getSurveyResponseService();
}
