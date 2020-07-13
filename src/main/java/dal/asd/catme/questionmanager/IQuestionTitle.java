package dal.asd.catme.questionmanager;

import java.util.List;

public interface IQuestionTitle
{

    public String getQuestionTitle();
    public void setQuestionTitle(String questionTitle);
    public List<IQuestion> getQuestions();
    public void setQuestions(List<IQuestion> questions);
}
