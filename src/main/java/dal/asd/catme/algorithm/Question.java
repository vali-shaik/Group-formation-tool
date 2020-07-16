package dal.asd.catme.algorithm;

public class Question implements IQuestion{
	int rule;
    int questionId;
    String questionType;
    int priority;
    int totalNumberOfAnswerOption;
    
    public int getTotalNumberOfAnswerOption() {
		return totalNumberOfAnswerOption;
	}
	public void setTotalNumberOfAnswerOption(int totalNumberOfAnswerOption) {
		this.totalNumberOfAnswerOption = totalNumberOfAnswerOption;
	}
	public int getRule() {
		return rule;
	}
	public void setRule(int rule) {
		this.rule = rule;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
}
