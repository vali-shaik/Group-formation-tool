package dal.asd.catme.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Student;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.dao.IRoleDao;
import dal.asd.catme.dao.IStudentDao;
import dal.asd.catme.dao.IUserDao;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.exception.EnrollmentException;
import dal.asd.catme.util.CatmeUtil;
import dal.asd.catme.util.RandomPasswordGenerator;

public class EnrollStudentService implements IEnrollStudentService
{
    DatabaseAccess db;

    IUserDao userDao;

    IRoleDao roleDao;

    IStudentDao studentDao;

    IMailSenderService mailSenderService;

    Connection con;

    public EnrollStudentService()
    {
       
    }


    public EnrollStudentService(IUserDao userDao, IRoleDao roleDao, IStudentDao studentDao, IMailSenderService mailSenderService)
    {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.studentDao = studentDao;
        this.mailSenderService = mailSenderService;
    }

    @Override
    public boolean enrollStudentsIntoCourse(ArrayList<Student> students, Course c)
    {
        try
        {
        	db=SystemConfig.instance().getDatabaseAccess();
            con = db.getConnection();
            System.out.println("Database Connected");

            for (Student s : students)
            {
                if (userDao.checkExistingUser(s.getBannerId(),con) == 0)
                {
                    try
                    {
                        createNewStudent(s);
                        sendCredentials(s, c);
                    } catch (EnrollmentException e)
                    {
                        System.out.println(e.getMessage() + " " + s);
                        return false;
                    } catch (MailException e)
                    {
                        System.out.println(e.getMessage() + " " + s);
                    }
                }

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
        }
        catch (SQLException e)
        {
            System.out.println("Error connecting database");
        }
        finally
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
        if(!studentDao.enroll(s,c,con))
            throw new EnrollmentException("Error making entry in Enrollment table");
    }

    @Override
    public void assignStudentRole(User student) throws EnrollmentException
    {
        if(roleDao.checkUserRole(student.getBannerId(),CatmeUtil.STUDENT_ROLE_ID,con)==0)
        {
            if(roleDao.assignRole(student.getBannerId(), CatmeUtil.STUDENT_ROLE_ID,con)==0)
                throw new EnrollmentException("Error assigning student role");
        }
    }

    @Override
    public void createNewStudent(User student) throws EnrollmentException
    {
        student.setPassword(RandomPasswordGenerator.generateRandomPassword(CatmeUtil.RANDOM_PASSWORD_LENGTH));
        if(userDao.addUser(student,con)==0)
            throw new EnrollmentException("Error creating new user for student");
    }

    @Override
    public void sendCredentials(User student, Course c) throws MailException
    {
        try
        {
            mailSenderService.sendCredentialsToStudent((Student) student,c);
        } catch (MessagingException e)
        {
            throw new MailSendException("Error sending credentials to student");
        }
    }
}
