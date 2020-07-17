package dal.asd.catme.algorithm;

import java.util.List;

public interface IAnswer
{

    int getQuestionId();

    void setQuestionId(int questionId);

    List<Integer> getAnswers();

    void setAnswers(List<Integer> answers);

}
