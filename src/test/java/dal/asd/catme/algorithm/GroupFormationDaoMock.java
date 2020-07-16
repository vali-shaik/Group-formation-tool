package dal.asd.catme.algorithm;

import java.util.ArrayList;
import java.util.List;

public class GroupFormationDaoMock {
    List<Question> questionTypeOne;
    List<Question> questionTypeTwo;
    List students;

    List getStudents(){
        students = new ArrayList<String>();
        students.add("s1");
        students.add("s2");
        students.add("s3");
        students.add("s4");
        students.add("s5");
        students.add("s6");
        students.add("s7");
        students.add("s8");
        return getStudents();
    }

    List getQuestionTypeOne(){
        questionTypeOne = new ArrayList<>();
        questionTypeOne.add(new Question(1,1,9));
        questionTypeOne.add(new Question(1,2,8));
        questionTypeOne.add(new Question(1,3,4));
        questionTypeOne.add(new Question(1,4,5));
        questionTypeOne.add(new Question(1,5,1));
        questionTypeOne.add(new Question(1,6,5));
        questionTypeOne.add(new Question(1,7,3));
        questionTypeOne.add(new Question(1,8,4));
        return questionTypeOne;
    }

    List getQuestionTypeTwo(){
        questionTypeTwo = new ArrayList<>();
        questionTypeTwo.add(new Question(1,9,10, 5));
        questionTypeTwo.add(new Question(1,10,5,5));
        questionTypeTwo.add(new Question(1,11,8, 5));
        return  getQuestionTypeTwo();
    }




}
