package dal.asd.catme.surveytest;

import dal.asd.catme.survey.*;

public class SurveyAbstractFactoryMock implements ISurveyAbstractFactory
{
    static ISurveyAbstractFactory surveyAbstractFactory = null;
    ISurveyService surveyService;
    ISurveyDao surveyDao;

    public SurveyAbstractFactoryMock()
    {
        surveyDao = new SurveyDaoMock();
        surveyService = new SurveyServiceImpl(surveyDao);
    }

    @Override
    public ISurveyService makeSurveyService()
    {
        return surveyService;
    }

    @Override
    public ISurveyDao makeSurveyDao()
    {
        return surveyDao;
    }

    @Override
    public ISurveyGroupService makeSurveyGroupService()
    {
        return null;
    }

    @Override
    public ISurveyGroupDao makeSurveyGroupDao()
    {
        return null;
    }
}
