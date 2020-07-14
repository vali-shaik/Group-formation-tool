package dal.asd.catme.questionmanager;

import java.util.List;

public interface IQuestionTitle
{

    String getQuestionTitle();
    void setQuestionTitle(String questionTitle);
    List<IQuestion> getQuestions();
    void setQuestions(List<IQuestion> questions);
}
