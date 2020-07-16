package dal.asd.catme.algorithm;

import java.util.List;

public interface IAlgorithmParameters {
	public int getGroupSize();
	public void setGroupSize(int groupSize);
	public int getNoOfGroups() ;
	public void setNoOfGroups(int noOfGroups);
	public int getNoOfStudents();
	public void setNoOfStudents(int noOfStudents);
	public List<Question> getQuestions();
	public void setQuestions(List<Question> questions);
}
