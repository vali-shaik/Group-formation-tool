package dal.asd.catme.algorithm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.DBQueriesUtil;

public class GroupFormationDaoImpl implements IGroupFormationDao {

	private static final Logger logger = LoggerFactory.getLogger(GroupFormationDaoImpl.class);

	@Override
	public AlgorithmParameters setAlgorithmParameter(int surveyId) {
		AlgorithmParameters algorithmParameter = new AlgorithmParameters();
		DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
		Connection con = null;
		List<SurveyGroups> surveyGroups = new ArrayList<SurveyGroups>();
		try {
			con = db.getConnection();
			algorithmParameter.setGroupSize(getGroupSize(con, surveyId));
			algorithmParameter.setNoOfStudents(getNumberOfStudents(con, surveyId));
			algorithmParameter.setQuestions(getQuestionList(con, surveyId));
		} catch (SQLException e) {
			logger.error("Exeption occurred in ListGroups method.");
		}
		return algorithmParameter;

	}

	public int getGroupSize(Connection con, int surveyId) {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(DBQueriesUtil.GET_GROUP_SIZE);
			stmt.setInt(1, surveyId);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return Integer.parseInt(rs.getString("GroupSize"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public int getNumberOfStudents(Connection con, int surveyId) {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(DBQueriesUtil.GET_NUMBER_OF_STUDENTS);
			stmt.setInt(1, surveyId);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return Integer.parseInt(rs.getNString(0));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public List<Question> getQuestionList(Connection con, int surveyId) {
		List<Question> questions = new ArrayList<Question>();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(DBQueriesUtil.GET_QUESTIONS_LIST);
			stmt.setInt(1, surveyId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setQuestionId(Integer.parseInt(rs.getString("QuestionId")));
				question.setPriority(Integer.parseInt(rs.getString("Prority")));
				question.setQuestionType(rs.getString("QuestionType"));
				if (rs.getString("QuestionType").equalsIgnoreCase(CatmeUtil.SIMILAR)) {
					question.setRule(CatmeUtil.ONE);
				} else if (rs.getString("QuestionType").equalsIgnoreCase(CatmeUtil.DISSIMILAR)) {
					question.setRule(CatmeUtil.TWO);
				} else if (rs.getString("QuestionType").equalsIgnoreCase(CatmeUtil.GREATER_THAN)) {
					question.setRule(CatmeUtil.THREE);
				} else if (rs.getString("QuestionType").equalsIgnoreCase(CatmeUtil.LESSER_THAN)) {
					question.setRule(CatmeUtil.FOUR);
				}
				if (rs.getString("QuestionType").equalsIgnoreCase(CatmeUtil.CHECKBOX)) {
					PreparedStatement statement = con.prepareStatement(DBQueriesUtil.GET_QUESTIONS_LIST);
					statement.setInt(1, Integer.parseInt(rs.getString("QuestionId")));

					ResultSet resultSet = stmt.executeQuery();
					if(resultSet.next())
					{
						question.setTotalNumberOfAnswerOption(Integer.parseInt(resultSet.getString(0)));
					}
					
				}
				questions.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return questions;
	}

}
