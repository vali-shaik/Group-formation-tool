package dal.asd.catme.beans;

import java.util.Map;
import java.util.TreeMap;

public class Question {
	
	String question;
	String questionType;
	Map<Integer,String> optionWithOrder = new TreeMap<Integer,String>();
	
	
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
		return optionWithOrder;
	}
	public void setOptionOrder(Map<Integer, String> optionOrder) {
		this.optionWithOrder = optionOrder;
	}
	

}
