package dal.asd.catme.surveyresponse;

public class SurveyResponseAbstractFactoryImpl implements ISurveyResponseAbstractFactory
{
    static ISurveyResponseAbstractFactory surveyResponseAbstractFactory=null;
    ISurveyResponseDao surveyResponseDao;
    ISurveyResponseService surveyResponseService;

    public SurveyResponseAbstractFactoryImpl()
    {
        surveyResponseDao = new SurveyResponseDaoImpl();
        surveyResponseService = new SurveyResponseServiceImpl(surveyResponseDao);
    }

    public static ISurveyResponseAbstractFactory instance()
    {
        if(surveyResponseAbstractFactory==null)
        {
            surveyResponseAbstractFactory = new SurveyResponseAbstractFactoryImpl();
        }
        return surveyResponseAbstractFactory;
    }
    @Override
    public ISurveyResponseDao makeSurveyResponseDao()
    {
        return surveyResponseDao;
    }

    @Override
    public ISurveyResponseService makeSurveyResponseService()
    {
        return surveyResponseService;
    }
}
