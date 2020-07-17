package dal.asd.catme.algorithm;

import java.sql.Connection;
import java.util.List;

public interface IGroupFormationDao
{
    IAlgorithmParameters setAlgorithmParameter(int surveyId);

    int getGroupSize(Connection con, int surveyId) throws AlgorithmException;

    int getNumberOfStudents(Connection con, int surveyId, List<Question> questions, IAlgorithmParameters algorithmParameters) throws AlgorithmException;

    List<Question> getQuestionList(Connection con, int surveyId) throws AlgorithmException;
}
