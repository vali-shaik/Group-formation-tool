package dal.asd.catme.survey;

import java.util.List;

import dal.asd.catme.accesscontrol.User;

public interface ISurveyGroupDao 
{
	public List<User>displaySurveyGroups(int surveyId);

	public int getGroupSize(int surveyId);

	public List<User> getStudents(int surveyId);
}
