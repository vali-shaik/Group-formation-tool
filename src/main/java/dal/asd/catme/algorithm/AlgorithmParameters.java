package dal.asd.catme.algorithm;

import java.util.List;

public class AlgorithmParameters {
	
    int groupSize;
    int noOfGroups;
    int noOfStudents;
	List<Question> questions;

    public int getGroupSize() {
		return groupSize;
	}
	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}
	public int getNoOfGroups() {
		return noOfGroups;
	}
	public void setNoOfGroups(int noOfGroups) {
		this.noOfGroups = noOfGroups;
	}
	public int getNoOfStudents() {
		return noOfStudents;
	}
	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	

}
