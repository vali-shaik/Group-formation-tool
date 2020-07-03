package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.Student;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.CatmeException;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dal.asd.catme.util.DBQueriesUtil.*;

public class CourseDaoImpl implements ICourseDao
{
    public CourseDaoImpl(DatabaseAccess database)
    {
        this.database = database;
    }

    public CourseDaoImpl()
    {
    }
    private static final Logger log = LoggerFactory.getLogger(CourseDaoImpl.class);

    DatabaseAccess database;

    @Override
    public List<Course> getCourses(String role) throws CatmeException
    {
        log.info("Fetching all courses related to User");

        List<Course> listOfCourses = new ArrayList<>();
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;

        try
        {
            database = SystemConfig.instance().getDatabaseAccess();
            connection = database.getConnection();
            statement = connection.createStatement();

            if (connection != null)
            {
                log.info("DB connection is succesfull");
                String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

                switch (role)
                {
                    case CatmeUtil.GUEST_ROLE:
                        log.info("Fetching courses of User " + currentUser + ": GUEST");
                        resultSet = statement.executeQuery(SELECT_GUEST_COURSES_QUERY);
                        break;

                    case CatmeUtil.TA_ROLE:
                        log.info("Fetching courses of User " + currentUser + ": TA");
                        resultSet = statement.executeQuery(SELECT_STUDENT_COURSES_QUERY + "'" + currentUser + "'" + " UNION " + SELECT_INSTRUTOR_COURSES_QUERY + "'" + currentUser + "'");
                        break;

                    case CatmeUtil.INSTRUCTOR_ROLE:
                        log.info("Fetching courses of User " + currentUser + ": INSTRUCTOR");
                        resultSet = statement.executeQuery(SELECT_INSTRUTOR_COURSES_QUERY + "" + "'" + currentUser + "'");
                        break;

                    case CatmeUtil.STUDENT_ROLE:
                        log.info("Fetching courses of User " + currentUser + ": STUDENT");
                        resultSet = statement.executeQuery(SELECT_STUDENT_COURSES_QUERY + "" + "'" + currentUser + "'");
                        break;

                    default:
                        break;
                }

                while (resultSet.next())
                {
                    log.info("Fetched all courses from Database");
                    Course course = new Course();
                    course.setCourseId(resultSet.getString(CatmeUtil.COURSE_ID_FIELD));
                    course.setCourseName(resultSet.getString(CatmeUtil.COURSE_NAME_FIELD));

                    listOfCourses.add(course);
                }
            }
        } catch (SQLException | NullPointerException e)
        {
            throw new CatmeException("Failed while connecting and fetching courses from DB " + e.getMessage());
        } finally
        {
            try
            {
                if (connection != null)
                    connection.close();
                if (statement != null)
                    statement.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException | NullPointerException e)
            {
                throw new CatmeException("Failed while closing connection with database " + e.getMessage());
            }
        }

        return listOfCourses;
    }

    @Override
    public Course displayCourseById(String courseId) throws CatmeException
    {
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;

        Course course = new Course();
        try
        {
            database = SystemConfig.instance().getDatabaseAccess();
            connection = database.getConnection();

            statement = connection.createStatement();

            if (connection != null)
            {
                resultSet = statement.executeQuery(SELECT_COURSE_QUERY + "'" + courseId + "'");

                while (resultSet.next())
                {
                    course.setCourseId(resultSet.getString(CatmeUtil.COURSE_ID_FIELD));
                    course.setCourseName(resultSet.getString(CatmeUtil.COURSE_NAME_FIELD));
                }
            }
        } catch (SQLException | NullPointerException e)
        {
            throw new CatmeException("Failed while connecting and fetching Course from DB " + e.getMessage());
        } finally
        {
            try
            {
                if (connection != null)
                    connection.close();
                if (statement != null)
                    statement.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException | NullPointerException e)
            {
                throw new CatmeException("Failed while closing conection with DB " + e.getMessage());
            }
        }

        return course;
    }

    @Override
    public String findRoleByCourse(User user, String courseId) throws CatmeException
    {
        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        String role = "";

        try
        {
            database = SystemConfig.instance().getDatabaseAccess();
            connection = database.getConnection();

            statement = connection.createStatement();

            if (connection != null)
            {
                resultSet = statement.executeQuery(SELECT_COURSE_ROLE_QUERY + "'" + user.getBannerId() + "' and  c.courseId='" + courseId + "'");

                while (resultSet.next())
                {
                    role = resultSet.getString(CatmeUtil.ROLE_NAME_FIELD);
                }
            }
        } catch (SQLException | NullPointerException e)
        {
            throw new CatmeException("Failed while connecting and Fetching Role for a Course in DB" + e.getMessage());
        } finally
        {
            try
            {
                if (connection != null)
                    connection.close();
                if (statement != null)
                    statement.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException | NullPointerException e)
            {
                throw new CatmeException("Failed while closing conection with DB " + e.getMessage());
            }
        }
        return role;
    }

    @Override
    public ArrayList<Student> getRegisteredStudents(String courseId)
    {
        ArrayList<Student> registeredStudents = new ArrayList<>();

        Connection con = null;
        try
        {
            database = SystemConfig.instance().getDatabaseAccess();
            con = database.getConnection();

            PreparedStatement stmt = con.prepareStatement(SEELCT_ENROLLED_STUDENTS_QUERY);
            stmt.setString(1, courseId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Student s = new Student(rs.getString(1), rs.getString(2), rs.getString(3), "");
                registeredStudents.add(s);
            }

            return registeredStudents;

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        } finally
        {
            try
            {
                con.close();
            } catch (SQLException | NullPointerException throwables)
            {
                throwables.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public int checkCourseExists(String courseId, Connection con)
    {
        int rowCount = 0;
        try
        {
            PreparedStatement stmt = con.prepareStatement(CHECK_COURSE_QUERY);
            stmt.setString(1, courseId);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return rowCount;
    }

    @Override
    public int checkCourseRegistration(String bannerId, String courseId, Connection con)
    {
        int rowCount = 0;
        try
        {
            PreparedStatement stmt = con.prepareStatement(CHECK_COURSE_REGISTRATION_QUERY);
            stmt.setString(1, bannerId);
            stmt.setString(2, courseId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return rowCount;
    }
}
