package dal.asd.catme.algorithm;

import java.util.List;

public interface IAlgorithmParameters
{
    int getGroupSize();

    void setGroupSize(int groupSize);

    int getNoOfGroups();

    void setNoOfGroups(int noOfGroups);

    int getNoOfStudents();

    void setNoOfStudents(int noOfStudents);

    List<Question> getQuestions();

    void setQuestions(List<Question> questions);

    List<Student> getStudents();

    void setStudents(List<Student> students);

}
