package dal.asd.catme.survey;

public class SurveyAbstractFactoryImpl implements ISurveyAbstractFactory 
{
	static ISurveyAbstractFactory surveyAbstractFactory=null;
	ISurveyService surveyService;
	ISurveyDao surveyDao;
	public SurveyAbstractFactoryImpl()
	{
		surveyDao=new SurveyDaoImpl();
		surveyService=new SurveyServiceImpl(surveyDao);
	}

	public ISurveyService makeSurveyService() {
		return surveyService;
	}
	public ISurveyDao makeSurveyDao() {
		return surveyDao;
	}
}
