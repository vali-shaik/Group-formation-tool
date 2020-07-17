package dal.asd.catme.survey;

import java.util.List;

import dal.asd.catme.accesscontrol.User;

public class SurveyGroupServiceImpl implements ISurveyGroupService 
{

	ISurveyGroupDao surveyGroupDao;
	public SurveyGroupServiceImpl(ISurveyGroupDao surveyGroupDao)
	{
		this.surveyGroupDao=surveyGroupDao;
	}
	
	@Override
	public List<User> displaySurveyGroups(int surveyId) {
		// TODO Auto-generated method stub
		return null;
	}

}
