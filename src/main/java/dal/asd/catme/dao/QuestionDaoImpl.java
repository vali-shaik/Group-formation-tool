package dal.asd.catme.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.util.CatmeUtil;

import static dal.asd.catme.util.DBQueriesUtil.*;

public class QuestionDaoImpl implements IQuestionDao{

	@Override
	public int deleteQuestion(int questionId, Connection con) {
		// TODO Auto-generated method stub
		int questionDeleted = 0;
		try {
			if(0 != checkExistingQuestion(questionId, con)){
				PreparedStatement stmt = con.prepareStatement(DELETE_QUESTION_QUERY);
				stmt.setInt(1,questionId);
				stmt.executeUpdate();
				questionDeleted = 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return questionDeleted;
	}

	@Override
	public int checkExistingQuestion(int questionId, Connection con) {
		// TODO Auto-generated method stub
		int rowCount = 0;
		try{
			PreparedStatement stmt = con.prepareStatement(CHECK_EXISTING_QUESTION_QUERY);
			stmt.setInt(1,questionId);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			rowCount = rs.getInt(1);
		}
		catch(SQLException e){e.printStackTrace();}		
		return rowCount;
	}

}
