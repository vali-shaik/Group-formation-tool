package dal.asd.catme.dao;

import java.sql.Connection;

public interface ICourseDao {
	public int checkCourseRegistration(String bannerId, String courseId, Connection con);
	public int checkCourseExists(String courseId, Connection con);
}
