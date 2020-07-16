package dal.asd.catme.algorithm;

import dal.asd.catme.util.CatmeUtil;

import java.util.*;

public class GroupFormationServiceImpl implements IGroupFormationService {
	/*
	 * List<Question> questionTypeOne = new ArrayList<>(); List students = new
	 * ArrayList<String>();
	 */
	IAlgorithmParameters algorithmParameters;
	int groupSize;
	int noOfStudents;
	int noOfGroups;
	int noOfQuestionsMcqNumeric;
	int noOfQuestionsMcqMultiple;

	List<Question> questionTypeOne = new ArrayList<>();
	List<Question> questionTypeTwo = new ArrayList<>();
	List students = new ArrayList<String>();

	int answerTableMcqNumeric[][];
	MCQMultiple answerTableMcqMultiple[][];

	int priorityTable[][];
	String groups[][];
	Map finalGroups = new HashMap<String, ArrayList<String>>();
	List memoization = new ArrayList<String>();
	List remainingStudents;

	GroupFormationServiceImpl(){

	}

	GroupFormationServiceImpl(int surveyId) {
		this.algorithmParameters = groupFormationDao.setAlgorithmParameter(surveyId);
		this.groupSize = this.algorithmParameters.getGroupSize();
		this.noOfStudents = this.algorithmParameters.getNoOfStudents();
		this.noOfGroups = this.noOfStudents / this.groupSize;
		this.priorityTable = new int[this.noOfStudents][this.noOfStudents];
		this.groups = new String[this.noOfGroups][this.groupSize];
	}

	IGroupFormationDao groupFormationDao=new GroupFormationDaoImpl();
	public GroupFormationServiceImpl(IGroupFormationDao groupFormationDao) {

		this.groupFormationDao = groupFormationDao;
	}

	void bubbleSort(int i, String [] arr)
	{
		int n = noOfStudents;
		for (int k = 0; k < n-1; k++)
		{
			for (int j = 0; j < n-k-1; j++)
			{
				if (priorityTable[i][j] < priorityTable[i][j+1])
				{
					int temp = priorityTable[i][j];
					String temp2 = arr[j];
					priorityTable[i][j] = priorityTable[i][j+1];
					arr[j] = arr[j+1];
					priorityTable[i][j+1] = temp;
					arr[j+1] = temp2;
				}
			}
		}
	}

	void fillFromMcqNumeric(){
		Question q;
		int priority;
		int rule;
		for(int j = 0; j<noOfStudents-1; j+=1) {
			for (int k = j + 1; k < noOfStudents; k += 1) {
				for (int i = 0; i < noOfQuestionsMcqNumeric; i += 1) {
					q = questionTypeOne.get(i);
					priority = q.priority;
					rule = q.rule;
					if (rule == 1) {
						if (answerTableMcqNumeric[i][j] == answerTableMcqNumeric[i][k]) {
							priorityTable[j][k] += priority;
							priorityTable[k][j] += priority;
						}
					}
					if (rule == 2) {
						if (answerTableMcqNumeric[i][j] == answerTableMcqNumeric[i][k]) {
							continue;
						} else {
							priorityTable[j][k] += priority;
							priorityTable[k][j] += priority;
						}
					}
				}
			}
		}
	}

