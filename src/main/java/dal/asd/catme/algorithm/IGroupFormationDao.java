package dal.asd.catme.algorithm;

import java.sql.Connection;
import java.util.List;

public interface IGroupFormationDao {
	public IAlgorithmParameters setAlgorithmParameter(int surveyId);
	public int getGroupSize(Connection con, int surveyId) throws AlgorithmException;
	public int getNumberOfStudents(Connection con, int surveyId,List<Question> questions,IAlgorithmParameters algorithmParameters) throws AlgorithmException;
	public List<Question> getQuestionList(Connection con, int surveyId) throws AlgorithmException;
}
