package dal.asd.catme.courses;

import dal.asd.catme.BaseAbstractFactoryImpl;
import dal.asd.catme.IBaseAbstractFactory;
import dal.asd.catme.accesscontrol.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.database.IDatabaseAbstractFactory;
import dal.asd.catme.database.IDatabaseAccess;
import dal.asd.catme.util.CatmeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnrollStudentServiceImpl implements IEnrollStudentService
{
    IDatabaseAccess db;
    IRoleDao roleDao;
    IStudentDao studentDao;
    Connection con = null;

    IBaseAbstractFactory baseAbstractFactory = BaseAbstractFactoryImpl.instance();
    IDatabaseAbstractFactory databaseAbstractFactory = baseAbstractFactory.makeDatabaseAbstractFactory();
    private static final Logger log = LoggerFactory.getLogger(EnrollStudentServiceImpl.class);

    public EnrollStudentServiceImpl(IRoleDao roleDao, IStudentDao studentDao)
    {
        this.roleDao = roleDao;
        this.studentDao = studentDao;
    }

    @Override
    public boolean enrollStudentsIntoCourse(ArrayList<User> students, Course c)
    {
        log.info("Enrolling students in course: "+c.getCourseId());
        try
        {
            db = databaseAbstractFactory.makeDatabaseAccess();
            con = db.getConnection();

            for (User s : students)
            {
                try
                {
                    assignStudentRole(s);
                    enrollStudent(s, c);
                } catch (EnrollmentException e)
                {
                    log.error("Error enrolling students: "+e.getMessage());
                    e.printStackTrace();
                    return false;
                }
            }

            log.info("Students enrolled successfully");
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
    public void enrollStudent(User s, Course c) throws EnrollmentException
    {
        if (studentDao.enroll(s, c, con) == false)
            throw new EnrollmentException("Error making entry in Enrollment table");
    }

    @Override
    public void assignStudentRole(User student) throws EnrollmentException
    {
        if (roleDao.checkUserRole(student.getBannerId(), CatmeUtil.STUDENT_ROLE_ID, con) == 0)
        {
            if (roleDao.assignRole(student.getBannerId(), CatmeUtil.STUDENT_ROLE_ID, con) == 0)
                throw new EnrollmentException("Error assigning student role");
        }
    }
}
