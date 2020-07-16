package dal.asd.catme.algorithm;

import dal.asd.catme.util.CatmeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	GroupFormationServiceImpl(){}

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

	@Override
	public HashMap formGroups() {

		
		for (Question q : algorithmParameters.getQuestions()) {
			if (q.questionType.equalsIgnoreCase("RadioButton") || q.questionType.equalsIgnoreCase("Numeric")) {
				questionTypeOne.add(q);
				noOfQuestionsMcqNumeric += 1;
			}
			if (q.questionType.equalsIgnoreCase("CheckBox")) {
				questionTypeTwo.add(q);
				noOfQuestionsMcqMultiple += 1;
			}
		} /*
			 * for (Question q : questionTypeOne) {
			 * System.out.println("question id "+q.getQuestionId()+"\tquestion prior "+q.
			 * getPriority()); } for (Question q : questionTypeTwo) {
			 * System.out.println("question id "+q.getQuestionId()+"\tquestion prior "+q.
			 * getPriority()); }
			 */

		answerTableMcqNumeric = new int[noOfQuestionsMcqNumeric][noOfStudents];
		answerTableMcqMultiple = new MCQMultiple[noOfQuestionsMcqMultiple][noOfStudents];
		System.out.println(noOfQuestionsMcqNumeric + "\t" + noOfQuestionsMcqNumeric+"\t" + noOfStudents);

		for(Student student :algorithmParameters.getStudents()){
			System.out.println("student id "+student.getBannerId());
			students.add(student.getBannerId());
		}

		for(Student student : algorithmParameters.getStudents()){
			List<Answer> answersList = student.getAnswers();
			for(Answer answer : answersList){
				List<Integer> individualAnswersList =answer.getAnswers();

				/*int questIndex = questionTypeOne.indexOf(answer.questionId);
				int studentIndex = students.indexOf(student.getBannerId());
				answerTableMcqNumeric[questIndex][] = individualAnswersList.get(CatmeUtil.ZERO);*/
			}
		}

		//Filling 2D array for answers (MCQ Numeric)
		for(int i=0; i<algorithmParameters.getStudents().size();i++){
			Student student = algorithmParameters.getStudents().get(i);
			List<Answer> answersList = student.getAnswers();
			for(int j=0;j<questionTypeOne.size();j++){
				Question question = questionTypeOne.get(j);
				for(Answer answer:answersList){
					System.out.println("answer.getQuestionId()"+answer.getQuestionId());
					System.out.println("question.getQuestionId()"+question.getQuestionId());
					if (answer.getQuestionId() == question.getQuestionId()){
						System.out.println("answer.getAnswers().get(0) "+answer.getAnswers().get(0));
						answerTableMcqNumeric[i][j] = answer.getAnswers().get(0);
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



		return null;

	}


}
