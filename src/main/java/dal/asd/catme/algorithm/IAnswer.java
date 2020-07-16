package dal.asd.catme.algorithm;

import java.util.List;

public interface IAnswer {

	public int getQuestionId();
	public void setQuestionId(int questionId);
	public List<Integer> getAnswers();
	public void setAnswers(List<Integer> answers);
	
}
