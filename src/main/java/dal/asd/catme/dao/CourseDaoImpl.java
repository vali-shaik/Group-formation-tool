package dal.asd.catme.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dal.asd.catme.beans.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dal.asd.catme.database.DatabaseAccess;
@Component
public class CourseDaoImpl implements ICourseDao{
	
	@Autowired
	DatabaseAccess db;

	@Override
	public int checkCourseRegistration(String bannerId, int courseId, Connection con) {
		int rowCount = 0;
		// TODO Auto-generated method stub
		try {
			String query = "SELECT EXISTS(SELECT * FROM Enrollment WHERE BannerId = '" + bannerId + "' AND CourseId = '" + courseId + "');";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			rowCount = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowCount;
	}

	@Override
	public ArrayList<Student> getRegisteredStudents(String courseId, Connection con)
	{
		ArrayList<Student> registeredStudents = new ArrayList<>();

		String selectStudetns = "select BannerId, FirstName, LastName from Enrollment join(`User`) using(BannerId) where CourseId='"+courseId+"'";

		try
		{
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(selectStudetns);

			while(rs.next())
			{
				Student s = new Student(rs.getString(1), rs.getString(2),rs.getString(3),"");
				registeredStudents.add(s);
			}

			return registeredStudents;

		} catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		return null;
	}

}
