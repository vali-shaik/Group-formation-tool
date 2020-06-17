package dal.asd.catme.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Question {
	
	String questionText;
	String questionType;
	List<Option> optionWithOrder = new ArrayList<Option>();
	Date createdDate;
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public List<Option> getOptionWithOrder() {
		return optionWithOrder;
	}
	public void setOptionWithOrder(List<Option> optionWithOrder) {
		this.optionWithOrder = optionWithOrder;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "Question [questionText=" + questionText + ", questionType=" + questionType + ", optionWithOrder="
				+ optionWithOrder + ", createdDate=" + createdDate + ", getQuestionText()=" + getQuestionText()
				+ ", getQuestionType()=" + getQuestionType() + ", getOptionWithOrder()=" + getOptionWithOrder()
				+ ", getCreatedDate()=" + getCreatedDate() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	

}
