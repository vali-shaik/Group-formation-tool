package dal.asd.catme.survey;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.IAccessControlModelAbstractFactory;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;
import dal.asd.catme.questionmanager.IQuestionManagerModelAbstractFactory;
import dal.asd.catme.util.DBQueriesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurveyGroupDaoImpl implements ISurveyGroupDao 
{
	private static final Logger log = LoggerFactory.getLogger(SurveyGroupDaoImpl.class);
	IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
	IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();
	IAccessControlModelAbstractFactory accessControlModelAbstractFactory = baseAbstractFactory.makeAccessControlModelAbstractFactory();

	@Override
	public List<User> displaySurveyGroups(int surveyId) 
	{
		return null;
	}

	@Override
	public int getGroupSize(int surveyId)
	{
		IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();

		try
		{
			PreparedStatement stmt = db.getPreparedStatement(DBQueriesUtil.GET_SURVEY_GROUP_SIZE);
			stmt.setInt(1,surveyId);

			ResultSet rs = db.executeForResultSet(stmt);

			if(rs.next())
			{
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		finally
		{
			db.cleanUp();
		}

		return 0;
	}

	@Override
	public List<User> getStudents(int surveyId)
	{
		IDatabaseAccess db = databaseAbstractFactory.makeDatabaseAccess();
		List<User> users = new ArrayList<>();
		try
		{
			PreparedStatement stmt = db.getPreparedStatement(DBQueriesUtil.GET_BANNER_ID);
			stmt.setInt(1,surveyId);

			ResultSet rs = db.executeForResultSet(stmt);

			while (rs.next())
			{
				User u = accessControlModelAbstractFactory.makeUser();
				u.setBannerId(rs.getString(1));
				users.add(u);
			}
			return users;
		} catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		finally
		{
			db.cleanUp();
		}

		return null;
	}
}
