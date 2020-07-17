package dal.asd.catme.algorithm;

import dal.asd.catme.surveyresponse.SurveyResponse;

import java.util.List;

public class SurveyGroups
{
    int surveyId;

    int groupNumber;
    String bannerId;
    String firstName;
    String lastName;

    List<SurveyResponse> surveyResponses;

    public int getSurveyId()
    {
        return surveyId;
    }

    public void setSurveyId(int surveyId)
    {
        this.surveyId = surveyId;
    }

    public int getGroupNumber()
    {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber)
    {
        this.groupNumber = groupNumber;
    }

    public String getBannerId()
    {
        return bannerId;
    }

    public void setBannerId(String bannerId)
    {
        this.bannerId = bannerId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public List<SurveyResponse> getSurveyResponses()
    {
        return surveyResponses;
    }

    public void setSurveyResponses(List<SurveyResponse> surveyResponses)
    {
        this.surveyResponses = surveyResponses;
    }

}
