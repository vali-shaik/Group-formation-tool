package dal.asd.catme.beans;

import java.util.HashMap;
import java.util.Map;

public class Question {
	String questionTitle;
	String question;
	String questionType;
	Map<Integer,String> optionOrder = new HashMap<Integer,String>();
	
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public Map<Integer, String> getOptionOrder() {
		return optionOrder;
	}
	public void setOptionOrder(Map<Integer, String> optionOrder) {
		this.optionOrder = optionOrder;
	}
	

}
