package dal.asd.catme.dao;

import java.util.List;

import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;

public interface IQuestionManagerDao {
	public int createQuestion(Question question,String user);
	public int createQuestionTitle(String questionTitle,String user);
	public int createOptions(int questionId,List<Option> options);
}
