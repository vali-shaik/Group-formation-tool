package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.IUser;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.EnrollmentException;
import dal.asd.catme.util.CatmeUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnrollStudentServiceImpl implements IEnrollStudentService
{
    DatabaseAccess db;
    IRoleDao roleDao;
    IStudentDao studentDao;
    Connection con = null;

    public EnrollStudentServiceImpl(IRoleDao roleDao, IStudentDao studentDao)
    {
        this.roleDao = roleDao;
        this.studentDao = studentDao;
    }

    @Override
    public boolean enrollStudentsIntoCourse(ArrayList<IUser> students, ICourse c)
    {
        try
        {
            db = SystemConfig.instance().getDatabaseAccess();
            con = db.getConnection();

            for (IUser s : students)
            {
                try
                {
                    assignStudentRole(s);
                    enrollStudent(s, c);
                } catch (EnrollmentException e)
                {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                con.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void enrollStudent(IUser s, ICourse c) throws EnrollmentException
    {
        if (studentDao.enroll(s, c, con) == false)
            throw new EnrollmentException("Error making entry in Enrollment table");
    }

    @Override
    public void assignStudentRole(IUser student) throws EnrollmentException
    {
        if (roleDao.checkUserRole(student.getBannerId(), CatmeUtil.STUDENT_ROLE_ID, con) == 0)
        {
            if (roleDao.assignRole(student.getBannerId(), CatmeUtil.STUDENT_ROLE_ID, con) == 0)
                throw new EnrollmentException("Error assigning student role");
        }
    }
}
