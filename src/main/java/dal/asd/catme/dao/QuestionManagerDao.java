package dal.asd.catme.dao;

import static dal.asd.catme.util.DBQueriesUtil.INSERT_INTO_COURSE_INSTRUCTOR;
import static dal.asd.catme.util.DBQueriesUtil.SELECT_COURSE_INSTRUCTOR_BY_USER_ROLE_COURSEID;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale.Category;
import java.sql.ResultSet;

import dal.asd.catme.beans.Option;
import dal.asd.catme.beans.Question;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.DBQueriesUtil;

public class QuestionManagerDao implements IQuestionManagerDao{
	
	DatabaseAccess databaseAccess = new DatabaseAccess();
	Connection connection;
	PreparedStatement preparedStatement;
	ResultSet resultSet;

	@Override
	public int createQuestion(Question question,String user) {
		int result=0;
		try {
			connection = databaseAccess.getConnection();
			int questionTitleId = createQuestionTitle(question.getQuestionTitle(), user);
			
			preparedStatement = connection.prepareStatement(DBQueriesUtil.CHECK_QUESTION);
			preparedStatement.setInt(1, questionTitleId);
			preparedStatement.setString(2,question.getQuestionText());
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				preparedStatement=connection.prepareStatement(DBQueriesUtil.INSERT_QUESTION);
				preparedStatement.setInt(1, questionTitleId);
				preparedStatement.setString(2, question.getQuestionText());
				preparedStatement.setString(3,getQuestionType(question.getQuestionType()));
				//preparedStatement.setDate(4, new Date(questionTitleId));
				result=preparedStatement.executeUpdate();
				if(result>0) {
					preparedStatement = connection.prepareStatement(DBQueriesUtil.CHECK_QUESTION);
					preparedStatement.setInt(1, questionTitleId);
					preparedStatement.setString(2,question.getQuestionText());
					resultSet = preparedStatement.executeQuery();
					resultSet.next();
			}
			}
			result = Integer.parseInt(resultSet.getString("QuestionId"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public int createQuestionTitle(String questionTitle,String user) {
		int result=0;
		
		try {
			Connection connection = databaseAccess.getConnection();
			int userRoleId = getUserRoleId(connection,user);
			preparedStatement = connection.prepareStatement(DBQueriesUtil.CHECK_QUESTION_TITLE);
			preparedStatement.setString(1, questionTitle);
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				preparedStatement=connection.prepareStatement(DBQueriesUtil.INSERT_QUESTION_TITLE);
				preparedStatement.setString(1, questionTitle);
				preparedStatement.setInt(2, userRoleId);
				result=preparedStatement.executeUpdate();
				
				if(result>0) {
					preparedStatement = connection.prepareStatement(DBQueriesUtil.CHECK_QUESTION_TITLE);
					preparedStatement.setString(1, questionTitle);
					resultSet = preparedStatement.executeQuery();
					resultSet.next();
				}
				
			}
			result = Integer.parseInt(resultSet.getString("QuestionTitleId"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return result;

	}
	
	private int getUserRoleId(Connection connection,String user) {
		int userRoleId = 0;
		try {
		preparedStatement = connection.prepareStatement(DBQueriesUtil.SELECT_ROLE_BY_ROLENAME);
		preparedStatement.setString(CatmeUtil.ONE, CatmeUtil.INSTRUCTOR_ROLE);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			int roleId =Integer.parseInt(resultSet.getString("roleId"));
			preparedStatement = connection.prepareStatement(DBQueriesUtil.SELECT_USER_ROLE_BY_BANNERID);
			preparedStatement.setString(1, user);
			preparedStatement.setInt(2,roleId);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
		}
		
		userRoleId= Integer.parseInt(resultSet.getString("UserRoleId"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userRoleId;
	}
	
	private String getQuestionType(String questionType) {
		if(questionType.equalsIgnoreCase(CatmeUtil.FREE_TEXT))
			return CatmeUtil.FREETEXT_DB;
		else if(questionType.equalsIgnoreCase(CatmeUtil.NUMERIC))
			return CatmeUtil.NUMERIC_DB;
		else if(questionType.equalsIgnoreCase(CatmeUtil.MCQ_CHOOSE_MULTIPLE))
			return CatmeUtil.CHECKBOX;
		else 
			return CatmeUtil.RADIOBUTTON;
	}

	@Override
	public int createOptions(int questionId, List<Option> options) {
		int result =0;
		for(int i=0;i<options.size();i++) {
			Option option = options.get(i);
			if(option.getDisplayText().trim().length() > 0 && option.getDisplayText()!="") {
			try {
				connection = databaseAccess.getConnection();
				preparedStatement = connection.prepareStatement(DBQueriesUtil.CHECK_QUESTION_OPTION);
				preparedStatement.setInt(1, questionId);
				preparedStatement.setString(2, option.getDisplayText());
				resultSet = preparedStatement.executeQuery();
				if(!resultSet.next()) {
					preparedStatement = connection.prepareStatement(DBQueriesUtil.INSERT_QUESTION_OPTION);
					preparedStatement.setInt(1, questionId);
					preparedStatement.setString(2, option.getDisplayText());
					preparedStatement.setInt(3, option.getStoredAs());
					result=preparedStatement.executeUpdate();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		}
		return result;
	}

}
