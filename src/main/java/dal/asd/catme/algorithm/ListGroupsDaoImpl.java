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
import dal.asd.catme.util.DBQueriesUtil;

public class ListGroupsDaoImpl implements IListGroupsDao {

	private static final Logger logger = LoggerFactory.getLogger(ListGroupsDaoImpl.class);

	@Override
	public List<SurveyGroups> listGroups(int surveyId) {
		logger.info("ListGroupsDaoImpl - listGroups() invoked");
		DatabaseAccess db = SystemConfig.instance().getDatabaseAccess();
		Connection con = null;
		List<SurveyGroups> surveyGroups = new ArrayList<SurveyGroups>();
		try {
			con = db.getConnection();
			PreparedStatement stmt = con.prepareStatement(DBQueriesUtil.GET_SURVEY_GROUPS);
			stmt.setInt(1, surveyId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				logger.info("here"+rs.getString("bannerId"));
				SurveyGroups surveyGroup = new SurveyGroups();
				surveyGroup.setGroupNumber(Integer.parseInt(rs.getString("GroupNumber")));
				surveyGroup.setBannerId(rs.getString("bannerId"));
				surveyGroup.setFirstName(rs.getString("firstName"));
				surveyGroup.setLastName(rs.getString("lastName"));
				surveyGroup.setSurveyId(surveyId);
				
				surveyGroups.add(surveyGroup);
			}
		} catch (SQLException e) {
			logger.error("Exeption occurred in ListGroups method.");
		}

		return null;
	}

}
