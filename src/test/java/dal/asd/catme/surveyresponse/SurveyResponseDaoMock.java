package dal.asd.catme.surveyresponse;

import dal.asd.catme.coursestest.POJOMock;
import dal.asd.catme.questionmanager.Question;
import dal.asd.catme.util.CatmeUtil;

import java.util.ArrayList;
import java.util.List;

public class SurveyResponseDaoMock implements ISurveyResponseDao
{
    @Override
    public String isSurveyPublished(String courseId)
    {
        for(String course: POJOMock.getPublishedCourses())
        {
            if(course.equals(courseId))
            {
                return course;
            }
        }

        return null;
    }

    @Override
    public List<SurveyResponse> getSurveyQuestions(String surveyId)
    {
        List<SurveyResponse> responses = new ArrayList<>();

        SurveyResponse s = new SurveyResponse();
        responses.add(s);

        Question q = new Question();
        q.setQuestionText("New Question");
        q.setQuestionType(CatmeUtil.NUMERIC_DB);

        s.setQuestion(q);

        if(surveyId.equals("5308"))
        {
            return responses;
        }
        return null;
    }

    @Override
    public boolean saveResponses(SurveyResponseBinder binder, String bannerId)
    {
        if(binder.getQuestionList().size()==0)
            return false;
        return true;
    }

    @Override
    public boolean saveAttempt(String surveyId, String bannerId)
    {
        if(surveyId==null || bannerId==null)
            return false;
        return true;
    }

    @Override
    public boolean isSurveyAttempted(String surveyId, String bannerId)
    {
        if(surveyId.equals("5100") && bannerId.equals("B00121212"))
            return true;
        return false;
    }
}
