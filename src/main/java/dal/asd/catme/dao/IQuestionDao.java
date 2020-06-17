package dal.asd.catme.dao;

import java.sql.Connection;

public interface IQuestionDao {
	public int deleteQuestion(int questionId,Connection con);
	public int checkExistingQuestion(int questionId, Connection con);

}
