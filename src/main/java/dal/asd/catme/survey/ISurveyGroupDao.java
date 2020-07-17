package dal.asd.catme.survey;

import java.util.List;

import dal.asd.catme.accesscontrol.User;

public interface ISurveyGroupDao 
{

	public int getGroupSize(int surveyId);

	public List<User> getSurveyParticipants(int surveyId);
}
