package dal.asd.catme.algorithm;

public interface IQuestion {

	public int getTotalNoOfOptions();

	public void setTotalNoOfOptions(int totalNumberOfAnswerOption);

	public int getRule();

	public void setRule(int rule);

	public int getQuestionId();

	public void setQuestionId(int questionId);

	public String getQuestionType();

	public void setQuestionType(String questionType);

	public int getPriority();

	public void setPriority(int priority);
}
