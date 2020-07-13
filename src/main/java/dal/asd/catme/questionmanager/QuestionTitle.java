package dal.asd.catme.questionmanager;

import java.util.List;

public class QuestionTitle implements IQuestionTitle
{

    String questionTitle;
    List<IQuestion> questions;

    public QuestionTitle(String questionTitle)
    {
        this.questionTitle = questionTitle;
    }

    public QuestionTitle()
    {
    }

    public String getQuestionTitle()
    {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle)
    {
        this.questionTitle = questionTitle;
    }

    public List<IQuestion> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<IQuestion> questions)
    {
        this.questions = questions;
    }


}
