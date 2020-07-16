package dal.asd.catme.algorithm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.surveyresponse.ISurveyResponseModelAbstractFactory;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.DBQueriesUtil;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;

public class GroupFormationDaoImpl implements IGroupFormationDao {

	private static final Logger logger = LoggerFactory.getLogger(GroupFormationDaoImpl.class);
	IAlgorithmModelAbstractFactory modelAbstractFactory = BaseAbstractFactoryImpl.instance().makeAlgorithmModelAbstractFactory();
	DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();

	@Override
	public IAlgorithmParameters setAlgorithmParameter(int surveyId) {
		System.out.println("inside dao");
		IAlgorithmParameters algorithmParameter = modelAbstractFactory.makeAlgorithmParameters();

//		DatabaseAccess db = new DatabaseAccess();

		Connection con = null;
		try {
			con = db.getConnection();
			algorithmParameter.setGroupSize(getGroupSize(con, surveyId));
			List<Question> questions =getQuestionList(con, surveyId);
			algorithmParameter.setQuestions(questions);
			algorithmParameter.setNoOfStudents(getNumberOfStudents(con, surveyId,questions));
			
			
		} catch (NullPointerException e) {
			logger.error("Null exception occurred in ListGroups method.");
			e.printStackTrace();
		}
		catch (Exception e) {
			logger.error("Exeption occurred in ListGroups method.");
			e.printStackTrace();
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

	public int getNumberOfStudents(Connection con, int surveyId,List<Question> questions) {
		PreparedStatement stmt;
		Answer answerList ;
		List<Answer> listOfAnswers = new ArrayList<Answer>();
		try {
			stmt = con.prepareStatement(DBQueriesUtil.GET_STUDENTS);
			stmt.setInt(1, surveyId);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String bannerId=rs.getString(0);
				//********** need to be changed
				Student student = new Student();
				for(int i=0;i<questions.size();i++) {
					//********** need to be changed
					answerList = new Answer();
					
					Question question = questions.get(0);
					int questionId=question.getQuestionId();
					
					PreparedStatement statement = con.prepareStatement(DBQueriesUtil.GET_STUDENTS_WITH_ANSWERS);
					statement.setString(1, bannerId);
					statement.setInt(2, questionId);

					ResultSet resultSet = stmt.executeQuery();
					List<String> answers = new ArrayList<String>();
					while(resultSet.next()) {
						String answer= resultSet.getString("Answer");

						answers.add(answer);
					}
					List<Integer> convertedAnswers = new ArrayList<>();
					for(String individualAnswer : answers){
						int asciiValue=0;
						for(int j=0;j<individualAnswer.length();j++){
							asciiValue+=(int)individualAnswer.charAt(j);
						}
						convertedAnswers.add(asciiValue);
					}
					answerList.setAnswers(convertedAnswers);
					answerList.setQuestionId(questionId);
					listOfAnswers.add(answerList);
				}

				student.setAnswers(listOfAnswers);
				student.setBannerId(bannerId);
				

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
						question.setTotalNoOfOptions(Integer.parseInt(resultSet.getString(0)));
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
