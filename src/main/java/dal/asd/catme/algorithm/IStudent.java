package dal.asd.catme.algorithm;

import java.util.List;

public interface IStudent {
	public String getBannerId();
	public void setBannerId(String bannerId);
	public List<Answer> getAnswers();
	public void setAnswers(List<Answer> answers);
	
}
