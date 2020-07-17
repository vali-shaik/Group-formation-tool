package dal.asd.catme.survey;

public interface ISurveyAbstractFactory
{
    ISurveyService makeSurveyService();
    ISurveyDao makeSurveyDao();
    public ISurveyGroupService makeSurveyGroupService();
    public ISurveyGroupDao makeSurveyGroupDao();
}
