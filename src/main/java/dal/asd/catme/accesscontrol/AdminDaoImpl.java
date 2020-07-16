package dal.asd.catme.accesscontrol;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.courses.Course;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;
import dal.asd.catme.util.CatmeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dal.asd.catme.util.DBQueriesUtil.*;

@Component
public class AdminDaoImpl implements IAdminDao
{

    IDatabaseAccess db;
    PreparedStatement preparedStatement;
    ResultSet rs;
    Connection connection;

    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();
    private static final Logger log = LoggerFactory.getLogger(AdminDaoImpl.class);
    @Override
    public int addCourse(Course course)
    {
    	log.info("Adding a new Course");
        int result = 0;
        try
        {
            db = databaseAbstractFactory.makeDatabaseAccess();
            connection = db.getConnection();
            result = addCourse(connection, ADD_COURSE_QUERY, course);
        } catch (Exception e)
        {
        	log.error("Failed while adding a new course");
            e.printStackTrace();
        } finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public int deleteCourse(String courseId)
    {
    	log.info("Deleting a course from application");
        int result = 0;
        try
        {
            if (courseId.length() > CatmeUtil.ZERO)
            {
                db = databaseAbstractFactory.makeDatabaseAccess();
                connection = db.getConnection();
                updateQuery(connection, DELETE_ENROLLMENT_QUERY, courseId);
                updateQuery(connection, DELETE_COURSE_INSTRUCTOR_QUERY, courseId);
                result = updateQuery(connection, DELETE_COURSE_QUERY, courseId);
            }
        } catch (Exception e)
        {
        	log.error("Failed while deleting a course");
            e.printStackTrace();
        } finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public int addInstructorToCourse(String user, String course)
    {
    	log.info("Adding an instructor to respective course "+course);
        int result = 0;
        try
        {
            if (user.length() > CatmeUtil.ZERO && course.length() > CatmeUtil.ZERO)
            {
                db = databaseAbstractFactory.makeDatabaseAccess();
                connection = db.getConnection();
                int roleId = selectInstructorRole(connection);
                System.out.println("roleId " + roleId);
                int userRole = insertInstructorRole(connection, user, roleId);
                result = addAsCourseInstructor(connection, course, userRole);
            }
        } catch (Exception e)
        {
        	log.error("Failed while adding a instructor to a course");
            e.printStackTrace();
        } finally
        {
            if (connection != null)
            {
                try
                {
                    connection.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public int selectInstructorRole(Connection connection)
    {
    	log.info("Finding the role Id for Course instructor");
        int result = 0;
        try
        {
            preparedStatement = connection.prepareStatement(SELECT_ROLE_BY_ROLENAME);
            preparedStatement.setString(1, CatmeUtil.ROLE_INSTRUCTOR);
            rs = preparedStatement.executeQuery();
            if (rs.next())
                result = Integer.parseInt(rs.getString("roleId"));

        } catch (SQLException e)
        {
        	log.error("Failed while finding a role Id for current user");
            e.printStackTrace();
        }

        return result;

    }

    public int addAsCourseInstructor(Connection connection, String course, int userRole)
    {
    	log.info("Adding a user as instructor to a course");
        int result = 0;
        try
        {
            preparedStatement = connection.prepareStatement(SELECT_COURSE_INSTRUCTOR_BY_USER_ROLE_COURSEID);
            preparedStatement.setInt(1, userRole);
            preparedStatement.setString(2, course);
            rs = preparedStatement.executeQuery();
            if (!rs.next())
            {
                preparedStatement = connection.prepareStatement(INSERT_INTO_COURSE_INSTRUCTOR);
                preparedStatement.setInt(2, userRole);
                preparedStatement.setString(1, course);
                result = preparedStatement.executeUpdate();
            } else
            {

                result = rs.getInt("userRoleId");
            }
        } catch (SQLException e)
        {
        	log.error("Failed while adding a role Id as instructor to a course");
            e.printStackTrace();
        }

        return result;
    }

    public int updateQuery(Connection connection, String query, String courseId)
    {
    	log.info("Updating the courses in the DB");
        int result = 0;
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e)
        {
        	log.error("Failed while updating the courses in the DB");
            e.printStackTrace();
        }

        return result;
    }

    public int addCourse(Connection connection, String query, Course course)
    {
    	log.info("Adding a new course by Admin");
        int result = CatmeUtil.ZERO;
        try
        {
            preparedStatement = connection.prepareStatement(CHECK_INSTRUCTOR);
            preparedStatement.setString(1, course.getCourseId());
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next())
            {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, course.getCourseId());
                preparedStatement.setString(2, course.getCourseName());
                result = preparedStatement.executeUpdate();
            } else
                result = CatmeUtil.TWO;
        } catch (SQLException e)
        {
        	log.error("Failed while adding the courses in the DB");
            e.printStackTrace();
        }
        return result;
    }

    public int insertInstructorRole(Connection connection, String user, int roleId)
    {
    	log.info("Assigning the new role to the user as an instructor");
        int userRoleId = 0;
        try
        {
            preparedStatement = connection.prepareStatement(SELECT_USER_ROLE_BY_BANNERID);
            preparedStatement.setString(1, user);
            preparedStatement.setInt(2, roleId);
            rs = preparedStatement.executeQuery();

            if (!rs.next())
            {
                preparedStatement = connection.prepareStatement(INSERT_INTO_USER_ROLE);
                preparedStatement.setInt(1, roleId);
                preparedStatement.setString(2, user);
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement(SELECT_USER_ROLE_BY_BANNERID);
                preparedStatement.setString(1, user);
                preparedStatement.setInt(2, roleId);
                rs = preparedStatement.executeQuery();
                rs.next();

            }
            userRoleId = Integer.parseInt(rs.getString(CatmeUtil.USER_ROLE_ID));

        } catch (SQLException e)
        {
        	log.error("Failed while adding the new instructor role");
            e.printStackTrace();
        }
        return userRoleId;
    }
}