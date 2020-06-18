package dal.asd.catme.beans;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Question {
	int questionId;
	String question;
	String questionTitle;
	String questionType;
	Map<Integer,String> optionWithOrder = new TreeMap<Integer,String>();
	Date createdDate;
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

	public Date getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	public String getQuestionTitle()
	{
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle)
	{
		this.questionTitle = questionTitle;
	}

	public int getQuestionId()
	{
		return questionId;
	}

	public void setQuestionId(int questionId)
	{
		this.questionId = questionId;
	}
}
