package dal.asd.catme.dao;

import dal.asd.catme.beans.Student;

import java.sql.Connection;
import java.util.ArrayList;

public interface ICourseDao {
	public int checkCourseRegistration(String bannerId, int courseId, Connection con);

	public ArrayList<Student> getRegisteredStudents(String courseId, Connection con);

}
