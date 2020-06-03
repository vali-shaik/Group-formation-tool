package dal.asd.catme.dao;

import java.sql.Connection;

public interface ICourseDao {
	public int checkCourseRegistration(String bannerId, int courseId, Connection con);

}
