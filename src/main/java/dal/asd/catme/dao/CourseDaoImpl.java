
package dal.asd.catme.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dal.asd.catme.beans.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.CatmeUtil;

@Component
public class CourseDaoImpl implements ICourseDao
{
	CourseDaoImpl(DatabaseAccess database)
	{
		this.database=database;
	}
	
	//Creating Logger
	private static final Logger log = LoggerFactory.getLogger(CourseDaoImpl.class);
	
	@Autowired
	DatabaseAccess database;
	
	@Override
	public List<Course> getCourses(String role) throws CatmeException 
	{
		//Fetching all courses from DB
		log.info("Fetching all courses related to User");
		
		//Created List of Course entities for saving fetched DB data
		List<Course> listOfCourses=new ArrayList<>();
		ResultSet resultSet = null;
		Statement statement=null;
		Connection connection=null;
		
		try 
		{
			//Create database connection
			connection=database.getConnection();
			
			//Creating statement for executing query
			statement=connection.createStatement();
			
			if(connection!=null)
			{
				//Database operation performed on successful connection to DB
				log.info("DB connection is succesfull");
				String currentUser=SecurityContextHolder.getContext().getAuthentication().getName();

				//Executing different queries based on current User's role
				switch(role)
				{
				case CatmeUtil.GUEST_ROLE:
							
							//Executing query to fetch all courses
							log.info("Fetching courses of User "+currentUser+": GUEST");
							resultSet = statement.executeQuery(CatmeUtil.SELECT_GUEST_COURSES_QUERY);
							break;
							
				case CatmeUtil.TA_ROLE:
					
							//Executing query to fetch all Enrolled courses and Teaching courses
							log.info("Fetching courses of User "+currentUser+": TA");
							resultSet = statement.executeQuery(CatmeUtil.SELECT_STUDENT_COURSES_QUERY+"'"+currentUser+"'"+" UNION "+CatmeUtil.SELECT_INSTRUTOR_COURSES_QUERY+"'"+currentUser+"'");
							break;
							
				case CatmeUtil.INSTRUCTOR_ROLE:
					
							//Executing query to fetch all courses as Instructor
							log.info("Fetching courses of User "+currentUser+": INSTRUCTOR");
							resultSet = statement.executeQuery(CatmeUtil.SELECT_INSTRUTOR_COURSES_QUERY+""+"'"+currentUser+"'");
							break;
							
				case CatmeUtil.STUDENT_ROLE:
					
							//Executing query to fetch all courses as Student
							log.info("Fetching courses of User "+currentUser+": STUDENT");
							resultSet = statement.executeQuery(CatmeUtil.SELECT_STUDENT_COURSES_QUERY+""+"'"+currentUser+"'");
							break;
							
				default:
							break;
				}
				
				while(resultSet.next()) 
				{
					//Parsing Database result and mapping it to Java beans
					log.info("Fetched all courses from Database");
					Course course = new Course();
					course.setCourseId(resultSet.getString(CatmeUtil.COURSE_ID_FIELD));
					course.setCourseName(resultSet.getString(CatmeUtil.COURSE_NAME_FIELD));
					
					//After parsing, adding each course to list of courses
					listOfCourses.add(course);
				}
			}
		}
		catch (SQLException|NullPointerException e) 
		{
			//Throwing user defined exception
			throw new CatmeException("Failed while connecting and fetching courses from DB "+e.getMessage());
		}
		finally
		{
			try 
			{
				//closing Connection, statement,result set
				if(connection!=null)
					connection.close();
				if(statement!=null)
					statement.close();
				if(resultSet!=null)
					resultSet.close();
			} 
			catch (SQLException|NullPointerException e) 
			{
				throw new CatmeException("Failed while closing connection with database "+e.getMessage());
			}
		}	

		return listOfCourses;
	}
	
