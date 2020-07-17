package dal.asd.catme.algorithm;

import java.util.List;

public interface IStudent
{
    String getBannerId();

    void setBannerId(String bannerId);

    List<Answer> getAnswers();

    void setAnswers(List<Answer> answers);

}
