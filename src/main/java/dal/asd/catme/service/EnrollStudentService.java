package dal.asd.catme.service;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.dao.IRoleDao;
import dal.asd.catme.dao.IStudentDao;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.EnrollmentException;
import dal.asd.catme.util.CatmeUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnrollStudentService implements IEnrollStudentService
{
    DatabaseAccess db;
    IRoleDao roleDao;
    IStudentDao studentDao;
    Connection con;

    public EnrollStudentService(IRoleDao roleDao, IStudentDao studentDao)
    {
        this.roleDao = roleDao;
        this.studentDao = studentDao;
    }

    @Override
    public boolean enrollStudentsIntoCourse(ArrayList<Student> students, Course c)
    {
        try
        {
            db = SystemConfig.instance().getDatabaseAccess();
            con = db.getConnection();
            System.out.println("Database Connected");

            for (Student s : students)
            {
                try
                {
                    assignStudentRole(s);
                    enrollStudent(s, c);
                } catch (EnrollmentException e)
                {
                    System.out.println(e.getMessage() + " " + s);
                    return false;
                }
            }
            return true;
        } catch (SQLException e)
        {
            System.out.println("Error connecting database");
        } finally
        {
            try
            {
                con.close();
                System.out.println("Connection closed");
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void enrollStudent(Student s, Course c) throws EnrollmentException
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