	@Override
	public Course displayCourseById(String courseId) throws CatmeException 
	{
		ResultSet resultSet = null;
		Statement statement=null;
		Connection connection=null;
		
		//Creating course bean to save and return result from DB
		Course course = new Course();
	
		try 
		{
			//Creating connection
			connection=database.getConnection();
			
			//Creating statement for executing query
			statement=connection.createStatement();
			
			if(connection!=null)
			{	
				//Executing query to get Course details by passing course id
				resultSet = statement.executeQuery(CatmeUtil.SELECT_COURSE_QUERY+"'"+courseId+"'");

				while(resultSet.next()) 
				{
					//Parsing result set object and mapping result to Java beans
					course.setCourseId(resultSet.getString(CatmeUtil.COURSE_ID_FIELD));
					course.setCourseName(resultSet.getString(CatmeUtil.COURSE_NAME_FIELD));
				}
			}
		} 
		catch (SQLException|NullPointerException e) 
		{
			//Throwing user defined exception
			throw new CatmeException("Failed while connecting and fetching Course from DB "+e.getMessage());
		}
		finally
		{
			try 
			{
				//Closing database related connections
				if(connection!=null)
					connection.close();
				if(statement!=null)
					statement.close();
				if(resultSet!=null)
					resultSet.close();
			} 
			catch (SQLException|NullPointerException e) 
			{
				//Throwing user defined exceptions
				throw new CatmeException("Failed while closing conection with DB "+e.getMessage());
			}
		}	

		return course;
	}
	
	@Override
	public String findRoleByCourse(User user,String courseId) throws CatmeException
	{
		ResultSet resultSet = null;
		Statement statement=null;
		Connection connection=null;
		String role="";

		try 
		{
			//Creating connection
			connection=database.getConnection();
			
			//Creating statement for executing query
			statement=connection.createStatement();
			
			if(connection!=null)
			{	
				//Executing query to fetch enrolled courses and teaching courses
				resultSet = statement.executeQuery(CatmeUtil.SELECT_COURSE_ROLE_QUERY+"'"+user.getBannerId()+"' and  c.courseId='"+courseId+"'");
				
				while(resultSet.next()) 
				{
					//Parsing result data and storing it in variable
					role=resultSet.getString(CatmeUtil.ROLE_NAME_FIELD);
				}
			}
		} 
		catch (SQLException|NullPointerException e) 
		{
			//throwing user defined exception
			throw new CatmeException("Failed while connecting and Fetching Role for a Course in DB"+e.getMessage());
		}
		finally
		{
			try 
			{
				//Closing all database connections
				if(connection!=null)
					connection.close();
				if(statement!=null)
					statement.close();
				if(resultSet!=null)
					resultSet.close();
			} 
			catch (SQLException|NullPointerException e) 
			{
				//throwing user defined exception
				throw new CatmeException("Failed while closing conection with DB "+e.getMessage());
			}
		}	
		return role;
	}

	@Override
	public ArrayList<Student> getRegisteredStudents(String courseId)
	{
		ArrayList<Student> registeredStudents = new ArrayList<>();

		String selectStudetns = "select BannerId, FirstName, LastName from Enrollment join(`User`) using(BannerId) where CourseId='"+courseId+"'";

		Connection con = null;
		try
		{
			con = database.getConnection();
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
		finally
		{
			try
			{
				con.close();
			} catch (SQLException|NullPointerException throwables)
			{
				throwables.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * @Override public int checkCourseRegistration(String bannerId, int courseId,
	 * Connection con) { int rowCount = 0; // TODO Auto-generated method stub try {
	 * String query = "SELECT EXISTS(SELECT * FROM Enrollment WHERE BannerId = '" +
	 * bannerId + "' AND CourseId = '" + courseId + "');";
	 * 
	 * Statement stmt = con.createStatement(); ResultSet rs =
	 * stmt.executeQuery(query); rs.next(); rowCount = rs.getInt(1); } catch
	 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return rowCount; }
	 */
@Override
	public int checkCourseExists(String courseId, Connection con) {
		int rowCount = 0;
		// TODO Auto-generated method stub
		try {
			String query = "SELECT EXISTS(SELECT * FROM Course WHERE CourseId  = '" + courseId + "');";
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
	public int checkCourseRegistration(String bannerId, String courseId, Connection con) {
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

}
