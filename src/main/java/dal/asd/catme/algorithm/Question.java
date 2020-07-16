package dal.asd.catme.algorithm;

public class Question implements IQuestion{
	int rule;
    int questionId;
    String questionType;
    int priority;
    int totalNoOfOptions;

	public Question(int rule, int questionId, int priority) {
		this.rule = rule;
		this.questionId = questionId;
		this.priority = priority;
	}
	public Question(int rule, int questionId, int priority, int totalNoOfOptions) {
		this.rule = rule;
		this.questionId = questionId;
		this.priority = priority;
		this.totalNoOfOptions = totalNoOfOptions;
	}


	public int getTotalNoOfOptions() {
		return totalNoOfOptions;
	}
	public void setTotalNoOfOptions(int totalNoOfOptions) {
		this.totalNoOfOptions = totalNoOfOptions;
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
