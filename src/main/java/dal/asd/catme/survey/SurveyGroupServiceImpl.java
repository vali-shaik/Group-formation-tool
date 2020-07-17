package dal.asd.catme.survey;

import dal.asd.catme.accesscontrol.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SurveyGroupServiceImpl implements ISurveyGroupService
{

    ISurveyGroupDao surveyGroupDao;

    public SurveyGroupServiceImpl(ISurveyGroupDao surveyGroupDao)
    {
        this.surveyGroupDao = surveyGroupDao;
    }

    @Override
    public List<List<User>> displaySurveyGroups(int surveyId)
    {
    	if(surveyId<=0)
		{
			return null;
		}

		int groupSize = surveyGroupDao.getGroupSize(surveyId);
    	List<User> students = surveyGroupDao.getStudents(surveyId);

    	return formGroups(students,groupSize);
    }

    private List<List<User>> formGroups(List<User> students, int groupSize)
	{
		Collections.shuffle(students);
		List<List<User>> groups = new ArrayList<>();
		int indexOfStudent = 0;
		int numberOfGroups = students.size()/groupSize;
		for(int indexOfGroup=0;indexOfGroup<numberOfGroups;indexOfGroup++)
		{
			List<User> group = new ArrayList<>();
			for(int i=0;i<groupSize;i++)
			{
				group.add(students.get(indexOfStudent));
				indexOfStudent++;
			}
			groups.add(group);
		}
		List<User> group = new ArrayList<>();
		while (indexOfStudent<students.size())
		{
			group.add(students.get(indexOfStudent));
			indexOfStudent++;
		}
		groups.add(group);

		return groups;
	}
}
