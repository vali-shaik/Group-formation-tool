package dal.asd.catme.courses;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.accesscontrol.IUserDao;
import dal.asd.catme.util.CatmeUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dal.asd.catme.util.DBQueriesUtil.*;

public class RoleDaoImpl implements IRoleDao
{
    IUserDao userDao;

    ICourseDao courseDao;
    private static final Logger log = LoggerFactory.getLogger(RoleDaoImpl.class);
    @Override
    public int assignRole(String bannerId, int roleId, Connection con)
    {
    	log.info("Assing a new role to the user");
        int rs = 0;
        try
        {
            PreparedStatement stmt = con.prepareStatement(ASSIGN_ROLE_QUERY);
            stmt.setString(1, bannerId);
            stmt.setInt(2, roleId);

            rs = stmt.executeUpdate();
            return rs;
        } catch (Exception e)
        {
        	log.error("Unable to assign a new role to the user");
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public int addInstructor(String courseId, int userRoleId, Connection con)
    {
    	log.info("Adding an instrcutor to the course");
        int rs = 0;
        try
        {
            PreparedStatement stmt = con.prepareStatement(INSERT_COURSE_INSTRUCTOR_QUERY);
            stmt.setString(1, courseId);
            stmt.setInt(2, userRoleId);

            rs = stmt.executeUpdate();
        } catch (Exception e)
        {
        	log.error("Adding an instructor to course is failed");
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public int checkCourseInstructor(String bannerId, String courseId, Connection con)
    {
    	log.info("Checking for an course instructor");
        int rowCount = 0;
        try
        {
            PreparedStatement stmt = con.prepareStatement(CHECK_COURSE_INSTRUCTOR_QUERY);
            stmt.setString(1, bannerId);
            stmt.setString(2, courseId);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e)
        {
        	log.error("Failed while checking for an instructor");
            e.printStackTrace();
        }

        return rowCount;
    }

    @Override
    public int checkUserRole(String bannerId, int roleId, Connection con)
    {
    	log.info("Finding the role of an user");
        int rowCount = 0;
        try
        {
            PreparedStatement stmt = con.prepareStatement(CHECK_USER_ROLE);
            stmt.setString(1, bannerId);
            stmt.setInt(2, roleId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e)
        {
        	log.error("Unable to find an user role");
            e.printStackTrace();
        }

        return rowCount;

    }

    @Override
    public int getUserRoleId(String bannerId, int roleId, Connection con)
    {
    	log.info("Finding the role Id of current user");
        int userRoleId = -1;
        try
        {
            PreparedStatement stmt = con.prepareStatement(GET_USER_ROLEID_QUERY);
            stmt.setString(1, bannerId);
            stmt.setInt(2, roleId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            userRoleId = rs.getInt(1);
        } catch (SQLException e)
        {
        	log.error("Unable to find the role Id of current user");
            e.printStackTrace();
        }
        return userRoleId;
    }

    @Override
    public String assignTa(Enrollment user, Connection con)
    {
        String isAssigned = "";
        log.info("Assigning a TA to the course");
        try
        {
            userDao = BaseAbstractFactoryImpl.instance().makeAccessControlAbstractFactory().makeUserDao();
            if (0 != userDao.checkExistingUser(user.getBannerId(), con))
            {
                courseDao = BaseAbstractFactoryImpl.instance().makeCourseAbstractFactory().makeCourseDao();
                if (0 != courseDao.checkCourseExists(user.getCourseId(), con))
                {
                    if (0 == courseDao.checkCourseRegistration(user.getBannerId(), user.getCourseId(), con))
                    {
                        if (0 == checkCourseInstructor(user.getBannerId(), user.getCourseId(), con))
                        {
                            if (0 != checkUserRole(user.getBannerId(), CatmeUtil.TA_ROLE_ID, con))
                            {
                                int userRoleId = getUserRoleId(user.getBannerId(), CatmeUtil.TA_ROLE_ID, con);
                                addInstructor(user.getCourseId(), userRoleId, con);
                            } else
                            {
                                assignRole(user.getBannerId(), CatmeUtil.TA_ROLE_ID, con);
                                int userRoleId = getUserRoleId(user.getBannerId(), CatmeUtil.TA_ROLE_ID, con);
                                addInstructor(user.getCourseId(), userRoleId, con);
                            }
                            isAssigned = "The user is successfully assigned as TA.";

                        } else
                        {
                            isAssigned = "This user is already an instructor for this course.";
                        }
                    } else
                    {
                        isAssigned = "This user is currently registered in this course. Failed to assign.";
                    }
                } else
                {
                    isAssigned = "No course exists with this Course Id. Failed to assign.";
                }

            } else
            {
                isAssigned = "No user exists with this Banner Id. Failed to assign.";
            }
        } catch (Exception e)
        {
        	log.info("Failed while assigning the TA");
            e.printStackTrace();
        } finally
        {
            if (con != null)
            {
                try
                {
                    con.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return isAssigned;
    }
}