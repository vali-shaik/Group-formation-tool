package dal.asd.catme.algorithm;

import java.util.List;

public class Student implements IStudent
{
    String bannerId;
    List<Answer> answers;

    public String getBannerId()
    {
        return bannerId;
    }

    public void setBannerId(String bannerId)
    {
        this.bannerId = bannerId;
    }

    public List<Answer> getAnswers()
    {
        return answers;
    }

    public void setAnswers(List<Answer> answers)
    {
        this.answers = answers;
    }

}
