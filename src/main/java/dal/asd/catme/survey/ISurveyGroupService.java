package dal.asd.catme.survey;

import java.util.List;

import dal.asd.catme.accesscontrol.User;

public interface ISurveyGroupService 
{
	public List<User>displaySurveyGroups(int surveyId);
}
