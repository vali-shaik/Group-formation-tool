package dal.asd.catme.service;

import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;

import java.util.List;

public interface IQuestionManagerService
{
    public int createQuestion(Question question, String user);

    public String findQuestionType(Question question, String user);

    public int createOptions(int questionId, List<Option> options);

    public int deleteQuestion(int questionId);

}