	void fillFromMcqMultiple() {
		Question q;
		int priority;
		int rule;
		int totalNoOfOptions;
		for (int j = 0; j < noOfStudents - 1; j += 1) {
			for (int k = j + 1; k < noOfStudents; k += 1) {
				for(int i = 0; i< noOfQuestionsMcqMultiple; i+=1){
					q = questionTypeTwo.get(i);
					priority = q.priority;
					rule = q.rule;
					totalNoOfOptions = q.totalNoOfOptions;

					MCQMultiple mcqMultiple1 =answerTableMcqMultiple[i][j];
					MCQMultiple mcqMultiple2 =answerTableMcqMultiple[i][k];
					Set<Integer> set1 = mcqMultiple1.getAnswers();
					Set<Integer> set2 = mcqMultiple2.getAnswers();

					Set<Integer> intersection = new HashSet<Integer>(set1);
					intersection.retainAll(set2);
					int priorityPerOption = priority/totalNoOfOptions;

					Set<Integer> difference;
					if(set1.size()>set2.size()){
						difference = new HashSet<Integer>(set1);
						difference.removeAll(set2);
					}
					else {
						difference = new HashSet<Integer>(set2);
						difference.removeAll(set1);
					}
					if(rule == 1){
						if(intersection.size()>0){
							priorityTable[j][k] += (priorityPerOption * intersection.size());
							priorityTable[k][j] += (priorityPerOption * intersection.size());
						}
					}
					if(rule == 2){
						if(difference.size()>0){
							priorityTable[j][k] += (priorityPerOption * difference.size());
							priorityTable[k][j] += (priorityPerOption * difference.size());
						}
					}
				}
			}
		}
	}
	void fillPriorityTable(){
		fillFromMcqNumeric();
		fillFromMcqMultiple();
	}
	void populateData(){
		for (Question q : algorithmParameters.getQuestions()) {
			if (q.questionType.equalsIgnoreCase("RadioButton") || q.questionType.equalsIgnoreCase("Numeric")) {
				questionTypeOne.add(q);
				noOfQuestionsMcqNumeric += 1;
			}
			if (q.questionType.equalsIgnoreCase("CheckBox")) {
				questionTypeTwo.add(q);
				noOfQuestionsMcqMultiple += 1;
			}
		}
		answerTableMcqNumeric = new int[noOfQuestionsMcqNumeric][noOfStudents];
		answerTableMcqMultiple = new MCQMultiple[noOfQuestionsMcqMultiple][noOfStudents];
		for(Student student :algorithmParameters.getStudents()){
			students.add(student.getBannerId());
		}

		for(Student student : algorithmParameters.getStudents()){
			List<Answer> answersList = student.getAnswers();
			for(Answer answer : answersList){
				List<Integer> individualAnswersList =answer.getAnswers();
			}
		}

		//Filling 2D array for answers (MCQ Numeric)
		for(int i=0; i<algorithmParameters.getStudents().size();i++){
			Student student = algorithmParameters.getStudents().get(i);
			List<Answer> answersList = student.getAnswers();
			for(int j=0;j<questionTypeOne.size();j++){
				Question question = questionTypeOne.get(j);
				for(Answer answer:answersList){
					if (answer.getQuestionId() == question.getQuestionId()){
						System.out.println("answer.getAnswers().get(0) "+answer.getAnswers().get(0));
						answerTableMcqNumeric[i][j] = answer.getAnswers().get(0);
					}
				}
			}
		}

		//Filling 2D array for answers (MCQ Multiple)
		for(int i=0; i<algorithmParameters.getStudents().size();i++){
			Student student = algorithmParameters.getStudents().get(i);
			List<Answer> answersList = student.getAnswers();
			for(int j=0;j<questionTypeTwo.size();j++){
				Question question = questionTypeTwo.get(j);
				for(Answer answer:answersList){
					if (answer.getQuestionId() == question.questionId){
						answerTableMcqMultiple[i][j] = new MCQMultiple(new HashSet<Integer>(answer.getAnswers()));
					}
				}
			}
		}

		for(int i=0; i<students.size();i++){
			System.out.print(students.get(i)+"\t");
		}
		System.out.println();
		for(int i=0; i<questionTypeOne.size();i++){
			Question ques=questionTypeOne.get(i);
			System.out.print(ques.getQuestionId()+"\t");
			for(int j=0;j<students.size();j++){
				System.out.println(i+" "+j);
				System.out.print(answerTableMcqNumeric[i][j]+"\t");
			}
			System.out.println();
		}
	}

	@Override
	public Map formGroups() {
		populateData();
		String[] studentsCopy;
		int k = 0;
		for(int i=0;i<noOfGroups;i+=1){
			studentsCopy = (String[]) students.toArray(new String[students.size()]);
			while(true){
				boolean a = memoization.contains(students.get(k));
				if(memoization.size()==0){
					break;
				}
				if(memoization.size()>0 && a==false){
					break;
				}
				++k;
			}
			bubbleSort(k,studentsCopy);
			groups[i][0] = (String) students.get(k);
			memoization.add(students.get(k));
			for(int j=0;j<groupSize-1;j+=1)
			{
				if(priorityTable[k][j]==0 || memoization.contains(studentsCopy[j])){
					continue;
				}
				else {
					groups[i][j+1] = studentsCopy[j];
					memoization.add(studentsCopy[j]);
					priorityTable[i][j] = 0;
					priorityTable[j][i] = 0;
				}
			}
			k+=1;
		}
		distributeRemaining();
		Map groupsMap = returnGroups();
		return groupsMap;
	}

	void distributeRemaining(){
		remainingStudents = new ArrayList<String>();
		for(int i = 0;i<students.size();i+=1){
			if(!memoization.contains(students.get(i))){
				remainingStudents.add(students.get(i));
			}
		}
		for(int i =0;i<noOfGroups;i+=1){
			for(int j =0;j<groupSize;j+=1){
				if(groups[i][j] == null){
					if(remainingStudents.size()>0){
						groups[i][j] = (String)remainingStudents.get(remainingStudents.size() - 1);
						remainingStudents.remove(remainingStudents.size() - 1);
					}
				}
			}
		}
	}

	Map returnGroups(){
		int i = 0;
		ArrayList<String> bannerIds = null;
		for( ;i<noOfGroups;i++){
			finalGroups.put("Group"+i+1, new ArrayList<String>());
			System.out.print("\n"+"Group"+(i+1)+"\t");
			for(int j =0;j<groupSize;j++){
				bannerIds = (ArrayList<String>) finalGroups.get("Group"+i+1);
				bannerIds.add(groups[i][j]);
			}
			for(String bannerId:bannerIds){
				System.out.print(bannerId+"\t");
			}
		}
		if(remainingStudents.size()>0 && remainingStudents.size()+1 == groupSize){
			finalGroups.put("Group"+i+1, remainingStudents);
			System.out.print("\n"+"Group"+(i+1)+"\t");
			for(String bannerId:(ArrayList<String>)finalGroups.get("Group"+i+1)){
				System.out.print(bannerId+"\t");
			}
		}
		if(remainingStudents.size()>0 && remainingStudents.size()+1 < groupSize){
			for(int j=0; j<remainingStudents.size(); j++){
				if(remainingStudents.size()>0){
					bannerIds = (ArrayList<String>) finalGroups.get("Group"+j);
					bannerIds.add((String)remainingStudents.get(remainingStudents.size()-1));
				}
			}
			for(String bannerId:(ArrayList<String>)finalGroups.get("Group"+i+1)){
				System.out.print(bannerId+"\t");
			}
		}
		return finalGroups;
	}


}
